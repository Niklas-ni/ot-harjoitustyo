
import sql.Sqlconnection;
import domain.Kassa;
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
        assertEquals(true, Kassa.newcashbox(team));

    }

    @Test
    public void CreateDuplicateKassa() throws SQLException {
        String team = "HIFK";
        Kassa.newcashbox(team);
        assertEquals(false, Kassa.newcashbox(team));

    }

    @Test
    public void AddPlayerWithoutToPayKassa() throws SQLException {
        String team = "IFK";
        Kassa.isacashbox(team);
        assertEquals(true, Kassa.isacashbox(team));

    }

    @Test
    public void Addcashboxandpassword() throws SQLException {
        String team = "HPK";
        String password = "password";
        assertEquals(true, Kassa.newcashboxpassword(team, password));
    }

    @Test
    public void Checkpassword() throws SQLException {
        String team = "HPK";
        String password = "password";
        assertEquals(true, Kassa.checkpassword(team, password));
    }

}
