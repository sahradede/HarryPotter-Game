import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level; //seçmen şapka seçim yaparken olabilecek hatalara karşı
import java.util.logging.Logger;

public class EvSecimi {
    //seçmen şapka exception sınıfı için oluşturulan alan
    private static final Logger logger = Logger.getLogger(EvSecimi.class.getName());
    private String ev;
    private final ArrayList<String> dersProgrami = new ArrayList<>();

    //rastgele bir şekilde oyuncuyu 4 evden birine atayan fonksiyon.
    public void evSecimi() {
        String[] evler = {"Gryffindor", "Hufflepuff", "Ravenclaw", "Slytherin"};
        Random random = new Random();

        System.out.println("\nDevam etmeden önce Seçmen Şapka hangi evde olacağınıza karar verecek.\n");
        System.out.println("⠂⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢧⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠐⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠰⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠄⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠄⠀⠀⠀⠀⠀⠈⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⡴⠖⠒⠶⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈\n" +
                "⠀⠀⠀⠀⠀⠠⠀⠀⠀⠀⠀⠀⠀⠀⠀⡀⠀⠀⠀⠀⠀⠠⠀⠀⠀⠀⢀⡰⠋⠀⢸⡆⠀⠈⠳⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣀⣤⠶⠛⠡⣏⠡⡀⢷⣄⡀⠀⠈⠙⠶⡄⠀⠤⠘⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣰⡟⠁⠀⠀⠀⠀⠙⠦⣈⠚⢹⡷⠤⠿⢷⣄⢹⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠄⠀⠀⠀⠀⠀⠀⠀⠀⢀⣰⢏⠐⠀⠀⠀⣀⣀⣀⡀⢈⠹⡏⠁⠀⠀⠀⠘⢧⣻⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⣾⡋⠁⠀⠀⠀⢰⣮⡭⠭⠤⢄⡀⢣⣷⠀⠀⠀⠀⠀⠀⠍⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠂⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⠴⠶⣾⣆⡀⠀⠀⣀⡴⠟⠁⣠⣾⣤⣄⡙⢖⢿⡆⠀⠀⠀⠀⠀⠀⡂⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠰⠀⠀⠀⠀⠂⠀⢀⢰⣯⣦⡀⠈⠳⢄⡴⠋⠉⢀⣴⣿⣿⣿⣿⣿⣧⡘⠜⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠆⠀⠀⠀⠀⠀⠁⠀⠀⠀⠀⠀⠀⠈⠻⡿⣿⣆⠀⢸⡇⢀⣴⣿⣿⣿⣿⣿⣿⣿⣃⡅⢰⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡴⢻⣿⡿⡷⠼⠳⠞⠻⠿⣿⠯⠭⠿⠿⠫⠅⢁⠈⠳⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢳⣄⡤⠈⠁⠀⢀⡀⡀⣀⠈⠉⢆⠀⠠⠤⠄⣈⡀⡀⣷⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⢀⡀⠀⠀⠀⠀⠀⠀⠀⢀⡞⣩⣶⣶⣶⣶⣿⣿⣿⣿⣿⣶⣶⣶⠶⠿⠛⠲⢯⣙⣿⣆⣀⡀⠀⠀⠈⠀⠀⠄⠀⠀⠀⠀⠀⠂\n" +
                "⠀⠀⠀⠀⢸⢳⡄⠀⠀⠀⠀⠀⢀⣨⡿⠸⡿⣿⣿⣿⣿⡿⠿⡟⠋⢉⣡⠤⠀⠀⠀⠀⢉⡩⠝⣛⣉⣭⣭⣽⣶⣷⣤⡀⠀⢀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠈⢣⡹⢶⣲⡶⣤⣠⡼⢏⢀⡠⣶⡶⠿⢍⣀⣀⣉⡠⠖⠋⠉⠁⢀⠀⢐⣨⠵⠖⠋⠉⠀⠀⠀⠀⠀⠉⠙⢿⣆⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⣙⢮⣳⢿⣌⠊⠁⡸⠅⠀⠀⠉⠐⠂⠔⢺⠉⠀⠀⠠⢔⣈⡵⠾⠛⠓⠒⠦⢄⠀⠀⠀⠀⠀⠀⠀⢀⣨⠞⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠈⠈⠀⠶⠉⠳⣝⠳⠦⣤⡄⡀⠀⠀⢀⠀⠀⠀⠃⣀⣤⣖⣉⣁⣀⣀⣀⣄⠤⠤⠶⠶⠶⠤⠤⠴⠶⠒⠉⠁⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠱⠀⠀⠀⡈⠓⠦⠤⣀⣩⣍⣉⣭⠤⠶⠒⠋⡙⠀⠉⠉⠉⠉⠉⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠰⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⢀⡀⠀⠂⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("Seçmen Şapka düşünüyor...");

        // düşünme efekti verilmesi için 3 saniyelik bekleme süresi
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            logger.log(Level.WARNING, "Thread uyutulurken bir hata meydana geldi", e);
        }

        //fonksiyonun başarılı olması halinde ev ataması yapılıyor
        ev = evler[random.nextInt(evler.length)];
        System.out.println("Seçmen Şapka sonunda kararını verdi: " + ev + " evine atandınız!");

        try {
            dersProgramiOlustur();
            dersProgramiGoster();
        } catch (GecersizEvException e) {
            logger.log(Level.SEVERE, "Geçersiz ev hatası!", e);//seçmen şapkanın random ev seçmesi sıraında meydana gelebilecek hatalara karşı
        }
    }

    //oyuncunun evine özel 'ders programı' oluşturan fonksiyon
    private void dersProgramiOlustur() throws GecersizEvException {
        switch (ev.toLowerCase()) {
            case "gryffindor":
                dersProgrami.add("Büyü - 08:00");
                dersProgrami.add("İksir - 14:00");
                dersProgrami.add("Savunma - 16:00");
                break;
            case "hufflepuff":
                dersProgrami.add("İksir - 08:00");
                dersProgrami.add("Savunma - 14:00");
                dersProgrami.add("Büyü - 16:00");
                break;
            case "ravenclaw":
                dersProgrami.add("Büyü - 08:00");
                dersProgrami.add("Savunma - 14:00");
                dersProgrami.add("İksir - 16:00");
                break;
            case "slytherin":
                dersProgrami.add("Savunma - 08:00");
                dersProgrami.add("Büyü - 14:00");
                dersProgrami.add("İksir - 16:00");
                break;
            default:
                //seçmen şapkanın ev seçimi sırasında meydana gelebilecek hataya karşı:
                throw new GecersizEvException("Seçmen Şapka bilinmeyen bir ev seçti: " + ev);
        }
    }

    //oyuncuya ders programını gösteren fonksiyon
    private void dersProgramiGoster() {
        System.out.println("\n" + ev + " evine özel ders programınız:");
        for (String ders : dersProgrami) {
            System.out.println("- " + ders);
        }

    }
    //uygulamanın farklı yerlerinde ders programı bilgisini kullanabilmek için yazılmış getter methodu
    public ArrayList<String> getDersProgrami() {
        return dersProgrami;
    }

    //uygulamanın farklı yerlerinde ev bilgisini kullanabilmek için yazılmış getter methodu
    public String getEv() {
        return ev;
    }
}
