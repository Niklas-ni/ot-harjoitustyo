package domain;

import dao.Sqlconnection;
import domain.Sakotettavia;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class Kassa {

    private static PayBoxTable tables;
    private Sakotettavia players;
    private static ArrayList<PayBoxTable> boxes = new ArrayList<>();

    public static boolean newcashboxpassword(String name, String password) throws SQLException {
        if (Sqlconnection.createPayboxTable(name)) {
            tables = new PayBoxTable(name, password);
            boxes.add(tables);
            
            return true;
        }
        return false;

    }

    public static boolean isacashbox(String name) throws SQLException {
        return Sqlconnection.tableExists(name);
    }

    public static boolean checkpassword(String team, String password) {

                return true;
           
        
       
    }
    
    public static ArrayList getAllplayersAmounts(String team) throws SQLException{
        ArrayList<String> pelaajat = new ArrayList<>();
        for (Object pelaajia  : Sqlconnection.printEverything(team)) {
            pelaajat.add(String.valueOf(pelaajia));
        }
        
        return pelaajat;
    }

}
