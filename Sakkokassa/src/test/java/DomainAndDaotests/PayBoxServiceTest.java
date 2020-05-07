package DomainAndDaotests;

import dao.SqlCashboxdao;
import domain.PayBoxTable;
import domain.Payboxservice;
import domain.PlayerService;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class PayBoxServiceTest {

    public PayBoxServiceTest() {
    }

    private String cashboxdaotable = "payboxtable";
    private String team1 = "TPS";
    private String team2 = "HIfk";
    private String password = "password";

    public static boolean tableExists(String nameOfTeam) throws SQLException {
        Connection db = DriverManager.getConnection("jdbc:sqlite:teams.db");
        DatabaseMetaData dbm = db.getMetaData();
        ResultSet tables = dbm.getTables(null, null, nameOfTeam, null);
        if (tables.next()) {
            db.close();
            return true;
        }
        db.close();
        return false;
    }

    public static boolean nameExistInTable(String nameOfTeam) throws SQLException {
        try {
            Connection db = DriverManager.getConnection("jdbc:sqlite:teams.db");
            PreparedStatement p = db.prepareStatement("SELECT Name FROM payboxtable WHERE Name=? ");
            p.setString(1, nameOfTeam);
            ResultSet r = p.executeQuery();
            while (r.next()) {

            }
        } catch (SQLException e) {
            System.out.println(e);
            return false;

        }
        return true;
    }

    @AfterClass
    public static void tearDownClass() throws SQLException {
        String cashboxDaotable = "payboxtable";
        String team1 = "TPS";

        Connection db = DriverManager.getConnection("jdbc:sqlite:teams.db");
        Statement payboxtable = db.createStatement();
        payboxtable.execute("DROP TABLE " + cashboxDaotable);
    }

    @Test
    public void tableMadeIfCalledUpOn() throws SQLException {
        SqlCashboxdao test = new SqlCashboxdao();
        Payboxservice test1 = new Payboxservice(test);
        assertEquals(true, tableExists(cashboxdaotable));
    }

    @Test
    public void tableStillexistIfCalledUpOn2Times() throws SQLException {
        SqlCashboxdao test = new SqlCashboxdao();
        Payboxservice test1 = new Payboxservice(test);
        SqlCashboxdao test2 = new SqlCashboxdao();
        Payboxservice test3 = new Payboxservice(test);
        assertEquals(true, tableExists(cashboxdaotable));
    }

    @Test
    public void cashBoxCreateUser() throws SQLException {
        SqlCashboxdao test = new SqlCashboxdao();
        Payboxservice test1 = new Payboxservice(test);
        test1.createUser(team1, password);
        assertEquals(true, nameExistInTable(team1));
    }

    @Test
    public void cashBoxcantCreatedubble() throws SQLException {
        SqlCashboxdao test = new SqlCashboxdao();
        Payboxservice test1 = new Payboxservice(test);
        test1.createUser(team1, password);
        assertEquals(false, test1.createUser(team1, password));
    }

    @Test
    public void loginExistingPaybox() throws SQLException {
        SqlCashboxdao test = new SqlCashboxdao();
        Payboxservice test1 = new Payboxservice(test);
        test1.createUser(team1, password);
        assertEquals(true, test1.login(team1));
    }

    @Test
    public void loginNotPossibleIfNotExisting() throws SQLException {
        SqlCashboxdao test = new SqlCashboxdao();
        Payboxservice test1 = new Payboxservice(test);
        test1.createUser(team1, password);
        assertEquals(false, test1.login(team2));
    }

    @Test
    public void getLoggedinreturnsright() throws SQLException {
        SqlCashboxdao test = new SqlCashboxdao();
        Payboxservice test1 = new Payboxservice(test);
        test1.createUser(team1, password);
        test1.login(team1);
        assertEquals(team1, test1.getLoggedUser().getName());
    }

    @Test
    public void afterLogOutGetLoginNull() throws SQLException {
        SqlCashboxdao test = new SqlCashboxdao();
        Payboxservice test1 = new Payboxservice(test);
        test1.createUser(team1, password);
        PayBoxTable loggedin = test1.getLoggedUser();
        test1.logout();
        assertEquals(null, test1.getLoggedUser());
    }

    @Test
    public void ifNooneLoggedinGetLoginreturnnull() throws SQLException {
        SqlCashboxdao test = new SqlCashboxdao();
        Payboxservice test1 = new Payboxservice(test);
        test1.createUser(team1, password);
        assertEquals(null, test1.getLoggedUser());
    }

    @Test
    public void payboxtableGetName() throws SQLException {
        PayBoxTable test = new PayBoxTable(team1, password);
        assertEquals(team1, test.getName());
    }

    @Test
    public void payboxtableGetPassword() throws SQLException {
        PayBoxTable test = new PayBoxTable(team1, password);
        assertEquals(password, test.getPassword());
    }

    @Test
    public void findNonExisting() throws SQLException {
        SqlCashboxdao test = new SqlCashboxdao();
        Payboxservice test1 = new Payboxservice(test);
        assertEquals(null, test.findByname("notExisting"));
    }

    @Test
    public void teamnameWithNumberFirstNotPossible() throws SQLException {
        SqlCashboxdao test = new SqlCashboxdao();
        Payboxservice test1 = new Payboxservice(test);
        assertEquals(false, test1.createUser("2NotPossible", password));
    }

    @Test
    public void teamnameEmptyOrWhiteSpaceReturnFalse() throws SQLException {
        SqlCashboxdao test = new SqlCashboxdao();
        Payboxservice test1 = new Payboxservice(test);
        assertEquals(false, test1.createUser("  ", password));
    }
}
