package com.albendiego.OfficeManagement.repository;

import com.albendiego.OfficeManagement.model.Aviz;
import com.albendiego.OfficeManagement.model.Proiect;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AvizRepository extends JpaRepository<Aviz, Long> {

    List<Aviz> findByProiectId(long id);
}
