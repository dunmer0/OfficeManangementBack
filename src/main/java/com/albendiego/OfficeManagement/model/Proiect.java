package com.albendiego.OfficeManagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "proiecte")
public class Proiect {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;
    @Column(unique = true)
    private String numar;
    private String titlu;
    private String descriere;
    private String adresa;
    private String tip;
    @ManyToOne
    @JoinColumn(name = "certificat_id")
    private CertificatUrbanism certificat;


    @OneToMany(mappedBy = "proiect")
    private Set<Aviz> avize = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "beneficar_id")
    private Beneficiar beneficiar;

}
