public class Ron extends Karakter implements voldemortOzelHamle{
    //karakterin özelliklerini tanımlayan kurucu metot
    public Ron(){
        super("Ron Weasley", 70,100,70,100);
    }
    //karakter avantajını tanımlayan metot
    @Override
    public void karakterAvantaji(){
        System.out.println("Ron savunma bonusuna sahip.");
    }
// oyun sonunda voldemorta özel hamleyi gerçekleştirecek metot
    @Override
    public void voldemortOzelHamle() {
        System.out.println(getIsim() + " kendisini ve arkadaşlarını Voldemort'a karşı koruyor!\nHarika bir büyücü.");
    }
}
