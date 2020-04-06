package domain;

import sql.Sqlconnection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class Kassa {

    private Sakotettavia players;
    private static final HashMap<String, String> TAble = new HashMap<>();

    public static boolean newcashboxpassword(String name, String password) throws SQLException {
        if (Sqlconnection.createTable(name)) {
            TAble.put(name, password);
            return true;
        }
        return false;

    }

    public static boolean newcashbox(String username) throws SQLException {
        return Sqlconnection.createTable(username);

    }

    public static boolean isacashbox(String name) throws SQLException {
        return !Sqlconnection.tableExists(name);
    }

    public static boolean checkpassword(String team, String password) {
        if (TAble.get(team).equals(password)) {
            return true;
        }
        return false;
    }
    

}
