package com.gamze.soru_uygulamasi.repository;

import com.gamze.soru_uygulamasi.entity.Konu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KonuRepository extends JpaRepository<Konu, Long> {
    // Buraya kod yazmamıza gerek yok.
    // JpaRepository sayesinde Spring Boot; kaydetme, silme, bulma
    // gibi tüm işlemleri bizim yerimize arka planda halledecek.
}