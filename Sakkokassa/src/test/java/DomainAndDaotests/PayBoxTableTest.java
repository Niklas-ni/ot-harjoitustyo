package DomainAndDaotests;

import dao.SqlCashboxdao;
import domain.Payboxservice;
import java.sql.SQLException;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import domain.PayBoxTable;

public class PayBoxTableTest {

    private String testTeam = "Tps";
    private String password = "Password";

    public PayBoxTableTest() {
    }

    @Test
    public void MakingTableWorksAndReturnsName() {
        PayBoxTable testPaybox = new PayBoxTable(testTeam, password);
        assertEquals(testTeam, testPaybox.getName());
    }

    @Test
    public void MakingTableAndreturnsPassword() {
        PayBoxTable testPaybox = new PayBoxTable(testTeam, password);
        assertEquals(password, testPaybox.getPassword());
    }
}
