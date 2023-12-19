package com.albendiego.OfficeManagement.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record CertificatUrbanismDTO(long id,
                                    String numar,
                                    String beneficiar,
                                    String adresa,
                                    String descriere,
                                    LocalDate dataEliberare,
                                    LocalDate dataExpirare,
                                    int valabilitate) {
}
