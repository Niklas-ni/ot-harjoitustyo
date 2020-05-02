package dao;

import domain.Player;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * SqlPlayerdao Makes the tables according to what team names are given.
 * also does sql questions to make different column answers and updates
 */
public class SqlPlayerdao implements Playerdao {

    /**
     * Creates a Table with the name given.
     *
     * @param team the given name of Table
     * @throws java.sql.SQLException for sql problems
     *
     */
    public SqlPlayerdao(String team)throws SQLException {
        try {
            Connection db = DriverManager.getConnection("jdbc:sqlite:teamPlayers.db");
            Statement paytable;
            paytable = db.createStatement();
            paytable.execute("CREATE TABLE " + team + " (id INTEGER PRIMARY KEY, Name TEXT UNIQUE, ToPay INTEGER, AllTime INTEGER, Time TEXT)");
            paytable.execute("PRAGMA foreign_keys = ON");
        } catch (SQLException e) {
        }
    }

    /**
     * adds player and a Time to given Table name
     *
     * @param player this player
     * @param payboxname this team
     * @param time the current time
     * @return true if player does not exist if player exists returns false
     * @throws java.sql.SQLException catches if already existing
     */
    @Override
    public boolean addPlayer(Player player, String payboxname, String time) throws SQLException {
        try {
            Connection db = DriverManager.getConnection("jdbc:sqlite:teamPlayers.db");
            PreparedStatement p = db.prepareStatement("INSERT INTO " + payboxname + "(Name,ToPay,AllTime,Time ) VALUES (?,?,?,?)");
            p.setString(1, player.getname());
            p.setInt(2, player.getAmmount());
            p.setInt(3, player.getAmmount());
            p.setString(4, time);
            p.executeUpdate();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    /**
     * gets all information that exist in the table given in for name,ToPay,All
     * time,Time
     *
     * @param teamTable name of table from which
     * @return a List of all existing players and data in table
     * @throws SQLException
     */
    @Override
    public ArrayList<String> getAll(String teamTable) throws SQLException {
        ArrayList<String> teams = new ArrayList<>();
        try {
            Connection db = DriverManager.getConnection("jdbc:sqlite:teamPlayers.db");
            PreparedStatement p = db.prepareStatement("SELECT Name,ToPay,AllTime,Time FROM " + teamTable);

            ResultSet r = p.executeQuery();
            while (r.next()) {
                String name = r.getString("Name");
                int amount = r.getInt("ToPay");
                int totalamount = r.getInt("AllTime");
                String time = r.getString("Time");
                String players = name + " ToPay: " + amount + " AllTime: " + totalamount + " Euros. Last Uppdate " + time;
                teams.add(players);
            }

        } catch (SQLException e) {
        }
        return teams;
    }

    /**
     * Updates the ToPay column for chosen player in chosen table. just executes
     * an update
     *
     * @param player which player
     * @param payboxname which table
     *
     * @throws SQLException
     */
    @Override
    public void uppdatePlayerAmmount(Player player, String payboxname) throws SQLException {

        Connection db = DriverManager.getConnection("jdbc:sqlite:teamPlayers.db");
        PreparedStatement p = db.prepareStatement("UPDATE " + payboxname + " SET ToPay = ToPay + " + player.getAmmount() + " WHERE " + payboxname + ".Name=?");
        p.setString(1, player.getname());
        p.executeUpdate();

    }

    /**
     * Updates the All time column in a certain table.
     *
     * @param player which player
     * @param payboxname which table
     * @throws SQLException
     */
    @Override
    public void uppdatePlayerAlltime(Player player, String payboxname) throws SQLException {

        Connection db = DriverManager.getConnection("jdbc:sqlite:teamPlayers.db");
        PreparedStatement p = db.prepareStatement("UPDATE " + payboxname + " SET AllTime = AllTime + " + player.getAmmount() + " WHERE " + payboxname + ".Name=?");
        p.setString(1, player.getname());
        p.executeUpdate();

    }

    /**
     * Returns the full Sum of all elements in column ToPay
     *
     * @param teamTable which table
     * @return Sum of ToPay column
     * @throws SQLException
     */
    @Override
    public int getSumfromTable(String teamTable) throws SQLException {
        int sumOfTable = 0;

        Connection db = DriverManager.getConnection("jdbc:sqlite:teamPlayers.db");
        PreparedStatement p = db.prepareStatement("SELECT SUM(ToPay) FROM " + teamTable);
        ResultSet r = p.executeQuery();
        while (r.next()) {
            int amount = r.getInt("SUM(ToPay)");
            sumOfTable = amount;
        }

        return sumOfTable;
    }

    /**
     * Returns the full Sum of all elements in column AllTime
     *
     * @param teamTable which table
     * @return Sum of AllTime column
     * @throws SQLException
     */
    @Override
    public int getSumAlltimeTable(String teamTable) throws SQLException {
        int sumOfTable = 0;
        Connection db = DriverManager.getConnection("jdbc:sqlite:teamPlayers.db");
        PreparedStatement p = db.prepareStatement("SELECT SUM(AllTime) FROM " + teamTable);
        ResultSet r = p.executeQuery();
        while (r.next()) {
            int amount = r.getInt("SUM(AllTime)");
            sumOfTable = amount;
        }

        return sumOfTable;
    }

    /**
     * Updates the Time column with given Time
     *
     * @param player which player
     * @param payboxname which team table
     * @param time time to uppdate
     * @throws SQLException
     */
    @Override
    public void uppdatePlayerTime(Player player, String payboxname,
            String time) throws SQLException {

        Connection db = DriverManager.getConnection("jdbc:sqlite:teamPlayers.db");
        PreparedStatement p = db.prepareStatement("UPDATE " + payboxname + " SET Time =? WHERE " + payboxname + ".Name=?");
        p.setString(1, time);
        p.setString(2, player.getname());
        p.executeUpdate();
    }
}
