package com.albendiego.OfficeManagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "fisiere_certificat")
public class FisierCertificat {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id", nullable = false, updatable = false)
   private Long id;
   private String nume;
   private String tip;
   private String filePath;

   @ManyToOne
   @JoinColumn(name = "certificat_id")
   private CertificatUrbanism certificatUrbanism;
}

