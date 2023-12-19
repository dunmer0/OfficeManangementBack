package com.albendiego.OfficeManagement.service.impl;

import com.albendiego.OfficeManagement.model.Institutie;
import com.albendiego.OfficeManagement.repository.InstitutieRepository;
import com.albendiego.OfficeManagement.service.InstitutieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InstitutieServiceImpl implements InstitutieService {
    private final InstitutieRepository institutieRepository;

    @Override
    public List<Institutie> findAll() {
        return this.institutieRepository.findAll();
    }

    @Override
    public Institutie findInstitutie(Long id) {
        return this.institutieRepository.findById(id).orElse(new Institutie());
    }

    @Override
    public Institutie addInstitutie(Institutie institutie) {
        return this.institutieRepository.save(institutie);
    }

    @Override
    public void deleteInstitutie(Long id) {
        this.institutieRepository.deleteById(id);
    }

    @Override
    public Institutie updateInstitutie(Institutie institutie) {
        return this.institutieRepository.save(institutie);
    }
}
