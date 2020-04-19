package domain;

import java.util.ArrayList;

public class PayBoxTable {

    private String name;
    private String password;

    public PayBoxTable(String name, String password) {
        this.name = name;
        this.password = password;

    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}
