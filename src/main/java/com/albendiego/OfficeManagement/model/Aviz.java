package com.albendiego.OfficeManagement.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "avize")
public class Aviz{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tip;
    private String numar;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private LocalDate dataDepunere;
    private Boolean eliberat = false;
    private int verificat = 0;
    private int perioadaRezolvare;
    @ManyToOne
    @JoinColumn(name = "proiect_id", nullable = false)
    private Proiect proiect;
    @ManyToOne
    @JoinColumn(name ="institutie_id")
    private Institutie institutie;


}
