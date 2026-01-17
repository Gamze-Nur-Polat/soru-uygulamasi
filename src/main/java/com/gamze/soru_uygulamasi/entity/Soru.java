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

    // DEĞİŞİKLİK BURADA: length = 2000 yaptık (Sığmayan sorular için)
    @Column(nullable = false, length = 2000)
    private String soruMetni;

    @Column(nullable = false)
    private String dogruCevap;

    // Şıklar da bazen uzun olabilir, onları da 500 yaptık garanti olsun
    @Column(length = 500)
    private String secenekA;

    @Column(length = 500)
    private String secenekB;

    @Column(length = 500)
    private String secenekC;

    @Column(length = 500)
    private String secenekD;

    private int zorlukSeviyesi = 1;

    private LocalDate olusturulmaTarihi = LocalDate.now();

    @ManyToOne
    @JoinColumn(name = "konu_id")
    private Konu konu;
}