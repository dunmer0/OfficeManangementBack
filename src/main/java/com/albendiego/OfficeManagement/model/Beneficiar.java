package com.albendiego.OfficeManagement.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Beneficiar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false, name = "id")
    private long id;
    private String nume;
    private String adresa;
    private String localitate;
    private String email;
    private String telefon;
    @OneToMany(mappedBy = "beneficiar")
    private Set<Proiect> proiecte = new HashSet<>();
    @OneToMany(mappedBy = "beneficiar")
    private Set<CertificatUrbanism> certificate = new HashSet<>();
}
