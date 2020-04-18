package domain;

public class Player {

    private String name;
    private int ammount;
    private PayBoxTable payBoxTable;

    public Player(String name, int ammount, PayBoxTable payBoxTable) {
        this.name = name;
        this.ammount = ammount;
        this.payBoxTable = payBoxTable;
    }

    public Player(String name, PayBoxTable payBoxTable) {
        this.name = name;
        this.payBoxTable = payBoxTable;
        this.ammount = 0;
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

    public PayBoxTable getPayBoxTable() {
        return payBoxTable;
    }

    public void setPayBoxTable(PayBoxTable payBoxTable) {
        this.payBoxTable = payBoxTable;
    }

}
