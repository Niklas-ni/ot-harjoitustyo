package dao;

import java.sql.*;
import java.util.ArrayList;
import domain.Sakotettavia;
import domain.PayBoxTable;
import java.util.Set;

public class Sqlconnection{

    public static boolean createPayboxTable(String nameOfTeam) throws SQLException {
        try {
            Connection db = DriverManager.getConnection("jdbc:sqlite:testi.db");
            Statement sakkokassa = db.createStatement();
            sakkokassa.execute("CREATE TABLE " + nameOfTeam + " (id INTEGER PRIMARY KEY, Name TEXT UNIQUE, ToPay INTEGER)");
            sakkokassa.execute("PRAGMA foreign_keys = ON");
        } catch (SQLException e) {
            System.out.println(e);
            System.out.println("Allready existing choose another:");
            return false;
        }
        return true;
    }

    public static boolean addplayerTable(String nameOfTeam, String player) throws SQLException {
        try {
            Connection db = DriverManager.getConnection("jdbc:sqlite:testi.db");
            PreparedStatement p = db.prepareStatement("INSERT INTO " + nameOfTeam + "(Name,ToPay) VALUES (?,?)");
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

    public static boolean addPlayerAndMoney(String nameOfTeam, String player, int toPay) throws SQLException {
        try {
            Connection db = DriverManager.getConnection("jdbc:sqlite:testi.db");
            PreparedStatement p = db.prepareStatement("INSERT INTO " + nameOfTeam + "(Name,ToPay) VALUES (?,?)");
            p.setString(1, player);
            p.setInt(2, toPay);
            p.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            System.out.println("Player exist");
            return false;
        }
        return true;
    }

    public static boolean playerUppdatePayment(String nameOfTeam, String player, int toPay) throws SQLException {
        try {
            Connection db = DriverManager.getConnection("jdbc:sqlite:testi.db");
            PreparedStatement p = db.prepareStatement("UPDATE " + nameOfTeam + " SET ToPay = ToPay + " + toPay + " WHERE " + nameOfTeam + ".Name=?");
            p.setString(1, player);
            p.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            System.out.println("Does not exist!");
            return false;
        }
        return true;
    }

    public static ArrayList printTableName(String nameOfTeam) throws SQLException {
        ArrayList players = new ArrayList();

        try {
            Connection db = DriverManager.getConnection("jdbc:sqlite:testi.db");
            PreparedStatement p = db.prepareStatement("SELECT Name FROM " + nameOfTeam);

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

    public static ArrayList printTableAmmount(String nameOfTeam) throws SQLException {
        ArrayList amount = new ArrayList();

        try {
            Connection db = DriverManager.getConnection("jdbc:sqlite:testi.db");
            PreparedStatement p = db.prepareStatement("SELECT ToPay FROM " + nameOfTeam);

            ResultSet r = p.executeQuery();
            while (r.next()) {
                int howMuch = r.getInt("ToPay");
                amount.add(howMuch);

            }
        } catch (SQLException e) {
            System.out.println("No such List");

        }
        return amount;
    }

    public static ArrayList printEverything(String nameOfTeam) throws SQLException {
        ArrayList<Sakotettavia> nameAmmount = new ArrayList();

        try {
            Connection db = DriverManager.getConnection("jdbc:sqlite:testi.db");
            PreparedStatement p = db.prepareStatement("SELECT Name,ToPay FROM " + nameOfTeam);

            ResultSet r = p.executeQuery();
            while (r.next()) {
                String name = r.getString("Name");
                int amount = r.getInt("ToPay");

                nameAmmount.add(new Sakotettavia(name, amount));

            }
        } catch (SQLException e) {
            System.out.println("No such List");

        }
        return nameAmmount;
    }

    public static boolean tableExists(String nameOfTeam) throws SQLException {
        Connection db = DriverManager.getConnection("jdbc:sqlite:testi.db");
        DatabaseMetaData dbm = db.getMetaData();
        ResultSet tables = dbm.getTables(null, null, nameOfTeam, null);
        if (tables.next()) {
            db.close();
            return true;
        }
        db.close();
        return false;
    }
}
