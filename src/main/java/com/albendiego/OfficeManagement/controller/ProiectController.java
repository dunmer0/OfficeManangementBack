package com.albendiego.OfficeManagement.controller;

import com.albendiego.OfficeManagement.model.CertificatUrbanism;
import com.albendiego.OfficeManagement.model.Proiect;
import com.albendiego.OfficeManagement.service.CertificatUrbanismService;
import com.albendiego.OfficeManagement.service.ProiectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.beans.PropertyEditor;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping()
@RequiredArgsConstructor
public class ProiectController {

    private final ProiectService proiectService;


    @CrossOrigin
    @GetMapping("/proiecte")
    public ResponseEntity<List<Proiect>> getProiecte() {
        List<Proiect> proiecte = this.proiectService.getProiecte();
        return new ResponseEntity<>(proiecte, HttpStatus.OK);
    }
    @CrossOrigin
    @PostMapping("/proiecte")
    public ResponseEntity<Proiect> addProiect(@RequestBody Proiect proiect) {
        Proiect proiectNou = this.proiectService.addProiect(proiect);
        return new ResponseEntity<>(proiectNou, HttpStatus.CREATED);
    }
    @CrossOrigin
    @PutMapping("/proiecte")
    public ResponseEntity<Proiect> updateProiect(@RequestBody Proiect proiect) {
        Proiect proiectNou = this.proiectService.updateProiect(proiect);
        return new ResponseEntity<>(proiectNou, HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/proiecte/{id}")
    public ResponseEntity<Optional<Proiect>> getProiect(@PathVariable("id") Long id) {
        Optional<Proiect> proiect = this.proiectService.getProiect(id);
        return new ResponseEntity<>(proiect, HttpStatus.OK);
    }

    @CrossOrigin
    @DeleteMapping("/proiecte/{id}")
    public ResponseEntity<?> deleteProiect(@PathVariable("id") Long id) {
        this.proiectService.deleteProiect(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/proiecte/search")
    public ResponseEntity<List<Proiect>> searchProiecte(@RequestParam("query") String query) {
        List<Proiect> proiecteGasite = this.proiectService.searchProiecte(query);
        return new ResponseEntity<>(proiecteGasite, HttpStatus.OK);
    }

    @CrossOrigin
    @PutMapping("/proiecte/proiect-certificat")
    public ResponseEntity<Proiect> updateProiectCuCertificat(@RequestBody Proiect proiect, @RequestBody CertificatUrbanism certificatUrbanism) {
        Proiect proiectNou = this.proiectService.updateProiectCuCertificat(proiect, certificatUrbanism);
        return new ResponseEntity<>(proiectNou, HttpStatus.OK);
    }
}
