package com.albendiego.OfficeManagement.controller;

import com.albendiego.OfficeManagement.model.Beneficiar;
import com.albendiego.OfficeManagement.service.BeneficiarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/beneficiari")
@RequiredArgsConstructor
@CrossOrigin
public class BeneficiarController {
    private final BeneficiarService beneficiarService;

    @GetMapping
    public ResponseEntity<List<Beneficiar>> getProiecte() {
        return new ResponseEntity<>(this.beneficiarService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Beneficiar> addBeneficiar(@RequestBody Beneficiar beneficiar) {
        return new ResponseEntity<>(this.beneficiarService.addBeneficiar(beneficiar), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Beneficiar> updateBeneficiar(@RequestBody Beneficiar beneficiar) {
        return new ResponseEntity<>(this.beneficiarService.updateBeneficiar(beneficiar), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Beneficiar> getBeneficiar(@PathVariable("id") Long id) {
        return new ResponseEntity<>(this.beneficiarService.findBeneficiar(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBeneficar(@PathVariable("id") Long id) {
        this.beneficiarService.deleteBeneficiar(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
