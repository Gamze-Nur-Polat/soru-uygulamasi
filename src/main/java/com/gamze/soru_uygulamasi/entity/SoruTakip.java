package com.gamze.soru_uygulamasi.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "soru_takip")
public class SoruTakip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate sonCozulmeTarihi = LocalDate.now();

    private boolean dogruMu; // True: Bildin, False: Bilemedin

    private int tekrarSayisi = 0; // Kaç kere üst üste bildin?

    private LocalDate birSonrakiTekrarTarihi;

    // Hangi sorunun takibi bu?
    @ManyToOne
    @JoinColumn(name = "soru_id")
    private Soru soru;
}