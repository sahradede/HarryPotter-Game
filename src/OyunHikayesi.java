import java.util.Scanner;
import java.util.Random;
import java.time.LocalDateTime;
import java.time.Duration;
import java.util.List;

public class OyunHikayesi {
    final Scanner scanner = new Scanner(System.in);
    private int gunNo; //oyundaki mecvut gün
    private Karakter karakter;
    private List<String> dersProgrami;
    private LocalDateTime oyunBaslangicZamani;
    //fluffy diğer karakterlerden farklı olarak sadece 1 metotta kullanılıyor ve parametreleri değişmiyor bu yüzden burda tanımlandı
    private Fluffy fluffy = new Fluffy();

    //kurucu metot
    public OyunHikayesi(int gunNo, Karakter karakter, List<String> dersProgrami) {
        this.gunNo = gunNo;
        this.karakter = karakter;
        this.dersProgrami = dersProgrami;
        this.oyunBaslangicZamani = LocalDateTime.now();
    }

    //oyundaki gün yapısının kurulduğu metot, ilk 4 oyun günü için geçerli
    public void oyunuBaslat() {
        System.out.println(gunNo + ".gün " + " başladı!");
        for (String ders : dersProgrami) {
            boolean oyunDevam = true; //döngünün devam durumunu kontrol etmek için
            while (oyunDevam) {
                try {
                    System.out.println("\nSıradaki ders: " + ders);
                    System.out.println("Bir seçenek belirleyin:");
                    System.out.println("1- Derse git");
                    System.out.println("2- Ormana git(Hagrid ile)");
                    System.out.println("3- Ormana git(Yalnız)");
                    System.out.println("4- 3. katı keşfet");
                    System.out.println("5- Hagrid'den ipucu al");
                    System.out.print("Seçiminiz: ");

                    int secim = scanner.nextInt();
                    scanner.nextLine();

                    switch (secim) {
                        case 1:
                            derseGit(ders);
                            oyunDevam = false;
                            break;
                        case 2:
                            ormanaGit("Hagrid ile"); // Hagrid ile git
                            oyunDevam = false;
                            break;
                        case 3:
                            ormanaGit(); // Yalnız git
                            oyunDevam = false;
                            break;
                        case 4:
                            kat3Kesif();
                            oyunDevam = false;
                            break;
                        case 5:
                            ipUclari();
                            break;
                        default:
                            System.out.println("Geçersiz seçim, tekrar deneyin.");
                    }
                } catch (Exception e) {
                    System.out.println("Hatalı giriş yaptınız, lütfen geçerli bir sayı girin.");
                    scanner.nextLine();
                }
            }
        }
        System.out.println(gunNo + ". Gün bitti!");
        gunNo++;

        if (gunNo == 5) {
            voldemortSavasi();
        }
    }

    //oyun süresini hesaplayan metot
    private String oyunSuresiniHesapla() {
        LocalDateTime oyunBitisZamani = LocalDateTime.now();
        Duration oyunSuresi = Duration.between(oyunBaslangicZamani, oyunBitisZamani);
        long dakika = oyunSuresi.toMinutes();
        long saniye = oyunSuresi.minusMinutes(dakika).getSeconds();
        return dakika + " dakika " + saniye + " saniye.";
    }
    //dosyaya kaydetme kısmında kullanılacak metot
    private void oyunSuresiniGoster()
    {
        System.out.println("Oynama süresi: " + oyunSuresiniHesapla());
    }

    //oyun boyunca kritik yerlerde oyuncunun sağlık değişkenini kontrol edecek
    // ve sağlık sıfır ve altında bir değere düştüğünde oyun sona erecek
    private void saglikKontrol() {
        if (karakter.getSaglik() <= 0) {
            System.out.println("Sağlığınız tükendi! Oyun sona erdi.");
            oyunSuresiniGoster();
            OyunKaydet.kaydet(karakter);
            System.exit(0);
        }
    }
    //oyuncun gittiği dersle ilgili yeteneğini artıracak metot
    private void derseGit(String ders) {
        System.out.println("Derse katıldınız: " + ders);
        if (ders.contains("Büyü")) {
            karakter.setBuyuculuk(karakter.getBuyuculuk() + 10);
        } else if (ders.contains("Savunma")) {
            karakter.setSavunma(karakter.getSavunma() + 10);
        } else if (ders.contains("İksir")) {
            karakter.setZeka(karakter.getZeka() + 10);
        }
    }


