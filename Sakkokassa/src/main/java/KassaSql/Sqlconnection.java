package KassaSql;


import java.sql.*;
import java.util.ArrayList;

public class Sqlconnection {

    public static String CreateSakkokassaTable(String NameOfTeam) throws SQLException {
        try {
            Connection db = DriverManager.getConnection("jdbc:sqlite:testi.db");
            Statement Sakkokassa = db.createStatement();
            Sakkokassa.execute("CREATE TABLE " + NameOfTeam + " (id INTEGER PRIMARY KEY, Name TEXT UNIQUE, ToPay INTEGER)");
            Sakkokassa.execute("PRAGMA foreign_keys = ON");
            return "Done!";
        } catch (SQLException e) {
            System.out.println(e);
            System.out.println("Allready existing choose another:");
        }
        return "Fail!";
    }
    public static String AddSakkokassaPlayer(String NameOfTeam, String player) throws SQLException {
       try {
            Connection db = DriverManager.getConnection("jdbc:sqlite:testi.db");
            Statement Sakkokassa = db.createStatement();

            PreparedStatement p = db.prepareStatement("INSERT INTO " + NameOfTeam +"(Name,ToPay) VALUES (?,?)");
            p.setString(1, player);
            p.setInt(2, 0);
            p.executeUpdate();
            return "Player Added";
        } catch (SQLException e) {
            System.out.println(e);
            System.out.println("Player exist");
        }
       return "Fail!";
    }
    public static void AddSakkokassaPlayerMoney(String NameOfTeam, String player, int ToPay) throws SQLException {
       try {
            Connection db = DriverManager.getConnection("jdbc:sqlite:testi.db");
            Statement Sakkokassa = db.createStatement();

            PreparedStatement p = db.prepareStatement("INSERT INTO " + NameOfTeam +"(Name,ToPay) VALUES (?,?)");
            p.setString(1, player);
            p.setInt(2, ToPay);
            p.executeUpdate();
            System.out.println("Player Added and ToPay made!");
        } catch (SQLException e) {
            System.out.println(e);
            System.out.println("Player exist");
        }
    }
     public static void SakkokassaPlayerNewPayment(String NameOfTeam, String player, int ToPay) throws SQLException {
       try {
            Connection db = DriverManager.getConnection("jdbc:sqlite:testi.db");
            Statement Sakkokassa = db.createStatement();
        
            PreparedStatement p = db.prepareStatement("UPDATE " + NameOfTeam +" SET ToPay = ToPay + " + ToPay + " WHERE " + NameOfTeam+ ".Name=?");
            p.setString(1, player);
            p.executeUpdate();
            System.out.println("Uppdate made!");
        } catch (SQLException e) {
            System.out.println(e);
            System.out.println("Does not exist!");
        }
    }
      public static ArrayList PrintNamesFromTable(String NameOfTeam) throws SQLException {
        ArrayList players = new ArrayList();
        
         try {
            Connection db = DriverManager.getConnection("jdbc:sqlite:testi.db");
            Statement Sakkokassa = db.createStatement();
            PreparedStatement p = db.prepareStatement("SELECT Name FROM " + NameOfTeam );
            

            ResultSet r = p.executeQuery();
            while (r.next()) {
                String id = r.getString("Name");
                players.add(id);
                

            }
        } catch (SQLException e) {
             System.out.println("No such List");

        }
        return players;
    }
}


    
