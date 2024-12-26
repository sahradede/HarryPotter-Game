import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Karakter secilenKarakter = null; /* karakter sınıfından nesne oluşturuluyor.
        bu nesne kullanıcının ileride seçeceği karakteri öncelikli olarak null atıyor*/

        int a;
        do {
            System.out.println("       ___                             | '  \\\n" +
                    "   ___  \\ /  ___         ,'\\_           | .-. \\        /|\n" +
                    "   \\ /  | |,'__ \\  ,'\\_  |   \\          | | | |      ,' |_   /|\n" +
                    " _ | |  | |\\/  \\ \\ |   \\ | |\\_|    _    | |_| |   _ '-. .-',' |_   _\n" +
                    "// | |  | |____| | | |\\_|| |__    //    |     | ,'_`. | | '-. .-',' `. ,'\\_\n" +
                    "\\\\_| |_,' .-, _  | | |   | |\\ \\  //    .| |\\_/ | / \\ || |   | | / |\\  \\|   \\\n" +
                    " `-. .-'| |/ / | | | |   | | \\ \\//     |  |    | | | || |   | | | |_\\ || |\\_|\n" +
                    "   | |  | || \\_| | | |   /_\\  \\ /      | |`    | | | || |   | | | .---'| |\n" +
                    "   | |  | |\\___,_\\ /_\\ _      //       | |     | \\_/ || |   | | | |  /\\| |\n" +
                    "   /_\\  | |           //_____//       .||`      `._,' | |   | | \\ `-' /| |\n" +
                    "        /_\\           `------'        \\ |              `.\\  | |  `._,' /_\\\n" +
                    "                                       \\|                    `.\\");
            // oyunun ilk menüsü
            System.out.println("1- Eski oyunları görüntüle.");
            System.out.println("2- Yeni oyuna başla.");
            System.out.print("Seçiminiz: ");
            a = scanner.nextInt();
            scanner.nextLine();

            if (a == 1) {
                OyunKaydet.eskiOyunlariGoster(scanner);
            } else if (a == 2) {
                System.out.println("Yeni oyuna başlıyorsunuz.\n");
            } else {
                System.out.println("Geçersiz seçim yapıldı, tekrar deneyin.");
            }
        } while (a != 2);
        //yeni oyunun başlatıldığı kod bloğu
        System.out.println("Hogwarts'a Hoş Geldiniz!\n");

        while (secilenKarakter == null) {
            System.out.println("Maceraya başlamadan önce bir karakter seçmelisiniz:");
            System.out.println("1. Hermione Granger");
            System.out.println("2. Harry Potter");
            System.out.println("3. Ron Weasley");
            System.out.print("Seçiminiz (1-2-3): ");

            String input = scanner.nextLine();
            try {
                int secim = Integer.parseInt(input);

                switch (secim) {
                    case 1:
                        secilenKarakter = new Hermione();
                        break;
                    case 2:
                        secilenKarakter = new Harry();
                        break;
                    case 3:
                        secilenKarakter = new Ron();
                        break;
                    default:
                        System.out.println("Geçersiz seçim. Tekrar deneyin.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Geçersiz giriş. Lütfen bir sayı girin.");
            }
        }


        //karakterin ismi ve kendine özel avantajı konsola yazdırılır
        System.out.println("\nİyi seçim. Karakteriniz: " + secilenKarakter.getIsim());
        secilenKarakter.karakterAvantaji();

        //ev secimi nesnesi oluşturulup, fonksiyon çağırılıyor.
        EvSecimi evSecimi = new EvSecimi();
        evSecimi.evSecimi();

        //seçilen ev karaktere atanıyor
        secilenKarakter.setEv(evSecimi.getEv());

        // Karakter durumu gösteriliyor
        secilenKarakter.karakterDurumu();

        //kullanıcıya ipucu isteyip istemediği soruluyor
        String cevap;
        do {
            System.out.println("\nArtık hazırsın. İlk ders gününden önce Hagrid'den ipuçları almak ister misin?(E/H)");
            cevap = scanner.next().toLowerCase();

            switch (cevap) {
                case "e":
                    System.out.println("                 ⁺˚*･༓☾　　☽༓･*˚⁺");
                    System.out.println("Merhaba, sanırım merak ettiğin şeyler var...");
                    System.out.println("Kendini hazırlamak için 4 günün var, bu 4 günü iyi değerlendir.");
                    System.out.println("5.gün zorlu bir gün olacak. İyi bir büyücü olup olmadığını herkes görecek.");
                    System.out.println("Aklına daha fazla şey takılırsa bana her zaman sorabilirsin. Bol şans!");
                    System.out.println("                 ⁺˚*･༓☾　　☽༓･*˚⁺");
                    break;
                case "h":
                    System.out.println("Macera başlıyor...");
                    break;
                default:
                    System.out.println("Lütfen 'e' ya da 'h' şeklinde cevap verin.\n");
            }
        } while (!cevap.equals("e") && !cevap.equals("h"));


        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //oyuncunun zeka, savunma gibi yeteneklerini geliştirmesi gereken 4 temel oyun gününün gerçekleştirildiği for döngüsü
        for (int i = 1; i <= 4; i++) {
            OyunHikayesi oyunHikayesi = new OyunHikayesi(i, secilenKarakter, evSecimi.getDersProgrami());
            oyunHikayesi.oyunuBaslat();

            boolean araEkranDevam = true;
            while (araEkranDevam) {
                System.out.println("\n͙⁺˚*･༓☾　　☽༓･*˚⁺");
                System.out.println("Bir seçim yapın:");
                System.out.println("1- Oyuna devam et.");
                System.out.println("2- Karakter durumunu görüntüle.");
                System.out.println("3- Oyundan çık.");
                System.out.print("Seçiminiz: ");

                //her bir oyun gününden sonra oyuncuya sunulan ara ekran
                try {
                    int araSecim = scanner.nextInt();
                    scanner.nextLine();

                    switch (araSecim) {
                        case 1:
                            araEkranDevam = false;
                            break;
                        case 2:
                            secilenKarakter.karakterDurumu();
                            break;
                        case 3:
                            System.out.println("Oyundan çıkılıyor...");
                            OyunKaydet.kaydet(secilenKarakter);
                            System.exit(0);
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
    }
}
