
package DomainAndDaotests;
import domain.Player;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class PlayerTest {
    
    String testplayer = "Niklas";
    @Test 
    public void addingNewPlayerWithoutTeam() {
    Player test = new Player(testplayer, 0);
    assertEquals(testplayer, test.getname());
    }
    
    @Test 
    public void addingNewPlayerWithoutTeamAndDiffrentAmmount() {
    Player test = new Player(testplayer, 22);
    assertEquals(22, test.getAmmount());
    }
    @Test 
    public void addingNewPlayerAndChangingAmount() {
    Player test = new Player(testplayer, 0);
    test.setAmmount(23);
    assertEquals(23, test.getAmmount());
    }
    

}
