package com.albendiego.OfficeManagement.service;

import com.albendiego.OfficeManagement.model.Beneficiar;
import com.albendiego.OfficeManagement.model.Institutie;

import java.util.List;

public interface BeneficiarService {
    List<Beneficiar> findAll();

    Beneficiar findBeneficiar(Long id);

    Beneficiar addBeneficiar(Beneficiar beneficiar);

    void deleteBeneficiar(Long id);

    Beneficiar updateBeneficiar(Beneficiar beneficiar);
}
