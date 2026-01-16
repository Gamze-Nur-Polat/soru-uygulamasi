package com.gamze.soru_uygulamasi.repository;

import com.gamze.soru_uygulamasi.entity.Soru;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SoruRepository extends JpaRepository<Soru, Long> {

    // Mevcut (Tüm sorulardan rastgele)
    @Query(value = "SELECT * FROM sorular ORDER BY RANDOM() LIMIT 1", nativeQuery = true)
    Soru rastgeleSoruGetir();

    // YENİ EKLENECEK KISIM: (Belirli bir konudan rastgele)
    @Query(value = "SELECT * FROM sorular WHERE konu_id = :konuId ORDER BY RANDOM() LIMIT 1", nativeQuery = true)
    Soru konuyaGoreRastgeleSoruGetir(@Param("konuId") Long konuId);
}