    // bu  metotta, oyuncu yetenekleri bir seviyenin üstündeyse görevi başarılı geçiyor ve ödülllendiriliyor
    private void ormanaGit() {
        System.out.println("Yalnız ormana gittiniz ve bir canavarla karşılaştınız!");
        if (karakter.getBuyuculuk() > 30 || karakter.getZeka() > 30) {
            System.out.println("Canavarı mağlup ettiniz ve tüm yeteneklerinize +10 puan kazandınız!");
            karakter.setBuyuculuk(karakter.getBuyuculuk() + 10);
            karakter.setSavunma(karakter.getSavunma() + 10);
            karakter.setZeka(karakter.getZeka() + 10);
        } else {
            System.out.println("Canavar sizi yendi. Sağlığınız büyük ölçüde azaldı.");
            karakter.setSaglik(karakter.getSaglik() - 30);
            saglikKontrol();
        }
    }

    // bu metotta methodoverloading kullanıldıi ormanaGit metotu benzer işlevli
    private void ormanaGit(String hagridleGit) {
        System.out.println("Hagrid ile ormana gittiniz. Karşınıza bir canavar çıktı!");
        if (karakter.getSavunma() >= 40) {
            System.out.println("Savunmanız yeterli olduğu için canavardan zarar görmeden kaçtınız.");
        } else {
            System.out.println("Hagrid ile birlikte olsanız da canavarın gücünden etkilendiniz.");
            karakter.setSaglik(karakter.getSaglik() - 20);
            System.out.println("Sağlığınız 20 puan azaldı ama kaçmayı başardınız.");
            saglikKontrol();
        }
    }
 //oyuncu 3 farklı seçenekten biriyle rastgele karşılaşıyor
    private void kat3Kesif() {
        Random random = new Random();
        int olay = random.nextInt(10);
        //ilk ihtimalde oyuncu yetenek seviyesine göre başarılı/başarısız oluyor
        if (olay < 4) {
            System.out.println("Fluffy ile karşılaştın!");
            if(karakter.getBuyuculuk() + karakter.getSavunma() >= fluffy.getZeka()+ fluffy.getSavunma() ) {
                System.out.println("Fluffy'den daha güçlüymüşsün. Onu alt etmeyi başardın!");
            }
            else {
                System.out.println("Fluffy'e karşı koyamadın.");
                oyunSuresiniGoster();
                OyunKaydet.kaydet(karakter);
                System.exit(0);
            }

            //2. ihtimal (+)
        } else if (olay == 4 || olay == 5 || olay == 6) {
            System.out.println("'Gerçeklik Aynası' ile karşılaştın ve aileni özledin. Bu olay zekanı azaltan bir bunalıma yol açtı.");
            karakter.setZeka(karakter.getZeka() - 10);
        } else { //3. ihtimal (-)
            System.out.println("Gizemli bir keşifte bulundunuz ve büyücülük gücünüz arttı!");
            karakter.setBuyuculuk(karakter.getBuyuculuk() + 10);
        }
    }

    //oyuncuya oyun işleyişi hakkında verilen ipuçları , metot
    private void ipUclari() {
        System.out.println("                      ⁺˚*･༓☾　　☽༓･*˚⁺");
        System.out.println("İyi bir öğrenci olup derse gitmeyi tercih edersen o dersle alakalı yeteneklerin artar.");
        System.out.println("*Ormanda bir canavar var ama onu tanıyorum, belki onu yenmene yardım edebilirim.");
        System.out.println("*Tek başına ormana gitmek tehlikeli ama seni güzel şeyler de bekliyor olabilir.");
        System.out.println("*3. katta seni riskli ama heyecan verici şeyler bekliyor.");
        System.out.println("                      ⁺˚*･༓☾　　☽༓･*˚⁺");
    }

