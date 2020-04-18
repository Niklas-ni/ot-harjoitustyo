package dao;

import domain.Player;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class sqlPlayerdao implements Playerdao {

    private ArrayList<Player> teams;

    public sqlPlayerdao(String team) throws SQLException {
        try {
            Connection db = DriverManager.getConnection("jdbc:sqlite:teamPlayers.db");
            Statement paytable = db.createStatement();
            paytable.execute("CREATE TABLE " + team + " (id INTEGER PRIMARY KEY, Name TEXT UNIQUE, ToPay INTEGER)");
            paytable.execute("PRAGMA foreign_keys = ON");
        } catch (SQLException e) {
            System.out.println(e);
            System.out.println("Error");
        }
    }

    @Override
    public boolean addPlayer(Player player) throws Exception {
     try {
            Connection db = DriverManager.getConnection("jdbc:sqlite:teamPlayers.db");
            PreparedStatement p = db.prepareStatement("INSERT INTO " + player.getPayBoxTable() + "(Name,ToPay) VALUES (?,?)");
            p.setString(1, player.getname());
            p.setInt(2, player.getAmmount());
            p.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            System.out.println("Player exist");
            return false;
        }
        return true;
    }

    @Override
    public ArrayList<Player> getAll(Player player) {
       try {
            Connection db = DriverManager.getConnection("jdbc:sqlite:teamPlayers.db");
            PreparedStatement p = db.prepareStatement("SELECT Name,ToPay FROM " + player.getPayBoxTable());

            ResultSet r = p.executeQuery();
            while (r.next()) {
                String name = r.getString("Name");
                int amount = r.getInt("ToPay");

                teams.add(new Player(name, amount, player.getPayBoxTable()));

            }
        } catch (SQLException e) {
            System.out.println("No such List");

        }
        return teams;
    }

    @Override
    public void uppdatePlayerAmmount(Player player) throws Exception {
         try {
            Connection db = DriverManager.getConnection("jdbc:sqlite:teamPlayers.db");
            PreparedStatement p = db.prepareStatement("UPDATE " + player.getPayBoxTable() + " SET ToPay = ToPay + " + player.getAmmount() + " WHERE " + player.getPayBoxTable() + ".Name=?");
            p.setString(1, player.getname());
            p.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            System.out.println("Does not exist!");
            
        }
     
    }
}
