package domain;

public class Player {

    private String name;
    private int ammount;

    public Player(String name, int ammount) {
        this.name = name;
        this.ammount = ammount;
    }

    public String getname() {
        return name;
    }

    public int getAmmount() {
        return ammount;
    }

    public void setAmmount(int ammount) {
        this.ammount = ammount;
    }
}
