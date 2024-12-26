/* bu sınıf seçmen şapka ev seçerken meydana gelebilecek hatalara karşı
oluşturulmuş bir özel istisna sınıfıdır */
public class GecersizEvException extends Exception {
    public GecersizEvException(String mesaj) {
        super(mesaj); //exception sınıfının kurucu metoduna gönderiliyor
    }
}