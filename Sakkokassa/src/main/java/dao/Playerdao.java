package dao;

import domain.Player;
import java.sql.SQLException;
import java.util.ArrayList;

public interface Playerdao {

    boolean addPlayer(Player player) throws SQLException;

    ArrayList<Player> getAll(Player player) throws SQLException;

    void uppdatePlayerAmmount(Player player) throws SQLException;

}
