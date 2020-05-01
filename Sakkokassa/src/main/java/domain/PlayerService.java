package domain;

import dao.Playerdao;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Playerservice keeps track of all Player related methods like - addPlayer and
 * - getAll - uppdateplayer -.getsumfromtable - getalltimesum
 */
public class PlayerService {

    private Playerdao playerDao;

    public PlayerService(Playerdao userDao) {
        this.playerDao = userDao;
    }

    /**
     * method add player adds a player to given team table if it does not exist
     * if it exist it calls uppdatePlayerAmmountmethod
     *
     *
     * @param player users chosen player to be added
     * @param payboxname users logged in table where player will be added
     * @return true
     *
     *
     * @throws java.sql.SQLException
     */
    public boolean addPlayer(Player player, String payboxname) throws SQLException {
        String time = getTime();
        if (playerDao.addPlayer(player, payboxname, time)) {
            return true;
        } else {
            uppdatePlayerAmmount(player, payboxname);
        }
        return true;
    }

    /**
     * method updates chosen players amount if the amount to udate is less than
     * 0 method only reduces from ToPay column. if update is greater than 0
     * method updates ToPay column and AllTime column
     *
     *
     * @param player users chosen player to have to pay updated
     * @param payboxname from witch table the player should be found
     * @throws java.sql.SQLException
     *
     *
     */
    public void uppdatePlayerAmmount(Player player, String payboxname) throws SQLException {
        String time = getTime();
        if (player.getAmmount() < 0) {
            playerDao.uppdatePlayerAmmount(player, payboxname);
            playerDao.uppdatePlayerTime(player, payboxname, time);
        } else {
            playerDao.uppdatePlayerAmmount(player, payboxname);
            playerDao.uppdatePlayerAlltime(player, payboxname);
            playerDao.uppdatePlayerTime(player, payboxname, time);

        }
    }

    /**
     * Adds to a list with string players and what they should pay If it does
     * not exist returns a List with just the word empty
     *
     *
     *
     * @param payboxname user chosen table from where to get all players and
     * amounts
     * @return a List with all information
     * @throws java.sql.SQLException
     *
     *
     */
    public ArrayList<String> getAll(String payboxname) throws SQLException {
        ArrayList players = new ArrayList<>();
        if (playerDao.getAll(payboxname).isEmpty()) {
            players.add("empty");
            return players;
        }

        return playerDao.getAll(payboxname);
    }

    /**
     * Returns The sum of every amount int ToPay column for this team.
     *
     * @param payboxname From witch table to get SUM of All toPays
     *
     * @return Integer SUM(ToPay)
     * @throws java.sql.SQLException
     *
     *
     */
    public int getSumfromTable(String payboxname) throws SQLException {
        return playerDao.getSumfromTable(payboxname);

    }

    /**
     * Returns The sum of every amount int ALLtime column for this team.
     *
     * @param payboxname From witch table to get SUM of All toPays
     *
     * @return Integer SUM(AllTime)
     * @throws java.sql.SQLException
     *
     *
     */
    public int getSumfromAlLTimeTable(String payboxname) throws SQLException {
        return playerDao.getSumAlltimeTable(payboxname);
    }

    /**
     * asks the current time
     *
     * @return the currentTime
     *
     */

    public String getTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

}
