package domain;

import dao.Playerdao;
import java.util.ArrayList;

public class PlayerService {

    private Playerdao playerDao;

    public PlayerService(Playerdao userDao) {
        this.playerDao = userDao;
    }

    public boolean addPlayer(Player player) {
        try {
            playerDao.addPlayer(player);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    public void uppdatePlayerAmmount(Player player) {
        try {
            playerDao.uppdatePlayerAmmount(player);

        } catch (Exception ex) {
            System.out.println(ex);
        }

    }
    ArrayList<Player> getAll(Player player){
       return playerDao.getAll(player);
    }

}
