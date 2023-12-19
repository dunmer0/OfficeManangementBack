package com.albendiego.OfficeManagement.service.impl;

import com.albendiego.OfficeManagement.model.Aviz;
import com.albendiego.OfficeManagement.model.CertificatUrbanism;
import com.albendiego.OfficeManagement.repository.AvizRepository;
import com.albendiego.OfficeManagement.repository.CertificatUrbanismRepository;
import com.albendiego.OfficeManagement.service.EmailService;
import com.albendiego.OfficeManagement.service.Verificator;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@EnableScheduling
@EnableAsync
public class VerificatorImpl implements Verificator {

    private final CertificatUrbanismRepository certificatUrbanismRepository;
    private final EmailService emailService;
    private final AvizRepository avizRepository;


    @Override
    @Scheduled(cron = "0 0 9 * * MON-FRI")
    @Async
    public void checkCertificate() {
        List<CertificatUrbanism> certificatUrbanismList = this.certificatUrbanismRepository.findAll();
        List<CertificatUrbanism> certificateExpirate = new ArrayList<>();
        certificatUrbanismList
                .forEach(certificat -> {
                    LocalDate dataExpirare = calcDataExpirare(certificat);
                    LocalDate dataActuala = LocalDate.now();
                    long daysDiff = ChronoUnit.DAYS.between(dataActuala, dataExpirare);
                    if (daysDiff < 45 && certificat.getVerificat() < 1) {
                        certificateExpirate.add(certificat);
                        certificat.setVerificat(1);
                        this.certificatUrbanismRepository.save(certificat);
                        System.out.println(certificat.getNumar() + " " + daysDiff);
                    } else if (daysDiff < 38 && certificat.getVerificat() < 2) {
                        certificateExpirate.add(certificat);
                        certificat.setVerificat(2);
                        this.certificatUrbanismRepository.save(certificat);
                    }
                });

        certificateExpirate.forEach(certificat -> {
            String numarCertificat = certificat.getNumar();
            String[] eMail = {"office@albendiego.ro", "contact@albendiego.ro", "ioanlarionesi@albendiego.ro"};
            String subiect = "Expirare certificat " + numarCertificat;
            String text = "Certificatul cu numarul " +
                    numarCertificat +
                    " va expira in curand.\nVa rugam sa efectuati etapele necesare prelungirii certificatului.";
            this.emailService.sendMessage(eMail, subiect, text);
        });
        System.out.println("Am verificat certificate in data de: " + LocalDateTime.now());
    }

    @Override
    @Scheduled(cron = "0 15 9 * * MON-FRI")
    // @Scheduled(fixedRate = 30000)
    @Async
    public void checkAvize() {
        List<Aviz> avize = this.avizRepository.findAll();
        List<Aviz> avizeDeEliberat = new ArrayList<>();
        avize.forEach(aviz -> {
            LocalDate expirareTimpVerificare = calcDataIesireAviz(aviz);
            LocalDate dataActuala = LocalDate.now();
            long daysDiff;
            if (expirareTimpVerificare != null) {
                daysDiff = ChronoUnit.DAYS.between(expirareTimpVerificare, dataActuala);
                if (daysDiff >= 0 && aviz.getVerificat() == 0) {
                    avizeDeEliberat.add(aviz);
                    aviz.setVerificat(1);
                    this.avizRepository.save(aviz);
                } else if (daysDiff >= 7 && aviz.getVerificat() == 1 && !aviz.getEliberat()) {
                    avizeDeEliberat.add(aviz);
                    aviz.setVerificat(2);
                    this.avizRepository.save(aviz);
                } else if (daysDiff >= 14 && aviz.getVerificat() == 2 && !aviz.getEliberat()) {
                    avizeDeEliberat.add(aviz);
                    aviz.setVerificat(3);
                    this.avizRepository.save(aviz);
                }
            }
        });
        avizeDeEliberat.forEach(aviz -> {
            String numarProiect = aviz.getProiect().getNumar();
            String tipAviz = aviz.getTip();
            String[] eMail = {"office@albendiego.ro"};
            String subiect = "Verificare aviz " + tipAviz + aviz.getInstitutie() + " proiect " + numarProiect;
            String text = "Avizul de " +
                    tipAviz + " " +
                    aviz.getInstitutie() +
                    " de la proiectul cu numarul " +
                    numarProiect +
                    "\nNu a fost eliberat in timpul preconizat in timpul depunerii." +
                    "\nVa rugam sa verificati starea avizului." +
                    "\nMultumim.";
            this.emailService.sendMessage(eMail, subiect, text);

        });
        System.out.println("Am verificat avizele in data de: " + LocalDateTime.now());
    }

    private LocalDate calcDataExpirare(CertificatUrbanism certificatUrbanism) {
        if (certificatUrbanism.getDataEliberare() != null) {
            return certificatUrbanism.getDataEliberare().plusMonths(certificatUrbanism.getValabilitate());
        }
        return null;
    }

    private LocalDate calcDataIesireAviz(Aviz aviz) {
        if (aviz.getDataDepunere() != null && !aviz.getEliberat()) {
            return aviz.getDataDepunere().plusDays(aviz.getPerioadaRezolvare());
        }
        return null;
    }


}

