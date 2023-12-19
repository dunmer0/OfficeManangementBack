package com.albendiego.OfficeManagement.controller;

import com.albendiego.OfficeManagement.model.Institutie;
import com.albendiego.OfficeManagement.service.InstitutieService;
import com.albendiego.OfficeManagement.service.impl.InstitutieServiceImpl;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/institutii")
@RequiredArgsConstructor
public class InstitutieController {
    private final InstitutieService institutieService;

    @GetMapping
    public ResponseEntity<List<Institutie>> getInstitutii() {
        return new ResponseEntity<>(this.institutieService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Institutie> addInstitutie(Institutie institutie) {
        return new ResponseEntity<>(this.institutieService.addInstitutie(institutie), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Institutie> updateInstitutie(Institutie institutie) {
        return new ResponseEntity<>(this.institutieService.updateInstitutie(institutie), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Institutie> getInstitutie(@PathVariable("id") Long id) {
        return new ResponseEntity<>(this.institutieService.findInstitutie(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteInstitutie(@PathVariable("id") Long id) {
        this.institutieService.deleteInstitutie(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
