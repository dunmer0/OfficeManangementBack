package com.albendiego.OfficeManagement.service.impl;

import com.albendiego.OfficeManagement.model.CertificatUrbanism;
import com.albendiego.OfficeManagement.model.Proiect;
import com.albendiego.OfficeManagement.repository.CertificatUrbanismRepository;
import com.albendiego.OfficeManagement.repository.ProiectRepository;
import com.albendiego.OfficeManagement.service.ProiectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProiectServiceImpl implements ProiectService {

    private final ProiectRepository proiectRepository;
    private final CertificatUrbanismRepository certificatUrbanismRepository;

    @Override
    public Proiect addProiect(Proiect proiect) {
        return this.proiectRepository.save(proiect);
    }

    @Override
    public List<Proiect> getProiecte() {
        return this.proiectRepository.findAll();
    }

    @Override
    public Optional<Proiect> getProiect(long id) {
        return this.proiectRepository.findById(id);
    }

    @Override
    public void deleteProiect(long id) {
        this.proiectRepository.deleteById(id);
    }

    @Override
    public Proiect updateProiect(Proiect proiect) {
        return this.proiectRepository.save(proiect);
    }

    @Override
    public Proiect addProiectCuCertificat(long certificatId, Proiect proiect) {
        CertificatUrbanism certificatUrbanism = this.certificatUrbanismRepository.getReferenceById(certificatId);
        proiect.setCertificat(certificatUrbanism);
        return this.proiectRepository.save(proiect);
    }

    @Override
    public List<Proiect> getProiecteCuCertificat(long certificatID) {
        return this.proiectRepository.findByCertificatId(certificatID);
    }

    @Override
    public List<Proiect> searchProiecte(String search) {
        List<Proiect> proiecte = this.proiectRepository.findAll();
        return proiecte.stream().filter(proiect ->
                        proiect.toString().toLowerCase().contains(search.toLowerCase())
                ).sorted(Comparator.comparing(Proiect::getNumar))
                .collect(Collectors.toList());
    }

    @Override
    public List<Proiect> getProiecteFaraCertificat() {
        List<Proiect> proiecte = this.proiectRepository.findAll();
        return proiecte.stream()
                .filter(proiect -> proiect.getCertificat() == null)
                .collect(Collectors.toList());
    }

    @Override
    public Proiect updateProiectCuCertificat(Proiect proiect, CertificatUrbanism certificatUrbanism) {
        proiect.setCertificat(certificatUrbanism);
        return this.proiectRepository.save(proiect);
    }


}
