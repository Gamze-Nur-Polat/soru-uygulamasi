package com.gamze.soru_uygulamasi;

import com.gamze.soru_uygulamasi.entity.Konu;
import com.gamze.soru_uygulamasi.entity.Soru;
import com.gamze.soru_uygulamasi.repository.KonuRepository;
import com.gamze.soru_uygulamasi.repository.SoruRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class VeriYukleyici implements CommandLineRunner {

    private final SoruRepository soruRepo;
    private final KonuRepository konuRepo;

    @Override
    public void run(String... args) throws Exception {
        soruRepo.deleteAll();
        konuRepo.deleteAll();

        // 1. KONULARI OLUŞTUR
        Konu konuAnayasa = konuEkle("T.C. Anayasası", "1982 Anayasası ve ilgili mevzuat");
        Konu konu657 = konuEkle("657 Sayılı DMK", "Devlet Memurları Kanunu");
        Konu konuTarih = konuEkle("İnkılap Tarihi", "Atatürk İlkeleri ve İnkılap Tarihi");

        // 2. SORULARI EKLE
        List<Soru> sorular = Arrays.asList(
                // ANAYASA SORULARI
                soruOlustur("Yasama yetkisi kime aittir?", "Cumhurbaşkanı", "TBMM", "Mahkemeler", "Halk", "TBMM", konuAnayasa),
                soruOlustur("Cumhurbaşkanı seçilme yaşı kaçtır?", "30", "35", "40", "45", "40", konuAnayasa),

                // 657 DMK SORULARI
                soruOlustur("Memurluktan çıkarma cezasını kim verir?", "Amir", "Vali", "Yüksek Disiplin Kurulu", "Müdür", "Yüksek Disiplin Kurulu", konu657),
                soruOlustur("Hangisi memura verilen bir ceza değildir?", "Uyarma", "Kınama", "Sürgün", "Aylıktan Kesme", "Sürgün", konu657),

                // TARİH SORULARI
                soruOlustur("Hatay hangi yıl anavatana katıldı?", "1938", "1939", "1940", "1941", "1939", konuTarih)
        );

        soruRepo.saveAll(sorular);
        System.out.println("✅ Konular ve Sorular Yüklendi!");
    }

    private Konu konuEkle(String isim, String aciklama) {
        Konu k = new Konu();
        k.setIsim(isim);
        k.setAciklama(aciklama);
        return konuRepo.save(k);
    }

    private Soru soruOlustur(String metin, String a, String b, String c, String d, String cevap, Konu konu) {
        Soru s = new Soru();
        s.setSoruMetni(metin);
        s.setSecenekA(a); s.setSecenekB(b); s.setSecenekC(c); s.setSecenekD(d);
        s.setDogruCevap(cevap);
        s.setKonu(konu);
        return s;
    }
}