    //5. oyun gününde gerçekleşecek fonksiyon
    private void voldemortSavasi() {
        System.out.println("\nBüyük gündesin. Voldemort sana saldırıyor!");
        System.out.println("...");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣤⠶⠟⠛⠛⠛⠶⢦⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⢠⡾⠋⠀⠀⠀⠀⠀⠀⠀⠀⠉⢷⡀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⢠⣿⠁⠀⠀⡀⠀⠀⠀⠀⠀⠀⠀⣾⣷⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⣾⠟⢉⣀⣀⡈⠃⠀⠀⠒⣉⣀⡀⠈⢻⡄⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⡏⠀⣴⣶⣿⣿⣷⠂⠀⣾⣿⣿⣿⣆⢠⡇⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⢸⣿⠀⠻⠿⢿⡿⠃⣰⣆⠙⣿⡿⠿⠋⠸⣧⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⣿⠁⠀⠀⠀⠈⠀⠠⣿⠿⠀⠀⣀⣀⣀⠀⣿⣦⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⣼⣿⣶⣾⣿⣿⡟⢀⠀⠀⠀⢀⢀⢻⣿⣿⣿⣿⢻⡇⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⣇⢸⡇⠻⢿⣿⠇⡜⢸⠀⡇⢸⠘⣼⣿⠿⠉⠙⣿⡇⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⣿⣾⣿⣦⠀⣿⣷⣷⣾⣤⣷⣾⣿⣿⢋⣴⣶⣶⣿⡀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⣠⠞⠙⠻⣿⣿⣷⣿⣿⣿⠿⠿⠻⠟⠋⠀⣼⣿⣿⡿⣻⣿⣦⡀⠀⠀⠀\n" +
                "⠀⢀⡼⢿⡦⣠⣴⢿⣿⣿⣿⣏⣿⣤⠀⠀⢰⡔⣶⣾⣿⠏⠀⠀⠀⣿⣿⣄⠀⠀\n" +
                "⠀⡾⢡⣏⢰⣯⠃⣼⣿⡿⢿⡟⢿⡋⠀⠀⢸⣽⣿⣿⡿⣇⠀⠀⠀⢈⡿⡿⣆⠀\n" +
                "⢸⡿⠿⠏⢰⠇⢸⡿⠋⠀⠀⣿⡟⠀⠀⠀⠈⡟⠀⠉⠳⣿⣿⠆⠀⢿⣙⡇⣿⡄\n" +
                "⣿⠀⠀⠀⢸⢰⣿⠁⠀⠀⠀⢸⣧⣤⠀⠀⡼⡇⠀⠀⠀⢹⣾⠀⠀⠈⢻⡇⢹⡇\n" +
                "⣿⣿⣷⠀⢸⣸⣿⠀⠀⠀⠀⠀⢷⣿⠀⠀⠳⡇⠀⠀⠀⢸⡟⠀⠀⠀⡸⠀⢼⡇\n" +
                "⢿⣟⠋⠀⢸⡿⣿⠀⠀⠀⠀⠀⠘⣿⢲⣦⠀⢹⡀⠀⠀⡼⠀⣤⣤⣴⠃⢠⣾⠇\n" +
                "⠸⡏⣿⠆⠘⢷⣼⣷⣄⠀⠀⠀⠀⠹⣿⡇⠀⠘⢵⣤⡾⢁⡤⣡⠞⠁⠀⣸⠟⠀\n" +
                "⠀⠹⣧⠀⢀⣀⠀⠸⣯⣽⣷⣦⣄⡀⢻⣷⣦⣄⣤⠙⣷⡼⠞⠁⣀⢄⣾⠏⠀⠀\n" +
                "⠀⠀⠈⠻⣟⠙⢧⣀⣀⠀⠘⢳⣾⣿⣿⣿⣿⣮⡻⣤⡌⠛⢶⣵⣵⡿⠁⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠈⠳⢶⣤⣿⣦⠖⡉⠕⠊⢉⣿⣿⣿⣷⣾⣧⣖⣦⠙⢿⣄⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⣸⠟⠠⠈⠀⢀⣰⣿⣿⣿⣿⣿⣟⢿⣿⣿⣠⡴⢮⢳⣄⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⣼⡫⠂⠀⠀⠀⣶⡿⣿⣿⣿⠁⠘⢿⣆⠙⣿⣿⣅⢺⡇⠛⢷⡄\n" +
                "⠀⠀⠀⠀⠀⢰⣿⠁⠀⠀⠀⣼⠏⠀⠈⢿⣿⠀⠀⢀⣿⠀⢸⣿⢿⣿⣷⢺⡆⣿\n" +
                "⠀⠀⠀⠀⠀⢸⡇⠀⠀⠀⢰⣿⠀⠀⠀⠈⣏⢻⡇⢷⣾⡇⢸⣿⠃⠘⢿⣼⣧⣿\n" +
                "⠀⠀⠀⠀⠀⢸⣇⠀⠀⠀⢉⢿⣆⠀⠀⠀⣿⡟⠀⠀⢹⣷⣿⡏⠀⠀⢽⣿⣿⠏\n" +
                "⠀⠀⠀⠀⠀⠀⢻⡆⠀⠀⠀⠸⢻⢿⣶⣶⣿⢻⡆⢀⣼⣿⠏⠀⠀⣠⣹⣿⠋⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠻⣷⣀⣀⠀⠈⠈⠘⣿⡇⡿⠃⣸⢸⣿⢀⣠⣦⡿⠛⠁⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⠳⠶⣤⣴⣾⣿⣠⡇⠐⣇⣿⣿⠿⠛⠉⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢉⣿⠁⠀⠀⣹⡏⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣼⣧⡀⠀⣀⡿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣰⢧⡟⠁⣾⢻⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⣿⠞⠃⢠⣆⡏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣾⠃⠀⠀⠀⡿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣸⣿⡄⠀⡀⣸⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡏⣼⠋⣸⢹⡟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣸⣷⠿⠀⢫⣾⠁⠀⠀⠀⡠⠚⢉⣉⠓⣦⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⠀⠀⠀⢰⡟⠀⠀⢀⣼⢱⣿⣾⣿⣷⣼⡄⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣿⣆⠀⢠⣾⠇⠀⣼⡥⢃⣾⣿⣿⣿⡟⠈⠻⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠸⡇⡿⠂⢸⣸⠀⢰⣏⠔⠛⠛⢻⣿⣿⣧⢠⡄⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣷⡇⠀⠘⢿⡆⠸⣇⠀⠀⠀⠀⠈⠉⠉⣩⠇⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢹⣷⣦⡄⠸⣷⡀⢹⣷⠄⠀⠀⢠⣤⠾⠃⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢻⡍⠳⠤⠾⠿⠛⠁⠀⠀⣨⡿⠁⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠻⣄⠀⠀⠀⠀⢀⠀⢤⡾⠁⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠓⠦⣴⣴⣶⠶⠟⠁⠀⠀⠀⠀⠀⠀⠀⠀\n\n");


        //oyuncunun voldemortu yenmesi için gerekli koşullar
        if (karakter.getBuyuculuk() + karakter.getSavunma() + karakter.getZeka() >= 300) {
            ((voldemortOzelHamle) karakter).voldemortOzelHamle();
            oyunSuresiniGoster();
            OyunKaydet.kaydet(karakter);
            System.exit(0);
        }
        //oyuncu gerekli koşulları sağlayamazsa oyunu kaybediyor
        else {
            System.out.println("Voldemort'u yenemediniz... Daha güçlü olmanız gerekiyordu. Oyun sona erdi.");
            oyunSuresiniGoster();
            OyunKaydet.kaydet(karakter);
            System.exit(0);

        }
    }
}
