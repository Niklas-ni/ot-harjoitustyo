package dao;

import domain.Player;
import java.sql.*;
import java.util.ArrayList;

public class SqlPlayerdao implements Playerdao {

    public SqlPlayerdao(String team) throws SQLException {
        try {
            Connection db = DriverManager.getConnection("jdbc:sqlite:teamPlayers.db");
            Statement paytable = db.createStatement();
            paytable.execute("CREATE TABLE " + team + " (id INTEGER PRIMARY KEY, Name TEXT UNIQUE, ToPay INTEGER, AllTime Integer)");
            paytable.execute("PRAGMA foreign_keys = ON");
        } catch (SQLException e) {
        }
    }

    @Override
    public boolean addPlayer(Player player, String payboxname) throws SQLException {
        try {
            Connection db = DriverManager.getConnection("jdbc:sqlite:teamPlayers.db");
            PreparedStatement p = db.prepareStatement("INSERT INTO " + payboxname + "(Name,ToPay,AllTime ) VALUES (?,?,?)");
            p.setString(1, player.getname());
            p.setInt(2, player.getAmmount());
            p.setInt(3,player.getAmmount());
            p.executeUpdate();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    @Override
    public ArrayList<String> getAll(String teamTable) throws SQLException {
        ArrayList<String> teams = new ArrayList<>();
        try {
            Connection db = DriverManager.getConnection("jdbc:sqlite:teamPlayers.db");
            PreparedStatement p = db.prepareStatement("SELECT Name,ToPay,AllTime FROM " + teamTable);

            ResultSet r = p.executeQuery();
            while (r.next()) {
                String name = r.getString("Name");
                int amount = r.getInt("ToPay");
                int totalamount = r.getInt("AllTime");
                String players = name + " ToPay: " + amount + " AllTime: " + totalamount + " Euros";
                teams.add(players);
            }
        
        } catch (SQLException e) {
        }
        return teams;
    }

    @Override
    public void uppdatePlayerAmmount(Player player, String payboxname) throws SQLException {
        try {
            Connection db = DriverManager.getConnection("jdbc:sqlite:teamPlayers.db");
            PreparedStatement p = db.prepareStatement("UPDATE " + payboxname + " SET ToPay = ToPay + " + player.getAmmount() + " WHERE " + payboxname + ".Name=?");
            p.setString(1, player.getname());
            p.executeUpdate();
        } catch (SQLException e) {
        }
    }
     public void uppdatePlayerAlltime(Player player, String payboxname) throws SQLException {
        try {
            Connection db = DriverManager.getConnection("jdbc:sqlite:teamPlayers.db");
            PreparedStatement p = db.prepareStatement("UPDATE " + payboxname + " SET AllTime = AllTime + " + player.getAmmount() + " WHERE " + payboxname + ".Name=?");
            p.setString(1, player.getname());
            p.executeUpdate();
        } catch (SQLException e) {
        }
    }

    @Override
    public int getSumfromTable(String teamTable) throws SQLException {
        int sumOfTable = 0;
        try {
            Connection db = DriverManager.getConnection("jdbc:sqlite:teamPlayers.db");
            PreparedStatement p = db.prepareStatement("SELECT SUM(ToPay) FROM " + teamTable);
            ResultSet r = p.executeQuery();
            while (r.next()) {
                int amount = r.getInt("SUM(ToPay)");
                sumOfTable = amount;
            }
        } catch (SQLException e) {
        }
        return sumOfTable;
    }
     public int getSumAlltimeTable(String teamTable) throws SQLException {
        int sumOfTable = 0;
        try {
            Connection db = DriverManager.getConnection("jdbc:sqlite:teamPlayers.db");
            PreparedStatement p = db.prepareStatement("SELECT SUM(AllTime) FROM " + teamTable);
            ResultSet r = p.executeQuery();
            while (r.next()) {
                int amount = r.getInt("SUM(AllTime)");
                sumOfTable = amount;
            }
        } catch (SQLException e) {
        }
        return sumOfTable;
    }

}
