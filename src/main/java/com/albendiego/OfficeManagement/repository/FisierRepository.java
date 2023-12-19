package com.albendiego.OfficeManagement.repository;

import com.albendiego.OfficeManagement.model.FisierCertificat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FisierRepository extends JpaRepository<FisierCertificat, Long> {


   Optional<FisierCertificat> findByNume(String fileName);

   List<FisierCertificat> getAllFisiereById(long id);


}
