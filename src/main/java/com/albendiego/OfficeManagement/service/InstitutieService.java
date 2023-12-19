package com.albendiego.OfficeManagement.service;

import com.albendiego.OfficeManagement.model.Institutie;

import java.util.List;

public interface InstitutieService {
    List<Institutie> findAll();

    Institutie findInstitutie(Long id);

    Institutie addInstitutie(Institutie institutie);

    void deleteInstitutie(Long id);

    Institutie updateInstitutie(Institutie institutie);

}
