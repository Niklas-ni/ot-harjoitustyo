package domain;

import dao.Playerdao;
import java.sql.SQLException;
import java.util.ArrayList;

public class PlayerService {

    private Playerdao playerDao;

    public PlayerService(Playerdao userDao) {
        this.playerDao = userDao;
    }

    public boolean addPlayer(Player player, String payboxname) throws SQLException {
         if (playerDao.addPlayer(player, payboxname)){
             return true;
         } else
        playerDao.uppdatePlayerAmmount(player, payboxname);
         return true; 
    }

    public void uppdatePlayerAmmount(Player player, String payboxname) {
        try {
            playerDao.uppdatePlayerAmmount(player, payboxname);

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public ArrayList<String> getAll(String payboxname) throws SQLException {
        ArrayList<String> players = new ArrayList();
        if (playerDao.getAll(payboxname).isEmpty()){
            players.add("empty");
            return players;
        }
        for (Player player : playerDao.getAll(payboxname)) {
           String nameammount = player.getname() + " To Pay: " + player.getAmmount()+ " Euros";
           players.add(nameammount);
        
        }
        return players;
    }

}
