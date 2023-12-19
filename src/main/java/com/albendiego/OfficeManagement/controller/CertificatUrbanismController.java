package com.albendiego.OfficeManagement.controller;

import com.albendiego.OfficeManagement.model.CertificatUrbanism;
import com.albendiego.OfficeManagement.model.CertificatUrbanismDTO;
import com.albendiego.OfficeManagement.model.Proiect;
import com.albendiego.OfficeManagement.service.CertificatUrbanismService;
import com.albendiego.OfficeManagement.service.EmailService;
import com.albendiego.OfficeManagement.service.FiserService;
import com.albendiego.OfficeManagement.service.ProiectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping()
@RequiredArgsConstructor
public class CertificatUrbanismController {

    private final CertificatUrbanismService certificatUrbanismService;
    private final ProiectService proiectService;
    private final EmailService emailService;
    private final FiserService fisierService;


    @CrossOrigin
    @PostMapping("/certificate")
    public ResponseEntity<CertificatUrbanism> addCertificatUrbanism(@RequestBody CertificatUrbanism certificatUrbanism) {
        CertificatUrbanism newCertificatUrbanism = this.certificatUrbanismService.addCertificatUrbanism(certificatUrbanism);
        return new ResponseEntity<>(newCertificatUrbanism, HttpStatus.CREATED);
    }

    @CrossOrigin
    @PutMapping("/certificate")
    public ResponseEntity<CertificatUrbanism> updateCertificatUrbanism(
            @RequestBody CertificatUrbanism certificatUrbanism) {
        CertificatUrbanism certificatUrbanism1 = this.certificatUrbanismService.updateCertificatUrbanism(certificatUrbanism);

        return new ResponseEntity<>(certificatUrbanism1, HttpStatus.OK);
    }


    @CrossOrigin
    @GetMapping("/certificate")
    public ResponseEntity<List<CertificatUrbanismDTO>> getCertificateUrbanism() {
        List<CertificatUrbanismDTO> listaCertificate = this.certificatUrbanismService.getAllCertificatUrbanism();
        return new ResponseEntity<>(listaCertificate, HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/certificate/{id}")
    public ResponseEntity<Optional<CertificatUrbanism>> getCertificatUrbanism(@PathVariable("id") Long id) {
        Optional<CertificatUrbanism> certificatUrbanism = this.certificatUrbanismService.getCertificatUrbanism(id);
        return new ResponseEntity<>(certificatUrbanism, HttpStatus.OK);
    }

    @CrossOrigin
    @DeleteMapping("/certificate/{id}")
    public ResponseEntity<?> deleteCertificatUrbanism(@PathVariable("id") Long id) {
        certificatUrbanismService.deleteCertificatUrbanism(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping("/certificate/{id}/proiecte")
    public ResponseEntity<Proiect> addProiectLaCertificat(@PathVariable Long id,
                                                          @RequestBody Proiect proiect) {
        Proiect proiectNou = this.proiectService.addProiectCuCertificat(id, proiect);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/certificate/{id}/proiecte")
    public ResponseEntity<List<Proiect>> getProiecteCuCertificat(@PathVariable Long id) {
        List<Proiect> proiecte = this.proiectService.getProiecteCuCertificat(id);
        return new ResponseEntity<>(proiecte, HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping("/certificate/{id}/fisiere")
    public ResponseEntity<?> uploadFisierCertificat(@PathVariable Long id, @RequestParam(value = "file") MultipartFile file) throws IOException {
        String uploadFisierCertificat = this.fisierService.addFisierCertificat(id, file);
        return ResponseEntity.status(HttpStatus.OK).body(uploadFisierCertificat);
    }


    @CrossOrigin
    @GetMapping("/certificate/{id}/fisiere/{numeFisier}")
    public ResponseEntity<?> downloadFisierCertificat(@PathVariable String numeFisier) throws IOException {

        byte[] fisierCertificat = this.fisierService.downloadFisierCertificat(numeFisier);
        return ResponseEntity.status(HttpStatus.OK).body(fisierCertificat);
    }

    @CrossOrigin
    @GetMapping("/certificate/fara-proiect")
    public ResponseEntity<List<CertificatUrbanism>> getCertificateFaraProiect() {
        List<CertificatUrbanism> certificate = this.certificatUrbanismService.getCertificatFaraProiect();
        return new ResponseEntity<>(certificate, HttpStatus.OK);
    }


}
