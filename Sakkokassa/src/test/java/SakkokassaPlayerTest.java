
import dao.SqlPlayerdao;
import domain.PayBoxTable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import domain.Player;
import domain.PlayerService;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;

public class SakkokassaPlayerTest {

    public SakkokassaPlayerTest() {
    }

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
    private String cashboxdaotable = "payboxtable";
    private String team1 = "TPS";
    private String team2 = "HIfk";
    private String team3 = "IFK";
    private String team4 = "HPK";
    private String team5 = "FBC";
    private String team6 = "TOR";
    private String password = "password";

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() throws SQLException {
        String team1 = "TPS";
        String team2 = "HIfk";
        String team3 = "IFK";
        //String team4 = "HPK";
        //String team5 = "FBC";
        //String team6 = "TOR";
        Connection db = DriverManager.getConnection("jdbc:sqlite:teamPlayers.db");
        Statement player = db.createStatement();
        player.execute("DROP TABLE " + team1);
        player.execute("DROP TABLE " + team2);

        //sakkokassa.execute("DROP TABLE " + team4);
        //sakkokassa.execute("DROP TABLE " + team5);
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void createPlayer() throws SQLException {
        PayBoxTable test = new PayBoxTable(team1, password);
        Player testPlayer = new Player(team1, 22, team1);
    }

    @Test
    public void createPlayerWithoutAmmountMakesAmmount0() throws SQLException {
        PayBoxTable test = new PayBoxTable(team1, password);
        Player testPlayer = new Player(team1, 0);
        assertEquals(0, testPlayer.getAmmount());
    }

    @Test
    public void setAmmountMakesPlayerNewAmmount() throws SQLException {
        PayBoxTable test = new PayBoxTable(team1, password);
        Player testPlayer = new Player(team1, 0);
        testPlayer.setAmmount(22);
        assertEquals(22, testPlayer.getAmmount());
    }

    @Test
    public void playerGetName() throws SQLException {
        PayBoxTable test = new PayBoxTable(team1, password);
        Player testPlayer = new Player(team1, 0);
        assertEquals(team1, testPlayer.getname());
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
        Player testPlayer = new Player(team1, 0, team2);
        assertEquals(true, test1.addPlayer(testPlayer, team2));
    }
}
