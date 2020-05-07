/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DomainAndDaotests;

import dao.SqlPlayerdao;
import domain.PayBoxTable;
import domain.Player;
import domain.PlayerService;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author niri91
 */
public class PlayerServiceTest {

    public static boolean tableExists(String nameOfTeam) throws SQLException {
        Connection db = DriverManager.getConnection("jdbc:sqlite:teamPlayers.db");
        DatabaseMetaData dbm = db.getMetaData();
        ResultSet tables = dbm.getTables(null, null, nameOfTeam, null);
        if (tables.next()) {
            db.close();
            return true;
        }
        db.close();
        return false;
    }

    public String getTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }
    private String cashboxdaotable = "payboxtable";
    private String password = "Password";
    private String team1 = "TPS";
    private String team2 = "HIfk";
    private String team3 = "IFK";
    private String team4 = "FBC";
    private String team5 = "HPK";
    private String team8 = "TOR";
    private String team9 = "SBS";
    private String team10 = "EHT";

    public PlayerServiceTest() {
    }

    @AfterClass
    public static void tearDownClass() throws SQLException {
        String team1 = "TPS";
        String team2 = "HIfk";
        String team3 = "IFK";
        String team4 = "FBC";
        String team5 = "HPK";
        String team8 = "TOR";
        String team9 = "SBS";
        String team10 = "EHT";
        
        Connection db = DriverManager.getConnection("jdbc:sqlite:teamPlayers.db");
        Statement player = db.createStatement();
        player.execute("DROP TABLE " + team1);
        player.execute("DROP TABLE " + team2);
        player.execute("DROP TABLE " + team3);
        player.execute("DROP TABLE " + team4);
        player.execute("DROP TABLE " + team5);
        player.execute("DROP TABLE " + team8);
        player.execute("DROP TABLE " + team9);
        player.execute("DROP TABLE " + team10);
    }

    @Test
    public void playerNewTeamMakesTable() throws SQLException {
        SqlPlayerdao test = new SqlPlayerdao(team1);
        PlayerService test1 = new PlayerService(test);
        assertEquals(true, tableExists(team1));
    }

    @Test
    public void playerTeamaddplayer() throws SQLException {
        SqlPlayerdao test = new SqlPlayerdao(team2);
        PlayerService test1 = new PlayerService(test);
        PayBoxTable testPayBox = new PayBoxTable(team2, password);
        Player testPlayer = new Player(team1, 0);
        assertEquals(true, test1.addPlayer(testPlayer, team2));
    }

    @Test
    public void ifPlayerExistAmmountUppdates() throws SQLException {
        SqlPlayerdao test = new SqlPlayerdao(team2);
        PlayerService test1 = new PlayerService(test);
        PayBoxTable testPayBox = new PayBoxTable(team2, password);
        Player testPlayer = new Player(team1, 22);
        Player testPlayer1 = new Player(team1, 28);
        test1.addPlayer(testPlayer1, team2);
        assertEquals(true, test1.addPlayer(testPlayer, team2));
    }

    @Test
    public void playersGetAll() throws SQLException {
        SqlPlayerdao test = new SqlPlayerdao(team1);
        PlayerService test1 = new PlayerService(test);
        PayBoxTable testPayBox = new PayBoxTable(team1, password);
        Player test4 = new Player(team1, 33);
        test1.addPlayer(test4, team1);
        ArrayList<String> testprint = new ArrayList<>();
        testprint.add("TPS ToPay: 33 AllTime: 33 Euros. Last Uppdate " + getTime());
        assertEquals(testprint, test1.getAll(team1));
    }

    @Test
    public void playersGetAllIsEmpty() throws SQLException {
        SqlPlayerdao test = new SqlPlayerdao(team1);
        PlayerService test1 = new PlayerService(test);
        String team6 = "nonExiting";
        PayBoxTable testPayBox = new PayBoxTable(team1, password);
        ArrayList<String> testprint = new ArrayList<>();
        testprint.add("empty");
        assertEquals(testprint, test1.getAll(team6));
    }

    @Test
    public void playersUppdate() throws SQLException {
        SqlPlayerdao test = new SqlPlayerdao(team3);
        PlayerService test1 = new PlayerService(test);
        PayBoxTable testPayBox = new PayBoxTable(team3, password);
        Player test4 = new Player(team3, 33);
        test1.addPlayer(test4, team3);
        test4.setAmmount(55);
        test1.uppdatePlayerAmmount(test4, team3);
        ArrayList<String> testprint = new ArrayList<>();
        testprint.add("IFK ToPay: 88 AllTime: 88 Euros. Last Uppdate " + getTime());
        assertEquals(testprint, test1.getAll(team3));
    }

    @Test
    public void playersgetSum() throws SQLException {
        SqlPlayerdao test = new SqlPlayerdao(team4);
        PlayerService test1 = new PlayerService(test);
        PayBoxTable testPayBox = new PayBoxTable(team4, password);
        Player test4 = new Player(team4, 33);
        test1.addPlayer(test4, team4);
        Player test5 = new Player(team3, 4);
        test1.addPlayer(test5, team4);
        assertEquals(37, test1.getSumfromTable(team4));
    }

    @Test
    public void playersgetSumAlltime() throws SQLException {
        SqlPlayerdao test = new SqlPlayerdao(team5);
        PlayerService test1 = new PlayerService(test);
        PayBoxTable testPayBox = new PayBoxTable(team5, password);
        Player test4 = new Player(team5, 33);
        test1.addPlayer(test4, team5);
        Player test5 = new Player(team3, 4);
        test1.addPlayer(test5, team5);
        assertEquals(37, test1.getSumfromAlLTimeTable(team5));
    }

    @Test
    public void playersToPayReduceWithSubstractionAlltimeStays() throws SQLException {
        SqlPlayerdao test = new SqlPlayerdao(team8);
        PlayerService test1 = new PlayerService(test);
        PayBoxTable testPayBox = new PayBoxTable(team8, password);
        Player test4 = new Player(team3, 33);
        test1.addPlayer(test4, team8);
        Player test5 = new Player(team3, -33);
        test1.addPlayer(test5, team8);
        ArrayList<String> testprint = new ArrayList();
        testprint.add("IFK ToPay: 0 AllTime: 33 Euros. Last Uppdate " + getTime());
        assertEquals(testprint, test1.getAll(team8));
    }

    @Test
    public void playersToPayReduceWithGivenNumberPossibleAlltimeStays() throws SQLException {
        SqlPlayerdao test = new SqlPlayerdao(team9);
        PlayerService test1 = new PlayerService(test);
        PayBoxTable testPayBox = new PayBoxTable(team9, password);
        Player test4 = new Player(team3, 33);
        test1.addPlayer(test4, team9);
        Player test5 = new Player(team3, -23);
        test1.addPlayer(test5, team9);
        ArrayList<String> testprint = new ArrayList();
        testprint.add("IFK ToPay: 10 AllTime: 33 Euros. Last Uppdate " + getTime());
        assertEquals(testprint, test1.getAll(team9));
    }

    @Test
    public void playersUppdateWithNegativeChangesToPay() throws SQLException {
        SqlPlayerdao test = new SqlPlayerdao(team10);
        PlayerService test1 = new PlayerService(test);
        PayBoxTable testPayBox = new PayBoxTable(team10, password);
        Player test4 = new Player(team3, 33);
        test1.addPlayer(test4, team10);
        Player test8 = new Player(team3, -33);
        test1.uppdatePlayerAmmount(test8, team10);
        ArrayList<String> testprint = new ArrayList();
        testprint.add("IFK ToPay: 0 AllTime: 33 Euros. Last Uppdate " + getTime());
        assertEquals(testprint, test1.getAll(team10));
    }
}
