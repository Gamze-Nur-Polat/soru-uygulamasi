package com.gamze.soru_uygulamasi.controller;

import com.gamze.soru_uygulamasi.entity.Konu;
import com.gamze.soru_uygulamasi.entity.Soru;
import com.gamze.soru_uygulamasi.repository.KonuRepository; // <-- EKLENDİ
import com.gamze.soru_uygulamasi.repository.SoruRepository;
import com.gamze.soru_uygulamasi.service.SoruService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sorular")
@RequiredArgsConstructor
public class SoruController {

    private final SoruRepository soruRepo;
    private final KonuRepository konuRepo; // <-- 1. BU SATIRI EKLE (Konulara erişmek için)
    private final SoruService soruService;

    @GetMapping
    public List<Soru> tumSorulariGetir() {
        return soruRepo.findAll();
    }

    // --- GÜNCELLENEN KISIM BURASI ---
    @PostMapping
    public Soru soruEkle(@RequestBody Soru yeniSoru) {
        // HTML'den gelen konu ID'sine güvenme!
        // Veritabanındaki ilk konuyu (Genel Kültür) bul ve bu soruya ata.
        Konu mevcutKonu = konuRepo.findAll().get(0);
        yeniSoru.setKonu(mevcutKonu);

        return soruRepo.save(yeniSoru);
    }
    // --------------------------------

    @GetMapping("/cevapla")
    public String soruyuCevapla(@RequestParam Long id, @RequestParam boolean dogruMu) {
        soruService.cevabiDegerlendir(id, dogruMu);

        if(dogruMu) {
            return "Tebrikler! Cevabın doğru, tekrar süresi uzatıldı.";
        } else {
            return "Maalesef yanlış. Bu soruyu yarın tekrar soracağım.";
        }
    }

    @GetMapping("/siradaki")
    public Soru siradakiSoruyuGetir() {
        return soruRepo.rastgeleSoruGetir();
    }
}