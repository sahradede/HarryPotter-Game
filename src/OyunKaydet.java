import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class OyunKaydet {
    //daha önce kaydedilmiş oyunları gösteren metot
    public static void eskiOyunlariGoster(Scanner scanner) {
        File dosya = new File("kayitlar"); //kayıtların tutulacağı dizin
        if (!dosya.exists()) {
            //eğer kayıt yoksa bunu kullanıcıya bildirecek
            System.out.println("Kayıt dizini bulunamadı,yeni kayıtlar oluşturulacak.");
            dosya.mkdir();
            return;
        }

        File[] kayitlar = dosya.listFiles(); //kayıtlı dosyaları listeleyecek
        if (kayitlar == null || kayitlar.length == 0) {
            //kayıtlı dosya yoksa bilgilendirme yapılacak
            System.out.println("Kayıtlı oyun bulunamadı.");
        } else {
            //mecvut oyun kayıtlarını yazdırma
            System.out.println("\nEski Oyun Kayıtları:");
            for (int i = 0; i < kayitlar.length; i++) {
                System.out.println((i + 1) + " - " + kayitlar[i].getName());
            }

            System.out.print("Görüntülemek istediğiniz oyun numarasını girin: ");
            int secim = scanner.nextInt();
            scanner.nextLine();

            if (secim > 0 && secim <= kayitlar.length) {
                //kullanıcı geçerli seçim yaptığı müddetçe seçilen dosya okuacak ve içeriği gösterecek
                File secilenDosya = kayitlar[secim - 1];
                System.out.println("Dosya Adı: " + secilenDosya.getName());
                try (BufferedReader okuyucu = new BufferedReader(new FileReader(secilenDosya))) {
                    String satir;
                    while ((satir = okuyucu.readLine()) != null) {
                        System.out.println(satir); //dosyadaki satırlar yazdırılacak
                    }
                }//dosya okunurken hata oluşursa:
                catch (IOException e) {
                    System.out.println("Dosya okunurken bir hata oluştu: " + secilenDosya.getName());
                }
            } else {
                System.out.println("Geçersiz seçim.");
            }
        }
    }
 //oyunu kaydeden metot
    public static void kaydet(Karakter karakter) {
        BufferedWriter yazici = null; //yazıcı nesnesi
        try {
            File dosya = new File("kayitlar");
            if (!dosya.exists() && !dosya.mkdir()) {
                System.out.println("Kayıt dizini oluşturulamadı!");
                return;
            }

            //kayıt dosyası isim oluşturma
            String dosyaAdi = "kayitlar/oyun-" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) + ".txt";
            File kayitDosya = new File(dosyaAdi);

            //kayıt dosyası yazma işlemleri
            System.out.println("Kayıt dosyası oluşturuluyor: " + dosyaAdi);
            yazici = new BufferedWriter(new FileWriter(kayitDosya));
            yazici.write("Karakter: " + karakter.getIsim());
            yazici.newLine();
            yazici.write("Sağlık: " + karakter.getSaglik());
            yazici.newLine();
            yazici.write("Büyücülük: " + karakter.getBuyuculuk());
            yazici.newLine();
            yazici.write("Savunma: " + karakter.getSavunma());
            yazici.newLine();
            yazici.write("Zeka: " + karakter.getZeka());
            yazici.newLine();
            yazici.write("Ev: " + karakter.getEv());
            yazici.newLine();
            yazici.write("Kaydedildiği Tarih: " + karakter.tarihGoster());
            yazici.newLine();

//kaydetme ve yazıcı kapatma sırasında oluşabilecek hatalara karşı:
        } catch (IOException e) {
            System.out.println("Oyun kaydedilirken bir hata oluştu.");
            e.printStackTrace();
        } finally {
            if (yazici != null) {
                try {
                    yazici.close();
                } catch (IOException e) {
                    System.out.println("Yazıcı kapatılırken bir hata oluştu.");
                    e.printStackTrace();
                }
            }
        }
    }
}
