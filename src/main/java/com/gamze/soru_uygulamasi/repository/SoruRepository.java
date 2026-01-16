package com.gamze.soru_uygulamasi.repository;

import com.gamze.soru_uygulamasi.entity.Soru;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SoruRepository extends JpaRepository<Soru, Long> {

    // BU KISMI EKLE:
    // Veritabanına "Rastgele sırala ve en üstteki 1 taneyi ver" diyoruz.
    @Query(value = "SELECT * FROM sorular ORDER BY RANDOM() LIMIT 1", nativeQuery = true)
    Soru rastgeleSoruGetir();
}