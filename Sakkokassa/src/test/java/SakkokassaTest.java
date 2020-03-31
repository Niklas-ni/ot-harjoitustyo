import KassaSql.Sqlconnection;
import java.sql.SQLException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class SakkokassaTest {

    public SakkokassaTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void CreateKassaPrintPlayers() throws SQLException {
        String team = "TPS";
        String player = "Kalle";
        assertEquals("Done!", Sqlconnection.CreateSakkokassaTable(team));
        assertEquals("Player Added",Sqlconnection.AddSakkokassaPlayer(team, player));
     }
}
