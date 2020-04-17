package domain;

import dao.Sqlconnection;
import domain.Sakotettavia;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class Kassa {

    private Sakotettavia players;
    private static final HashMap<String, String> TAble = new HashMap<>();

    public static boolean newcashboxpassword(String name, String password) throws SQLException {
        if (Sqlconnection.createPayboxTable(name)) {
            TAble.put(name, password);
            return true;
        }
        return false;

    }

    public static boolean newcashbox(String username) throws SQLException {
        return Sqlconnection.createPayboxTable(username);

    }

    public static boolean isacashbox(String name) throws SQLException {
        return Sqlconnection.tableExists(name);
    }

    public static boolean checkpassword(String team, String password) {
        if (TAble.containsKey(team)) {
            if (TAble.get(team).equals(password)) {
                return true;
            }
        }
        return false;
    }
    
    public static ArrayList getAllplayersAmounts(String team) throws SQLException{
        ArrayList<String> pelaajat = new ArrayList<>();
        for (Object pelaajia  : Sqlconnection.printEverything(team)) {
            pelaajat.add(String.valueOf(pelaajia));
        }
        
        return pelaajat;
    }

}
