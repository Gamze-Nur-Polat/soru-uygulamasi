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
        // 1. ÖNCE ESKİ VERİLERİ TEMİZLE (Çakışma olmasın)
        soruRepo.deleteAll();
        konuRepo.deleteAll();

        // 2. KONUYU OLUŞTUR
        Konu genelKultur = new Konu();
        genelKultur.setIsim("Genel Kültür");
        genelKultur.setAciklama("KPSS ve Görevde Yükselme Soruları");
        konuRepo.save(genelKultur);

        // 3. SORULARI HAZIRLA
        List<Soru> sorular = Arrays.asList(
                soruOlustur("1982 Anayasasına göre 'Yasama Yetkisi' kime aittir?", "Cumhurbaşkanı", "TBMM", "Anayasa Mahkemesi", "Bakanlar Kurulu", "TBMM", genelKultur),
                soruOlustur("657 sayılı kanuna göre hangi ceza için yargı yolu kapalıdır?", "Uyarma", "Kınama", "Aylıktan Kesme", "Hiçbiri", "Hiçbiri", genelKultur),
                soruOlustur("Resmi yazışmalarda 'Tarih' nereye yazılır?", "Sayı sağ yanına", "Konu altına", "İmza üstüne", "Sayfa sonuna", "Sayı sağ yanına", genelKultur),
                soruOlustur("Bilgi Edinme Kanununa göre cevap süresi kaç gündür?", "7", "10", "15", "30", "15", genelKultur),
                soruOlustur("Hangisi İdari Yargı mercisidir?", "Asliye Hukuk", "Danıştay", "Yargıtay", "Sulh Ceza", "Danıştay", genelKultur),
                soruOlustur("Cumhurbaşkanı seçilme yaşı kaçtır?", "30", "35", "40", "45", "40", genelKultur),
                soruOlustur("Hatay hangi yıl Türkiye'ye katılmıştır?", "1936", "1938", "1939", "1940", "1939", genelKultur),
                soruOlustur("Göbeklitepe hangi ilimizdedir?", "Gaziantep", "Mardin", "Şanlıurfa", "Diyarbakır", "Şanlıurfa", genelKultur),
                soruOlustur("Word'de geri alma kısayolu hangisidir?", "CTRL+C", "CTRL+V", "CTRL+Z", "CTRL+X", "CTRL+Z", genelKultur),
                soruOlustur("İstiklal Marşı nerede yazılmıştır?", "Taceddin Dergahı", "Ayasofya", "Mevlana Müzesi", "Ulu Cami", "Taceddin Dergahı", genelKultur)
        );

        // 4. HEPSİNİ KAYDET
        soruRepo.saveAll(sorular);

        System.out.println("------------------------------------------------");
        System.out.println("✅ MÜJDE: 10 Adet Soru Başarıyla Veritabanına Yüklendi!");
        System.out.println("------------------------------------------------");
    }

    // Soru oluşturmayı kolaylaştıran yardımcı metod
    private Soru soruOlustur(String metin, String a, String b, String c, String d, String cevap, Konu konu) {
        Soru soru = new Soru();
        soru.setSoruMetni(metin);
        soru.setSecenekA(a);
        soru.setSecenekB(b);
        soru.setSecenekC(c);
        soru.setSecenekD(d);
        soru.setDogruCevap(cevap);
        soru.setKonu(konu);
        return soru;
    }
}