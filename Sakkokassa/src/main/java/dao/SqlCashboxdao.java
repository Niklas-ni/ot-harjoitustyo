package dao;

import domain.PayBoxTable;
import java.sql.*;
import java.util.ArrayList;
/**
 * SqlCashboxdao keeps track of teams and passwords and what teams are already made
 */
public class SqlCashboxdao implements CashBoxdao {

    private ArrayList<PayBoxTable> teams;

    public SqlCashboxdao() throws SQLException {
        teams = new ArrayList<>();
        try {
            Connection db = DriverManager.getConnection("jdbc:sqlite:teams.db");
            Statement sakkokassa = db.createStatement();
            sakkokassa.execute("CREATE TABLE payboxtable (Name TEXT UNIQUE, Password TEXT)");
            sakkokassa.execute("PRAGMA foreign_keys = ON");
        } catch (SQLException e) {
        }
    }

    @Override
    public boolean create(PayBoxTable teamstable) throws SQLException {
        Connection db = DriverManager.getConnection("jdbc:sqlite:teams.db");
        PreparedStatement p = db.prepareStatement("INSERT INTO payboxtable (Name,Password) VALUES (?,?)");
        p.setString(1, teamstable.getName());
        p.setString(2, teamstable.getPassword());
        p.executeUpdate();
        return true;
    }

    @Override
    public PayBoxTable findByname(String teamname) throws SQLException {
        PayBoxTable thistable = null;

        Connection db = DriverManager.getConnection("jdbc:sqlite:teams.db");
        PreparedStatement p = db.prepareStatement("SELECT Name,Password FROM payboxtable WHERE Name=? ");
        p.setString(1, teamname);
        ResultSet r = p.executeQuery();
        while (r.next()) {
            String name = r.getString("Name");
            String password = r.getString("Password");
            thistable = new PayBoxTable(name, password);
        }
        return thistable;
    }
}
