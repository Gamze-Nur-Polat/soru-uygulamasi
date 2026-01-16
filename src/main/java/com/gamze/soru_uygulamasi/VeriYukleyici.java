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
        // Önce temizlik yapalım
        soruRepo.deleteAll();
        konuRepo.deleteAll();

        // --- 1. KONULARI OLUŞTURALIM ---
        Konu k1 = konuEkle("Anayasa - Genel Esaslar", "Devletin şekli, nitelikleri ve başlangıç ilkeleri");
        Konu k2 = konuEkle("Anayasa - Temel Hak ve Ödevler", "Kişi hakları, sosyal ve ekonomik haklar, siyasi haklar");
        Konu k3 = konuEkle("Devletin Temel Organları", "Yasama (TBMM), Yürütme (CB), Yargı organları");
        Konu k4 = konuEkle("İnkılap Tarihi", "Atatürk İlkeleri ve Türk İnkılap Tarihi");
        Konu k5 = konuEkle("657 Sayılı DMK", "Devlet Memurları Kanunu (Disiplin, İzinler, Haklar)");
        Konu k6 = konuEkle("Resmi Yazışma Kuralları", "Resmi Yazışmalarda Uygulanacak Usul ve Esaslar");
        Konu k7 = konuEkle("Türkçe Dil Bilgisi", "Yazım, noktalama ve anlatım bozuklukları");
        Konu k8 = konuEkle("Halkla İlişkiler ve Etik", "Etik Davranış İlkeleri ve Halkla İlişkiler");
        Konu k9 = konuEkle("Bilgi Edinme Hakkı", "4982 Sayılı Bilgi Edinme Hakkı Kanunu");

        // --- 2. SORULARI EKLEYELİM ---
        List<Soru> sorular = Arrays.asList(
                // 1. GENEL ESASLAR
                soruOlustur("1982 Anayasası'nın 1. maddesine göre Türkiye Devleti'nin yönetim şekli nedir?", "Monarşi", "Oligarşi", "Cumhuriyet", "Teokrasi", "Cumhuriyet", k1),
                soruOlustur("Anayasamızın değiştirilemeyecek hükümlerinden biri olan 'Başkent' neresidir?", "İstanbul", "Ankara", "Sivas", "İzmir", "Ankara", k1),

                // 2. TEMEL HAK VE ÖDEVLER
                soruOlustur("Aşağıdakilerden hangisi 'Siyasi Haklar ve Ödevler' bölümünde yer alır?", "Mülkiyet Hakkı", "Eğitim Hakkı", "Seçme ve Seçilme Hakkı", "Konut Dokunulmazlığı", "Seçme ve Seçilme Hakkı", k2),
                soruOlustur("Hiç kimse zorla çalıştırılamaz. Angarya yasaktır. Bu hangi hakkın kapsamındadır?", "Kişi Dokunulmazlığı", "Zorla Çalıştırma Yasağı", "Özel Hayatın Gizliliği", "Haberleşme Hürriyeti", "Zorla Çalıştırma Yasağı", k2),

                // 3. DEVLETİN TEMEL ORGANLARI
                soruOlustur("Türk Milleti adına 'Yasama' yetkisini kim kullanır?", "Cumhurbaşkanı", "Anayasa Mahkemesi", "TBMM", "Bakanlar", "TBMM", k3),
                soruOlustur("Yürütme yetkisi ve görevi kime aittir?", "TBMM Başkanı", "Cumhurbaşkanı", "Genelkurmay Başkanı", "Yargıtay", "Cumhurbaşkanı", k3),

                // 4. İNKILAP TARİHİ
                soruOlustur("Cumhuriyet hangi tarihte ilan edilmiştir?", "23 Nisan 1920", "19 Mayıs 1919", "29 Ekim 1923", "30 Ağustos 1922", "29 Ekim 1923", k4),
                soruOlustur("Aşağıdakilerden hangisi Atatürk İlkelerinden biridir?", "Mandacılık", "Turancılık", "Laiklik", "Osmanlıcılık", "Laiklik", k4),

                // 5. 657 SAYILI DMK (En Önemlisi)
                soruOlustur("657 sayılı Kanuna göre, memurluktan çıkarma cezasını kim verir?", "Disiplin Amiri", "Atamaya Yetkili Amir", "Yüksek Disiplin Kurulu", "Vali", "Yüksek Disiplin Kurulu", k5),
                soruOlustur("Memura, görevinde ve davranışlarında daha dikkatli olması gerektiğinin yazı ile bildirilmesine ne denir?", "Uyarma", "Kınama", "Aylıktan Kesme", "Kademe İlerlemesinin Durdurulması", "Uyarma", k5),
                soruOlustur("Devlet memurluğuna alınmada genel yaş şartı (alt sınır) kural olarak kaçtır?", "15", "18", "20", "25", "18", k5),

                // 6. RESMİ YAZIŞMA KURALLARI
                soruOlustur("Resmi yazılarda 'Tarih', sayı ile aynı hizada olmak üzere nereye yazılır?", "Sol üst köşeye", "Sağ üst köşeye", "Metnin sonuna", "İmza bloğunun altına", "Sağ üst köşeye", k6),
                soruOlustur("Resmi yazışmalarda 'İvedi' ibaresi hangi renkle yazılır?", "Siyah", "Mavi", "Kırmızı", "Yeşil", "Kırmızı", k6),

                // 7. TÜRKÇE DİL BİLGİSİ
                soruOlustur("Aşağıdaki cümlelerin hangisinde yazım yanlışı vardır?", "Ahmet Bey geldi.", "Herkez buradaydı.", "Akşam size geleceğiz.", "Kitabı masaya bıraktım.", "Herkez buradaydı.", k7),
                soruOlustur("Cümle bittikten sonra hangi noktalama işareti konur?", "Virgül", "Noktalı Virgül", "Nokta", "İki Nokta", "Nokta", k7),

                // 8. HALKLA İLİŞKİLER VE ETİK
                soruOlustur("Kamu görevlileri etik kurulu üyelerini kim seçer ve atar?", "TBMM", "Cumhurbaşkanı", "İçişleri Bakanı", "Yargıtay", "Cumhurbaşkanı", k8),
                soruOlustur("Kamu görevlisinin hediye alması neden yasaktır?", "Maaşı yettiği için", "Devletin itibarı ve tarafsızlığı için", "Hediye sevmediği için", "Vergisi olduğu için", "Devletin itibarı ve tarafsızlığı için", k8),

                // 9. BİLGİ EDİNME HAKKI (4982)
                soruOlustur("Kurumlar, bilgi edinme başvurularına kural olarak kaç iş günü içinde cevap vermelidir?", "7", "10", "15", "30", "15", k9),
                soruOlustur("Başvurunun reddedilmesi durumunda itiraz nereye yapılır?", "Mahkemeye", "Bilgi Edinme Değerlendirme Kuruluna", "TBMM'ye", "Valiliğe", "Bilgi Edinme Değerlendirme Kuruluna", k9)
        );

        soruRepo.saveAll(sorular);
        System.out.println("✅ TÜM SINAV KONULARI VE ÖRNEK SORULAR BAŞARIYLA YÜKLENDİ!");
    }

    // Yardımcı Metotlar
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