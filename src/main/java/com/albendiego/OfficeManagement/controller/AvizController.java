package com.albendiego.OfficeManagement.controller;

import com.albendiego.OfficeManagement.model.Aviz;
import com.albendiego.OfficeManagement.service.AvizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/proiecte")
@RequiredArgsConstructor
public class AvizController {
    private final AvizService avizService;

    @CrossOrigin
    @PostMapping("/{proiectId}/avize")
    public ResponseEntity<Aviz> addAvize(@PathVariable Long proiectId,
                                         @RequestBody Aviz aviz) {
        Aviz avizNou = this.avizService.addAviz(proiectId, aviz);
        return new ResponseEntity<>(avizNou, HttpStatus.CREATED);
    }

    @CrossOrigin
    @GetMapping("/{proiectId}/avize")
    public ResponseEntity<List<Aviz>> getAvize(@PathVariable Long proiectId) {
        List<Aviz> avize = this.avizService.findAvize(proiectId);
        return new ResponseEntity<>(avize, HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/avize/{avizId}")
    public ResponseEntity<Aviz> getAviz(
                                        @PathVariable long avizId) {
        Aviz aviz = this.avizService.findAviz(avizId);
        return new ResponseEntity<>(aviz, HttpStatus.OK);
    }

    @CrossOrigin
    @PutMapping("/avize")
    public ResponseEntity<Aviz> updateAviz(
                                           @RequestBody Aviz aviz) {
        Aviz avizNou = this.avizService.updateAviz(aviz);
        return new ResponseEntity<>(avizNou, HttpStatus.OK);
    }

    @CrossOrigin
    @DeleteMapping("{proiectId}/avize/{avizId}")
    public ResponseEntity<?> deleteAviz(@PathVariable long proiectId,
                                        @PathVariable long avizId) {
        this.avizService.deleteAviz(proiectId, avizId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
