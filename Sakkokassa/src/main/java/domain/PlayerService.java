package domain;

import dao.Playerdao;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Playerservice keeps track of all Player related methods like addPlayer and
 * getAll.
 */
public class PlayerService {

    private Playerdao playerDao;

    public PlayerService(Playerdao userDao) {
        this.playerDao = userDao;
    }

    /**
     * method add player adds a player to given team table if it does not exist
     * if it exist it updates the players amount
     *
     *
     * @param player users chosen player to be added
     * @param payboxname users logged in table where player will be added
     *
     * @return true
     */
    public boolean addPlayer(Player player, String payboxname) throws SQLException {
        if (playerDao.addPlayer(player, payboxname)) {
            return true;
        } else {
            playerDao.uppdatePlayerAmmount(player, payboxname);
        }
        return true;
    }

    /**
     * method updates player amount in given table
     *
     *
     *
     * @param player users chosen player to have to pay updated
     * @param payboxname from witch table the player should be found
     *
     *
     */
    public void uppdatePlayerAmmount(Player player, String payboxname) {
        try {
            playerDao.uppdatePlayerAmmount(player, payboxname);
        } catch (SQLException e) {
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
     *
     *
     */

    public ArrayList<String> getAll(String payboxname) throws SQLException {
        ArrayList<String> players = new ArrayList();
        if (playerDao.getAll(payboxname).isEmpty()) {
            players.add("empty");
            return players;
        }
        for (Player player : playerDao.getAll(payboxname)) {
            String nameammount = player.getname() + " To Pay: " + player.getAmmount() + " Euros";
            players.add(nameammount);

        }
        return players;
    }

}
