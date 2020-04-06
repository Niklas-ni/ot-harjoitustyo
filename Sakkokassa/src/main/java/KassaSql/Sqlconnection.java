package KassaSql;

import java.sql.*;
import java.util.ArrayList;
import Sakkokassa.domain.Sakotettavia;

public class Sqlconnection {

    public static boolean CreateSakkokassaTable(String NameOfTeam) throws SQLException {
        try {
            Connection db = DriverManager.getConnection("jdbc:sqlite:testi.db");
            Statement Sakkokassa = db.createStatement();
            Sakkokassa.execute("CREATE TABLE " + NameOfTeam + " (id INTEGER PRIMARY KEY, Name TEXT UNIQUE, ToPay INTEGER)");
            Sakkokassa.execute("PRAGMA foreign_keys = ON");
        } catch (SQLException e) {
            System.out.println(e);
            System.out.println("Allready existing choose another:");
            return false;
        }
        return true;
    }

    public static boolean AddSakkokassaPlayer(String NameOfTeam, String player) throws SQLException {
        try {
            Connection db = DriverManager.getConnection("jdbc:sqlite:testi.db");
            Statement Sakkokassa = db.createStatement();

            PreparedStatement p = db.prepareStatement("INSERT INTO " + NameOfTeam + "(Name,ToPay) VALUES (?,?)");
            p.setString(1, player);
            p.setInt(2, 0);
            p.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            System.out.println("Player exist");
            return false;
        }
        return true;
    }

    public static boolean AddSakkokassaPlayerMoney(String NameOfTeam, String player, int ToPay) throws SQLException {
        try {
            Connection db = DriverManager.getConnection("jdbc:sqlite:testi.db");
            Statement Sakkokassa = db.createStatement();

            PreparedStatement p = db.prepareStatement("INSERT INTO " + NameOfTeam + "(Name,ToPay) VALUES (?,?)");
            p.setString(1, player);
            p.setInt(2, ToPay);
            p.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            System.out.println("Player exist");
            return false;
        }
        return true;
    }

    public static boolean SakkokassaPlayerNewPayment(String NameOfTeam, String player, int ToPay) throws SQLException {
        try {
            Connection db = DriverManager.getConnection("jdbc:sqlite:testi.db");
            Statement Sakkokassa = db.createStatement();

            PreparedStatement p = db.prepareStatement("UPDATE " + NameOfTeam + " SET ToPay = ToPay + " + ToPay + " WHERE " + NameOfTeam + ".Name=?");
            p.setString(1, player);
            p.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            System.out.println("Does not exist!");
            return false;
        }
        return true;
    }

    public static ArrayList PrintNamesFromTable(String NameOfTeam) throws SQLException {
        ArrayList players = new ArrayList();

        try {
            Connection db = DriverManager.getConnection("jdbc:sqlite:testi.db");
            Statement Sakkokassa = db.createStatement();
            PreparedStatement p = db.prepareStatement("SELECT Name FROM " + NameOfTeam);

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

    public static ArrayList PrintAmountsFromTable(String NameOfTeam) throws SQLException {
        ArrayList Amount = new ArrayList();

        try {
            Connection db = DriverManager.getConnection("jdbc:sqlite:testi.db");
            Statement Sakkokassa = db.createStatement();
            PreparedStatement p = db.prepareStatement("SELECT ToPay FROM " + NameOfTeam);

            ResultSet r = p.executeQuery();
            while (r.next()) {
                int amount = r.getInt("ToPay");
                Amount.add(amount);

            }
        } catch (SQLException e) {
            System.out.println("No such List");

        }
        return Amount;
    }

    public static ArrayList PrintNameAmountOFAll(String NameOfTeam) throws SQLException {
        ArrayList<Sakotettavia> NameAmmount = new ArrayList();

        try {
            Connection db = DriverManager.getConnection("jdbc:sqlite:testi.db");
            Statement Sakkokassa = db.createStatement();
            PreparedStatement p = db.prepareStatement("SELECT Name,ToPay FROM " + NameOfTeam);

            ResultSet r = p.executeQuery();
            while (r.next()) {
                String Name = r.getString("Name");
                int Amount = r.getInt("ToPay");

                NameAmmount.add(new Sakotettavia(Name, Amount));

            }
        } catch (SQLException e) {
            System.out.println("No such List");

        }
        return NameAmmount;
    }

    public static boolean KassaTableExists(String NameOfTeam) throws SQLException{
        Connection db = DriverManager.getConnection("jdbc:sqlite:testi.db");
        DatabaseMetaData dbm = db.getMetaData();
// check if "employee" table is there
        ResultSet tables = dbm.getTables(null, null, NameOfTeam, null);
        if (tables.next()) {
            db.close();
            return true;
        } else {
            db.close();
            return false;
        }

    }
}


