package Modle;

import java.util.concurrent.ThreadLocalRandom;

public class Kotik {
    private int Eat;
    private boolean IsEatStatus;
    private int Play;
    private boolean IsPlayStatus;
    private int Sleep;
    private boolean IsSleepStatus;
    private int Chasemouse;
    private boolean IsChaseMouseStatus;
    private int Restroom;
    private boolean IsRestRoomStatus;
    private String Name;
    private double weight;
    private String Male;
    private double Volume;
    private static int p = 1034;
    private int prettiness;
    private String meow;

    //кол-во экземпляров начало
    public static int NumOfObject;
    public Kotik()
    {
        NumOfObject++;
    }
    //конец
    //Гетеры и сетеры котика начало

    public void setKotik(int prettiness, String Name, double weight, String meow)
    {
        this.prettiness = prettiness;
        this.Name = Name;
        this.weight = weight;
        this.meow = meow;
    }

    public String getMeow() {
        if (meow != null)
        {
            return meow;
        }
        else {
            meow = "MEOW";
            return meow;
        }

    }

    public static void setPrettiness(int prettiness) {
        prettiness = prettiness;
    }

    public void setMeow(String meow) {
        meow = meow;
    }

    public static void setNameKotik(String Name) {
        Name = Name;
    }

    public static void setMale(String male) {
        male = male;
    }

    public String getNameKotik() {
        return Name;
    }

    public double getWeight() {
        return weight;
    }

    public String getMale() {
        return Male;
    }

    //конец
//игра с котиком начало
    public void PlayKotik() {
        IsChaseMouseStatus = false;
        IsEatStatus = false;
        IsSleepStatus = false;
        IsRestRoomStatus = false;
        //обьявляем что все остальные действия свободны
        //если мы не голодны то играем, иначе мы голодны
        if (Eat >= 1)
        {
            Eat--;
            Play++;
            IsPlayStatus = true;
            System.out.println("CAt gaming " + Eat + " " + IsEatStatus);
        }
        else
        {
            Eat--;
            System.out.println("Vozmozhno kot goloden " + Eat + " " + IsEatStatus);
        }
    }
    //конец
    //спать котику пора
    public void SleepKotik() {
        IsChaseMouseStatus = false;
        IsEatStatus = false;
        IsPlayStatus = false;
        IsRestRoomStatus = false;
        //обьявляем что все остальные действия свободны
        //если мы не голодны то спим, иначе мы голодны
        if (Eat >= 1) {
            Eat--;
            Sleep++;
            IsSleepStatus = true;
            System.out.println("CAt sleep " + Eat + " " + IsEatStatus);
        } else {
            Eat--;
            System.out.println("Vozmozhno kot goloden " + Eat + " " + IsEatStatus);
        }
    }
    //конец
    //котик ловит мышей
    public void ChaseMouseKotik() {
        IsSleepStatus = false;
        IsEatStatus = false;
        IsPlayStatus = false;
        IsRestRoomStatus = false;
        //обьявляем что все остальные действия свободны
        //если мы не голодны то ловим мышей, иначе мы голодны
        if (Eat >= 1) {
            Eat--;
            Chasemouse++;
            IsChaseMouseStatus = true;
            System.out.println("CAt Chase Mouse " + Eat + " " + IsEatStatus);
        } else {
            Eat--;
            System.out.println("Vozmozhno kot goloden " + Eat + " " + IsEatStatus);
        }
    }
    //котик идет в туалет
    public void RestRoomKotik() {
        IsSleepStatus = false;
        IsEatStatus = false;
        IsPlayStatus = false;
        IsChaseMouseStatus = false;
        //обьявляем что все остальные действия свободны
        //если мы не голодны то идем в туалет, иначе мы голодны
        if (Eat >= 1) {
            Eat--;
            Restroom++;
            IsRestRoomStatus = true;
            System.out.println("Kotik.java skhodil v tualet " + Eat + " " + IsEatStatus);
        } else {
            Eat--;
            System.out.println("Vozmozhno kot goloden " + Eat + " " + IsEatStatus);
        }
    }
    //конец
    //первая перегрузка, едим еду оперделенной сытности.
    public void getEat(int satiety) {
        IsSleepStatus = false;
        IsRestRoomStatus = false;
        IsPlayStatus = false;
        IsChaseMouseStatus = false;
        //обьявляем что все остальные действия свободны
        Eat += satiety;
        IsEatStatus = true;
        System.out.println("Kotik.java pokushal " + Eat + " " + IsEatStatus);
    }
    //вторая перегрузка, едим еду оперделенной сытности, и с известным названием.
    public void getEat(int satiety,String name) {
        IsSleepStatus = false;
        IsRestRoomStatus = false;
        IsPlayStatus = false;
        IsChaseMouseStatus = false;
        //обьявляем что все остальные действия свободны
        String NameEat = name;
        Eat += satiety;
        IsEatStatus = true;
        System.out.println("Kotik.java pokushal " + NameEat  + " " + Eat + " " + IsEatStatus);
    }
    //четвертая перегрузка исходя из условий, едим изевстное название, с оперделенной сытностью
    public void getEat(String name,int satiety) {
        IsSleepStatus = false;
        IsRestRoomStatus = false;
        IsPlayStatus = false;
        IsChaseMouseStatus = false;
        String NameEat = name;
        Eat += satiety;
        IsEatStatus = true;
        System.out.println("Kotik.java pokushal " + NameEat  + " " + Eat + " " + IsEatStatus);
    }
    //третья перегрузка, вызов четвертой перегрузки
    public void getEat() {
        getEat("Miaso",15);
    }


    //запуск дня кота, на 24 часа
    public void liveAnotherDay() {
        for (int i = 0;i <= 23;i++) {
            //получение случайного числа в промежутке от 0 - 4
            int randomNum = ThreadLocalRandom.current().nextInt(0, 4 + 1);
            //если кот очень сильно голоден, то кормим его
            if (Eat == -1 && IsEatStatus == false){
                getEat(2);
            }
            //если не голоден, то по случайному числу выполняем действие
            else
            {
                switch (randomNum) {
                    case (0):
                        PlayKotik();
                        break;
                    case (1):
                        ChaseMouseKotik();
                        break;
                    case (2):
                        getEat();
                        break;
                    case (3):
                        RestRoomKotik();
                        break;
                    case (4):
                        SleepKotik();
                        break;
                    default:
                        getEat();
                        break;
                }
            }
        }
    }
}