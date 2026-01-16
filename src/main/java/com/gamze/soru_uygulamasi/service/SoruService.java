package com.gamze.soru_uygulamasi.service;

import com.gamze.soru_uygulamasi.entity.Soru;
import com.gamze.soru_uygulamasi.entity.SoruTakip;
import com.gamze.soru_uygulamasi.repository.SoruTakipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service // Bu sınıfın bir "Servis" (İş yapan beyin) olduğunu belirtir
@RequiredArgsConstructor // Repository'leri otomatik bağlar
public class SoruService {

    private final SoruTakipRepository takipRepo;

    /**
     * Bu metod, kullanıcı bir soruyu cevapladığında çalışır.
     * Eğer DOĞRU bilirse -> Süreyi uzatır.
     * Eğer YANLIŞ bilirse -> Süreyi sıfırlar (Hemen tekrar sorar).
     */
    public void cevabiDegerlendir(Long soruId, boolean dogruMu) {
        // 1. Önce bu sorunun takip bilgisini veritabanından bulalım
        // (Eğer yoksa yeni bir takip kaydı oluştururuz)
        SoruTakip takip = takipRepo.findById(soruId).orElse(new SoruTakip());

        // Eğer yeni bir kayıtsa, hangi soruya ait olduğunu belirtelim
        if (takip.getSoru() == null) {
            Soru s = new Soru();
            s.setId(soruId);
            takip.setSoru(s);
        }

        takip.setSonCozulmeTarihi(LocalDate.now()); // Bugün çözüldü
        takip.setDogruMu(dogruMu); // Sonucu kaydet

        if (dogruMu) {
            // DOĞRU BİLDİ: Tekrar sayısını artır ve süreyi uzat
            int yeniTekrarSayisi = takip.getTekrarSayisi() + 1;
            takip.setTekrarSayisi(yeniTekrarSayisi);

            // Algoritma: Tekrar sayısı * 3 gün sonraya at (Örnek: 1. kezde 3 gün, 2. kezde 6 gün sonra)
            takip.setBirSonrakiTekrarTarihi(LocalDate.now().plusDays(yeniTekrarSayisi * 3L));

            System.out.println("Tebrikler! Bu soru " + takip.getBirSonrakiTekrarTarihi() + " tarihinde tekrar sorulacak.");
        } else {
            // YANLIŞ BİLDİ: Sayacı sıfırla ve YARIN tekrar sor
            takip.setTekrarSayisi(0);
            takip.setBirSonrakiTekrarTarihi(LocalDate.now().plusDays(1));

            System.out.println("Yanlış cevap! Bu soru YARIN tekrar karşına çıkacak.");
        }

        // Son durumu veritabanına kaydet
        takipRepo.save(takip);
    }
}