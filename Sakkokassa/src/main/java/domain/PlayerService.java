package domain;

import dao.Playerdao;
import java.sql.SQLException;
import java.util.ArrayList;

public class PlayerService {

    private Playerdao playerDao;

    public PlayerService(Playerdao userDao) {
        this.playerDao = userDao;
    }

    public boolean addPlayer(Player player) throws SQLException {
        return playerDao.addPlayer(player);
    }

    public void uppdatePlayerAmmount(Player player) {
        try {
            playerDao.uppdatePlayerAmmount(player);

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    ArrayList<Player> getAll(Player player) throws SQLException {
        return playerDao.getAll(player);
    }

}
