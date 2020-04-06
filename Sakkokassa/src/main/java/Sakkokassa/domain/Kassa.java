package Sakkokassa.domain;

import KassaSql.Sqlconnection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author niri91
 */
public class Kassa {

    
    private Sakotettavia players;
    private static final HashMap<String, String> Tables = new HashMap<>();

    public static boolean NewCashBoxPassword(String Name, String Password) throws SQLException {
        if (Sqlconnection.CreateSakkokassaTable(Name)) {
            Tables.put(Name, Password);
            return true;
        }
        return false;

    }

    public static boolean NewCashBox(String username) throws SQLException {
        if (Sqlconnection.CreateSakkokassaTable(username)) {
            return false;
        }
        return true;

    }
    public static boolean IsACashBox(String Name) throws SQLException{
        return Sqlconnection.KassaTableExists(Name);
    }
    

    public static boolean CheckPassword(String team ,String password) {
        if (Tables.get(team).equals(password)) {
            return true;
        }
        return false;
    }

}
