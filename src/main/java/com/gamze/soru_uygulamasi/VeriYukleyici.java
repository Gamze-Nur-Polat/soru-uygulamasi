package com.gamze.soru_uygulamasi;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gamze.soru_uygulamasi.entity.Konu;
import com.gamze.soru_uygulamasi.entity.Soru;
import com.gamze.soru_uygulamasi.repository.KonuRepository;
import com.gamze.soru_uygulamasi.repository.SoruRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class VeriYukleyici implements CommandLineRunner {

    private final SoruRepository soruRepo;
    private final KonuRepository konuRepo;

    @Override
    public void run(String... args) throws Exception {
        // 1. Temizlik
        soruRepo.deleteAll();
        konuRepo.deleteAll();
        System.out.println("🧹 Eski veriler temizlendi.");

        // 2. JSON Dosyasını Oku
        ObjectMapper mapper = new ObjectMapper();
        InputStream inputStream = TypeReference.class.getResourceAsStream("/sorular.json");

        if (inputStream == null) {
            System.out.println("❌ HATA: sorular.json dosyası bulunamadı!");
            return;
        }

        // JSON'ı Listeye Çevir
        List<SoruJson> gelenSorular = mapper.readValue(inputStream, new TypeReference<List<SoruJson>>() {});
        System.out.println("📦 JSON'dan " + gelenSorular.size() + " adet soru okundu.");

        // 3. Veritabanına Kaydet
        Map<String, Konu> konuHafizasi = new HashMap<>();

        for (SoruJson veri : gelenSorular) {
            // Konu var mı kontrol et, yoksa oluştur
            Konu konu = konuHafizasi.get(veri.getKonuAdi());
            if (konu == null) {
                // Veritabanından da bir soralım (garanti olsun)
                Konu finalKonu = konu;
                konu = konuRepo.findAll().stream()
                        .filter(k -> k.getIsim().equals(veri.getKonuAdi()))
                        .findFirst()
                        .orElseGet(() -> {
                            Konu yeniK = new Konu();
                            yeniK.setIsim(veri.getKonuAdi());
                            yeniK.setAciklama(veri.getKonuAdi() + " ile ilgili sorular");
                            return konuRepo.save(yeniK);
                        });
                konuHafizasi.put(veri.getKonuAdi(), konu);
            }

            // Soruyu Oluştur
            Soru soru = new Soru();
            soru.setSoruMetni(veri.getSoruMetni());
            soru.setSecenekA(veri.getA());
            soru.setSecenekB(veri.getB());
            soru.setSecenekC(veri.getC());
            soru.setSecenekD(veri.getD());
            soru.setDogruCevap(veri.getCevap());
            soru.setKonu(konu);

            soruRepo.save(soru);
        }

        System.out.println("✅ MÜJDE: Tüm sorular başarıyla veritabanına yüklendi!");
    }

    // JSON verisini karşılayacak geçici sınıf
    @Data
    private static class SoruJson {
        private String konuAdi;
        private String soruMetni;
        private String a;
        private String b;
        private String c;
        private String d;
        private String cevap;
    }
}