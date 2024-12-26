public class Harry extends Karakter implements voldemortOzelHamle{
    //karakterin özelliklerini tanımlayan kurucu metot
    public Harry () {
        super("Harry Potter", 70, 70, 100,100);
    }

    //karakter avantajını tanımlayan metot
    @Override
    public void karakterAvantaji() {
        System.out.println("Harry büyücülük bonusuna sahip.");
    }

    // oyun sonunda voldemorta özel hamleyi gerçekleştirecek metot
    @Override
    public void voldemortOzelHamle() {
        System.out.println(getIsim() + " Voldemort'u güçlü bir Expelliarmus büyüsüyle alt ediyor!\nHarika bir büyücü.");
    }


}
