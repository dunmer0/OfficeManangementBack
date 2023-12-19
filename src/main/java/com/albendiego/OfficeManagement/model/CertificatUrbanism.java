package com.albendiego.OfficeManagement.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CertificatUrbanism implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false, name = "id")
    private Long id;
    private String numar;
    private String adresa;
    private String descriere;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private LocalDate dataEliberare;
    private int valabilitate;
    private int verificat = 0;
    private boolean isValid;
    @OneToMany(mappedBy = "certificat")
    private Set<Proiect> proiecte = new HashSet<>();
    @ManyToOne
    @JoinColumn(name = "beneficiar_id")
    private Beneficiar beneficiar;
    @OneToMany(mappedBy = "certificatUrbanism")
    private Set<FisierCertificat> fisiereCertificat;



}
