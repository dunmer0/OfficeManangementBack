package com.albendiego.OfficeManagement.service.impl;

import com.albendiego.OfficeManagement.model.CertificatUrbanism;
import com.albendiego.OfficeManagement.model.CertificatUrbanismDTO;
import com.albendiego.OfficeManagement.repository.CertificatUrbanismRepository;
import com.albendiego.OfficeManagement.service.CertificatUrbanismService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CertificatUrbanismServiceImpl implements CertificatUrbanismService {

    private final CertificatUrbanismRepository certificatUrbanismRepository;


    @Override
    public CertificatUrbanism addCertificatUrbanism(CertificatUrbanism certificatUrbanism) {
        return this.certificatUrbanismRepository.save(certificatUrbanism);
    }

    @Override
    public CertificatUrbanism updateCertificatUrbanism(CertificatUrbanism certificatUrbanism) {
        return this.certificatUrbanismRepository.save(certificatUrbanism);
    }

    @Override
    public List<CertificatUrbanismDTO> getAllCertificatUrbanism() {
        List<CertificatUrbanismDTO> certificatUrbanismDTOList = new ArrayList<>();
        List<CertificatUrbanism> certificatUrbanismList = this.certificatUrbanismRepository.findAll();

        certificatUrbanismList.forEach(
                certificatUrbanism -> {
                    CertificatUrbanismDTO certificatUrbanismDTO = this.certificatToCertificatDTO(certificatUrbanism);
                    certificatUrbanismDTOList.add(certificatUrbanismDTO);
                }
        );

        return certificatUrbanismDTOList;
    }

    @Override
    public Optional<CertificatUrbanism> getCertificatUrbanism(Long id) {
        return this.certificatUrbanismRepository.findById(id);
    }

    @Override
    public void deleteCertificatUrbanism(Long id) {
        this.certificatUrbanismRepository.deleteById(id);
    }

    private CertificatUrbanismDTO certificatToCertificatDTO(CertificatUrbanism certificatUrbanism) {

        return new CertificatUrbanismDTO(certificatUrbanism.getId(),
                certificatUrbanism.getNumar(), certificatUrbanism.getBeneficiar().getNume(),
                certificatUrbanism.getAdresa(), certificatUrbanism.getDescriere(),
                certificatUrbanism.getDataEliberare(), this.calcDataExpirare(certificatUrbanism),
                certificatUrbanism.getValabilitate()
        );

    }

    private LocalDate calcDataExpirare(CertificatUrbanism certificatUrbanism) {
        if (certificatUrbanism.getDataEliberare() != null) {
            return certificatUrbanism.getDataEliberare().plusMonths(certificatUrbanism.getValabilitate());
        }
        return null;
    }

    @Override
    public List<CertificatUrbanism> getCertificatFaraProiect() {
        List<CertificatUrbanism> certificate = this.certificatUrbanismRepository.findAll();
        return certificate.stream().filter(certificat -> certificat.getProiecte().isEmpty())
                .collect(Collectors.toList());
    }


}
