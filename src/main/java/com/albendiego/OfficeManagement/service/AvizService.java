package com.albendiego.OfficeManagement.service;

import com.albendiego.OfficeManagement.model.Aviz;

import java.util.List;

public interface AvizService {

    Aviz addAviz(Long proiectId, Aviz aviz);
    List<Aviz> findAvize(Long proiectId);

//    Aviz findAviz(Long proiectId, Long avizId);

    Aviz findAviz(long avizId);

    void deleteAviz(Long proiectId, Long avizId);

    Aviz updateAviz(Aviz aviz);



}
