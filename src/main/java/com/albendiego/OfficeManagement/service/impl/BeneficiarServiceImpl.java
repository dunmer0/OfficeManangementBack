package com.albendiego.OfficeManagement.service.impl;

import com.albendiego.OfficeManagement.model.Beneficiar;
import com.albendiego.OfficeManagement.model.Institutie;
import com.albendiego.OfficeManagement.repository.BeneficiarRepository;
import com.albendiego.OfficeManagement.service.BeneficiarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BeneficiarServiceImpl implements BeneficiarService {
    private final BeneficiarRepository beneficiarRepository;
    @Override
    public List<Beneficiar> findAll() {
        return this.beneficiarRepository.findAll();
    }

    @Override
    public Beneficiar findBeneficiar(Long id) {
        return this.beneficiarRepository.findById(id).orElse(new Beneficiar());
    }

    @Override
    public Beneficiar addBeneficiar(Beneficiar beneficiar) {
        return this.beneficiarRepository.save(beneficiar);
    }

    @Override
    public void deleteBeneficiar(Long id) {
        this.beneficiarRepository.deleteById(id);
    }

    @Override
    public Beneficiar updateBeneficiar(Beneficiar beneficiar) {
        return this.beneficiarRepository.save(beneficiar);
    }
}
