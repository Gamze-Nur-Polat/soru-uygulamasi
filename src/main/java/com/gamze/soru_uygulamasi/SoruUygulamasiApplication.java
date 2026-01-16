package com.gamze.soru_uygulamasi;

import com.gamze.soru_uygulamasi.entity.Konu;
import com.gamze.soru_uygulamasi.entity.Soru;
import com.gamze.soru_uygulamasi.repository.KonuRepository;
import com.gamze.soru_uygulamasi.repository.SoruRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SoruUygulamasiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SoruUygulamasiApplication.class, args);
	}

	// Program çalışınca bu metod devreye girer ve örnek veri ekler
	@Bean
	CommandLineRunner baslangicVerisi(KonuRepository konuRepo,
									  SoruRepository soruRepo,
									  com.gamze.soru_uygulamasi.repository.SoruTakipRepository takipRepo,
									  com.gamze.soru_uygulamasi.service.SoruService soruService) {
		return args -> {
			// EĞER VERİTABANINDA KONU VARSA HİÇBİR ŞEY YAPMA (Verileri Korur)
			if (konuRepo.count() > 0) {
				System.out.println("Veritabanı zaten dolu, temizlik yapılmadı.");
				return;
			}

			// --- Veri yoksa aşağıdakileri ekle ---
			Konu genelKultur = new Konu();
			genelKultur.setIsim("Genel Kültür");
			konuRepo.save(genelKultur);

			// İlk açılış için 1 tane örnek soru olsun
			Soru soru1 = new Soru();
			soru1.setSoruMetni("Türkiye'nin başkenti neresidir?");
			soru1.setSecenekA("İstanbul");
			soru1.setSecenekB("Ankara");
			soru1.setSecenekC("İzmir");
			soru1.setSecenekD("Antalya");
			soru1.setDogruCevap("Ankara");
			soru1.setKonu(genelKultur);
			soruRepo.save(soru1);

			System.out.println("Başlangıç verisi eklendi!");
		};
	}
}