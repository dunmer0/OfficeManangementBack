package com.albendiego.OfficeManagement.service.impl;

import com.albendiego.OfficeManagement.model.CertificatUrbanism;
import com.albendiego.OfficeManagement.model.FisierCertificat;
import com.albendiego.OfficeManagement.repository.CertificatUrbanismRepository;
import com.albendiego.OfficeManagement.repository.FisierRepository;
import com.albendiego.OfficeManagement.service.FiserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FisierServiceImpl implements FiserService {

    private final FisierRepository fisierRepository;
    private final CertificatUrbanismRepository certificatUrbanismRepository;


    @Override
    public String addFisierCertificat(long certificatId, MultipartFile fisier) throws IOException {
        CertificatUrbanism certificatUrbanism = this.certificatUrbanismRepository.getReferenceById(certificatId);
        String numarCertificat = certificatUrbanism.getNumar();
        String FOLDER_PATH = "h:\\Personale\\_Applications\\OfficeManagement\\Storage\\" + numarCertificat + "\\";
        Path fileStoragePath = Paths.get(FOLDER_PATH).toAbsolutePath().normalize();
        Files.createDirectories(fileStoragePath);
        String filePath = FOLDER_PATH + fisier.getOriginalFilename();
        FisierCertificat fisierCertificat = fisierRepository.save(FisierCertificat.builder()
                .nume(fisier.getOriginalFilename())
                .tip(fisier.getContentType())
                .filePath(filePath)
                .certificatUrbanism(certificatUrbanism)
                .build());

        fisier.transferTo(new File(filePath));

        if (fisierCertificat != null) {
            return "uploadat cu succes" + fisier.getOriginalFilename();
        }

        return null;
    }

    @Override
    public String addFisierCertificat(MultipartFile fisier) throws IOException {


        String FOLDER_PATH = "H:\\Apps\\Albendiego\\OfficeManagement_Storage\\";
        String filePath = FOLDER_PATH + fisier.getOriginalFilename();
        FisierCertificat fisierCertificat = fisierRepository.save(FisierCertificat.builder()
                .nume(fisier.getOriginalFilename())
                .tip(fisier.getContentType())
                .filePath(filePath)
                .build());

        fisier.transferTo(new File(filePath));

        if (fisierCertificat != null) {
            return "uploadat cu succes" + fisier.getOriginalFilename();
        }

        return null;
    }

    @Override
    public List<String> addFisierProiect(long proiectId, List<MultipartFile> fisiere) throws IOException {
        return null;
    }

    @Override
    public List<String> addFisierAviz(long avizId, List<MultipartFile> fisiere) throws IOException {
        return null;
    }

    public byte[] downloadFile(String fileName) throws IOException {
        Optional<FisierCertificat> fisier1 = this.fisierRepository.findByNume(fileName);
        String filePath = fisier1.get().getFilePath();
        byte[] fisier = Files.readAllBytes(new File(filePath).toPath());
        return fisier;
    }

    @Override
    public List<FisierCertificat> findAllFisiersById(long id) {
        return this.fisierRepository.getAllFisiereById(id);
    }


    @Override
    public Optional<FisierCertificat> getFisier(long id) {
        return this.fisierRepository.findById(id);
    }

    @Override
    public void deleteFisier(long id) {
        this.fisierRepository.deleteById(id);
    }

    @Override
    public byte[] downloadFisierCertificat(String filename) throws IOException {
        Optional<FisierCertificat> fisierCertificat = fisierRepository.findByNume(filename);
        String filePath = fisierCertificat.get().getFilePath();
        byte[] fisier = Files.readAllBytes(new File(filePath).toPath());
        return fisier;
    }
}
