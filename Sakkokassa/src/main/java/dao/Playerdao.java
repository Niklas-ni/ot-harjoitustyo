package dao;

import domain.Player;
import domain.PayBoxTable;
import java.sql.SQLException;
import java.util.ArrayList;

public interface Playerdao {

    boolean addPlayer(Player player, String payboxname) throws SQLException;

    ArrayList<Player> getAll(String teamTable) throws SQLException;

    void uppdatePlayerAmmount(Player player, String payboxname) throws SQLException;

}
