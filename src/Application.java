import Modle.Kotik;
public class Application {
    public static void main(String[] args) {
    Kotik kotik = new Kotik();
    Kotik kotik1 = new Kotik();
    kotik1.setKotik(2,"Na",2.1,"MEEW");
    kotik.liveAnotherDay();
        if (kotik.getMeow().equals(kotik1.getMeow()) == true)
        {
            System.out.println("Meow poxozhi");
        }
        else
        {
            System.out.println("nepohozhi meow");
        }
        System.out.println(Kotik.NumOfObject);
    }
}