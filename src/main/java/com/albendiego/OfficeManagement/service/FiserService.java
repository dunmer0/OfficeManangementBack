package com.albendiego.OfficeManagement.service;

import com.albendiego.OfficeManagement.model.FisierCertificat;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface FiserService {


   String addFisierCertificat(long certificatId, MultipartFile fisier) throws IOException;

   String addFisierCertificat(MultipartFile fisier) throws IOException;

   List<String> addFisierProiect(long proiectId, List<MultipartFile> fisiere) throws IOException;

   List<String> addFisierAviz(long avizId, List<MultipartFile> fisiere) throws IOException;

   List<FisierCertificat> findAllFisiersById(long id);

   Optional<FisierCertificat> getFisier(long id);

   void deleteFisier(long id);

   byte[] downloadFisierCertificat(String filename) throws IOException;
}
