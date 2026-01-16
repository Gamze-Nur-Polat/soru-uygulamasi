package com.gamze.soru_uygulamasi.controller;

import com.gamze.soru_uygulamasi.entity.Konu;
import com.gamze.soru_uygulamasi.entity.Soru;
import com.gamze.soru_uygulamasi.repository.KonuRepository;
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
    private final KonuRepository konuRepo;
    private final SoruService soruService;

    // 1. TÜM KONULARI LİSTELE (Ana menü için)
    @GetMapping("/konular")
    public List<Konu> tumKonulariGetir() {
        return konuRepo.findAll();
    }

    // 2. SEÇİLEN KONUDAN SORU GETİR
    @GetMapping("/konu/{konuId}/siradaki")
    public Soru konuyaGoreSoruGetir(@PathVariable Long konuId) {
        return soruRepo.konuyaGoreRastgeleSoruGetir(konuId);
    }

    // Cevaplama kısmı aynı kalıyor
    @GetMapping("/cevapla")
    public String soruyuCevapla(@RequestParam Long id, @RequestParam boolean dogruMu) {
        soruService.cevabiDegerlendir(id, dogruMu);
        if(dogruMu) return "Tebrikler! Doğru cevap.";
        else return "Maalesef yanlış.";
    }
}