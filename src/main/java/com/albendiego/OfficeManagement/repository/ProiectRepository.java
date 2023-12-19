package com.albendiego.OfficeManagement.repository;

import com.albendiego.OfficeManagement.model.Proiect;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProiectRepository extends JpaRepository<Proiect, Long> {
    List<Proiect> findByCertificatId(long certificatID);
}
