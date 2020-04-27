package dao;

import domain.Player;
import java.sql.*;
import java.util.ArrayList;

public class SqlPlayerdao implements Playerdao {

    public SqlPlayerdao(String team) throws SQLException {
        try {
            Connection db = DriverManager.getConnection("jdbc:sqlite:teamPlayers.db");
            Statement paytable = db.createStatement();
            paytable.execute("CREATE TABLE " + team + " (id INTEGER PRIMARY KEY, Name TEXT UNIQUE, ToPay INTEGER)");
            paytable.execute("PRAGMA foreign_keys = ON");
        } catch (SQLException e) {
        }
    }

    @Override
    public boolean addPlayer(Player player, String payboxname) throws SQLException {
        try {
            Connection db = DriverManager.getConnection("jdbc:sqlite:teamPlayers.db");
            PreparedStatement p = db.prepareStatement("INSERT INTO " + payboxname + "(Name,ToPay ) VALUES (? ,?)");
            p.setString(1, player.getname());
            p.setInt(2, player.getAmmount());
            p.executeUpdate();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    @Override
    public ArrayList<Player> getAll(String teamTable) throws SQLException {
        ArrayList<Player> teams = new ArrayList<>();
        try {
            Connection db = DriverManager.getConnection("jdbc:sqlite:teamPlayers.db");
            PreparedStatement p = db.prepareStatement("SELECT Name,ToPay FROM " + teamTable);

            ResultSet r = p.executeQuery();
            while (r.next()) {
                String name = r.getString("Name");
                int amount = r.getInt("ToPay");
                teams.add(new Player(name, amount));
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
}
