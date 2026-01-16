package com.gamze.soru_uygulamasi.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "sorular")
public class Soru {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String soruMetni;

    @Column(nullable = false)
    private String dogruCevap; // Örn: "4"

    // YENİ EKLENEN ŞIKLAR
    private String secenekA;
    private String secenekB;
    private String secenekC;
    private String secenekD;

    private int zorlukSeviyesi = 1;

    private LocalDate olusturulmaTarihi = LocalDate.now();

    @ManyToOne
    @JoinColumn(name = "konu_id")
    private Konu konu;
}