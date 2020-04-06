package Sakkokassa.domain;


public class Sakotettavia {

    
    private String Name;
    private int Ammount;
    
    public Sakotettavia(String Name, int Ammount) {
        this.Ammount = Ammount;
        this.Name = Name;
    }

    public int getAmmount() {
        return Ammount;
    }

    public String getName() {
        return Name;
    }

    public void setAmmount(int Ammount) {
        this.Ammount = Ammount;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    @Override
    public String toString() {
        return Name + " "+ Ammount + " Euros";
    }

   


    

}
