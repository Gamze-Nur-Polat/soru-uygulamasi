package com.gamze.soru_uygulamasi.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data // Bu, bizim yerimize getter/setter kodlarını yazar (Lombok).
@Entity // Bu, veritabanında bir tablo olduğunu söyler.
@Table(name = "konular") // Veritabanındaki "konular" tablosuna bağlanır.
public class Konu {

    @Id // Bu sütunun kimlik (ID) olduğunu belirtir.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID'yi otomatik 1, 2, 3 diye artırır.
    private Long id;

    private String isim; // Konu adı (Örn: Matematik)

    private String aciklama; // (Örn: 9. Sınıf Mat Konuları)
}