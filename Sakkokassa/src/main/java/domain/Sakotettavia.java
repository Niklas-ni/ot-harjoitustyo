package domain;

public class Sakotettavia {

    private String name;
    private int ammount;
    private int id= 0;

    public Sakotettavia(int id, String name, int ammount) {
        this.ammount = ammount;
        this.name = name;
        this.id = id;
    }

    public Sakotettavia(String name, int ammount) {
        this.name = name;
        this.ammount = ammount;

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
