import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Karakter {
    //karakterin özelliklerine ait değişkenler
    final String isim;
    private int zeka;
    private int savunma;
    private int buyuculuk;
    private int saglik;
    private String ev;

    //karakterin temel özelliklerini atayan kurucu metot
    public Karakter(String isim, int zeka, int savunma, int buyuculuk, int saglik) {
        this.isim = isim;
        this.zeka = zeka;
        this.savunma = savunma;
        this.buyuculuk = buyuculuk;
        this.saglik = saglik;
    }

    //karaktere ait özelliklere ulaşabilme ve bu özellikleri güncelleme imkanı tanıyan getter setter metotları
    public String getIsim() {
        return isim;
    }
    public int getZeka() {
        return zeka;
    }

    public void setZeka(int zeka) {
        this.zeka = zeka;
    }

    public int getBuyuculuk() {
        return buyuculuk;
    }

    public void setBuyuculuk(int buyuculuk) {
        this.buyuculuk = buyuculuk;
    }

    public int getSaglik() {
        return saglik;
    }

    public void setSaglik(int saglik) {
        this.saglik = saglik;
    }
    public int getSavunma() {
        return savunma;
    }

    public void setSavunma(int savunma) {
        this.savunma = savunma;
    }
    public String getEv() {
        return ev;
    }

    public void setEv(String ev) {
        this.ev = ev;
    }
    //her alt sınıfta bu metot seçilen karakterin özelliklerine göre yeniden tanımlanacak
    public abstract void karakterAvantaji(); //polimorfizm

    // karakterin son durumunu konsola yazdıran, her oyun gününden sonra ara ekranda oyuncuya bilgileri gösterebilecek fonksiyon
    public void karakterDurumu() {
        System.out.println("\nKarakter Durumu:");
        System.out.println("----------------");
        System.out.println("İsim: " + isim);
        System.out.println("Zeka: " + zeka);
        System.out.println("Savunma: " + savunma);
        System.out.println("Büyücülük: " + buyuculuk);
        System.out.println("Sağlık: " + saglik);
        System.out.println("Ev: "+  ev);
        tarihGoster();
    }

    //güncel saati ve tarihi gösteren metot
    public String tarihGoster() {
        LocalDateTime simdikiZaman = LocalDateTime.now();
        DateTimeFormatter formatlayici = DateTimeFormatter.ofPattern("dd-MM-yyyy, HH:mm:ss");
        String formatliTarih = simdikiZaman.format(formatlayici);
        System.out.println("Tarih ve saat: " + formatliTarih);
        return formatliTarih;
    }


}
