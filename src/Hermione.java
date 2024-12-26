public class Hermione extends Karakter implements voldemortOzelHamle {
    //karakterin özelliklerini tanımlayan kurucu metot
    public Hermione() {
        super("Hermione Granger", 100, 70, 70,100);
    }
    //karakter avantajını tanımlayan metot
    @Override
    public void karakterAvantaji() {
        System.out.println("Hermione zeka bonusuna sahip.");
    }

    // oyun sonunda voldemorta özel hamleyi gerçekleştirecek metot
    @Override
    public void voldemortOzelHamle() {
        System.out.println(getIsim() + " Voldemort'u zekasıyla alt ediyor!\nHarika bir büyücü.");
    }

}