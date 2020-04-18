package dao;

import domain.Player;
import java.util.ArrayList;

public interface Playerdao {

    boolean addPlayer(Player player) throws Exception;

    ArrayList<Player> getAll(Player player);

    void uppdatePlayerAmmount(Player player) throws Exception;

}
