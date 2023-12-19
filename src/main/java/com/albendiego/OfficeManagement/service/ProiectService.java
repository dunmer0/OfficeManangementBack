package com.albendiego.OfficeManagement.service;

import com.albendiego.OfficeManagement.model.CertificatUrbanism;
import com.albendiego.OfficeManagement.model.Proiect;

import java.util.List;
import java.util.Optional;

public interface ProiectService {
    Proiect addProiect(Proiect proiect);

    List<Proiect> getProiecte();

    Optional<Proiect> getProiect(long id);

    void deleteProiect(long id);

    Proiect updateProiect(Proiect proiect);

    Proiect addProiectCuCertificat(long certificatId, Proiect proiect);

    List<Proiect> getProiecteCuCertificat(long certificatID);

    List<Proiect> searchProiecte(String search);

    List<Proiect> getProiecteFaraCertificat();

    Proiect updateProiectCuCertificat(Proiect proiect, CertificatUrbanism certificatUrbanism);
}
