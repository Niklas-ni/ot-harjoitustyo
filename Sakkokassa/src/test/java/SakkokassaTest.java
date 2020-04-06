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
    public void CreateKassa() throws SQLException {
        String team = "TPS";
        assertEquals(true, Sqlconnection.CreateSakkokassaTable(team));
     
    }
     @Test
    public void CreateDuplicateKassa() throws SQLException {
        String team = "HIFK";
        Sqlconnection.CreateSakkokassaTable(team);
         assertEquals(false ,Sqlconnection.CreateSakkokassaTable(team));
        
        
    }
    @Test
    public void AddPlayerWithoutToPayKassa() throws SQLException {
        String team = "IFK";
        String player = "Niklas";
        Sqlconnection.CreateSakkokassaTable(team);
        assertEquals(true, Sqlconnection.AddSakkokassaPlayer(team, player));
        
    }
    
}
