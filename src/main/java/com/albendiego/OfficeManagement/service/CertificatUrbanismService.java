package com.albendiego.OfficeManagement.service;

import com.albendiego.OfficeManagement.model.CertificatUrbanism;
import com.albendiego.OfficeManagement.model.CertificatUrbanismDTO;

import java.util.List;
import java.util.Optional;

public interface CertificatUrbanismService {



    CertificatUrbanism addCertificatUrbanism(CertificatUrbanism certificatUrbanism);

    CertificatUrbanism updateCertificatUrbanism(CertificatUrbanism certificatUrbanism);

    List<CertificatUrbanismDTO> getAllCertificatUrbanism();

    Optional<CertificatUrbanism> getCertificatUrbanism(Long id);

    void deleteCertificatUrbanism(Long id);
    
    List<CertificatUrbanism> getCertificatFaraProiect();
}
