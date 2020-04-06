package domain;

public class Sakotettavia {

    private String name;
    private int ammount;

    public Sakotettavia(String name, int ammount) {
        this.ammount = ammount;
        this.name = name;
    }

    public int getAmmount() {
        return ammount;
    }

    public String getName() {
        return name;
    }

    public void setAmmount(int ammount) {
        this.ammount = ammount;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name + " " + ammount + " Euros";
    }

}
