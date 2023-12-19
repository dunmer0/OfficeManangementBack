package com.albendiego.OfficeManagement.service.impl;

import com.albendiego.OfficeManagement.exception.OfficeManagementAPIException;
import com.albendiego.OfficeManagement.model.Aviz;
import com.albendiego.OfficeManagement.model.Proiect;
import com.albendiego.OfficeManagement.repository.AvizRepository;
import com.albendiego.OfficeManagement.repository.ProiectRepository;
import com.albendiego.OfficeManagement.service.AvizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AvizServiceImpl implements AvizService {
    private final AvizRepository avizRepository;
    private final ProiectRepository proiectRepository;


    @Override
    public Aviz addAviz(Long proiectId, Aviz aviz) {
        Proiect proiect = this.proiectRepository.getReferenceById(proiectId);
        aviz.setProiect(proiect);
        return this.avizRepository.save(aviz);
    }


    @Override
    public List<Aviz> findAvize(Long proiectId) {

        return this.avizRepository.findByProiectId(proiectId);
    }


    @Override
    public Aviz findAviz(long avizId) {

        return this.avizRepository.getReferenceById(avizId);
    }

//    @Override
//    public Aviz findAviz(Long proiectId, Long avizId) {
//        Proiect proiect = this.proiectRepository.getReferenceById(proiectId);
//        Aviz aviz = this.avizRepository.getReferenceById(avizId);
//        if (!aviz.getProiect().getId().equals(proiect.getId())) {
//            throw new OfficeManagementAPIException(
//                    HttpStatus.BAD_REQUEST, "Avizul nu apartine proiectului.");
//        }
//        return aviz;
//    }


    @Override
    public void deleteAviz(Long proiectId, Long avizId) {
        Proiect proiect = this.proiectRepository.getReferenceById(proiectId);
        Aviz aviz = this.avizRepository.getReferenceById(avizId);
        if (!aviz.getProiect().getId().equals(proiect.getId())) {
            throw new OfficeManagementAPIException(
                    HttpStatus.BAD_REQUEST, "Avizul nu apartine proiectului.");

        }
        this.avizRepository.delete(aviz);
    }

    @CrossOrigin
    @Override
    public Aviz updateAviz(Aviz aviz) {
        return this.avizRepository.save(aviz);
    }
}
