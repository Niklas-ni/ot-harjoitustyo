
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class SakkokassaTest {

    public SakkokassaTest() {
    }
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
        /*String team1 = "TPS";
        String team2 = "HIfk";
        String team3 = "IFK";
        String team4 = "HPK";
        String team5 = "FBC";
        String team6 = "TOR";
        Connection db = DriverManager.getConnection("jdbc:sqlite:testi.db");
        Statement sakkokassa = db.createStatement();
        sakkokassa.execute("DROP TABLE " + team1);
        sakkokassa.execute("DROP TABLE " + team2);
        sakkokassa.execute("DROP TABLE " + team3);
        sakkokassa.execute("DROP TABLE " + team4);
        sakkokassa.execute("DROP TABLE " + team5);
         */

    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

}
