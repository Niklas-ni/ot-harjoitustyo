package dao;

import domain.Player;
import domain.PayBoxTable;
import java.sql.SQLException;
import java.util.ArrayList;

public interface Playerdao {

    boolean addPlayer(Player player, String payboxname, String time) throws SQLException;

    ArrayList<String> getAll(String teamTable) throws SQLException;

    void uppdatePlayerAmmount(Player player, String payboxname) throws SQLException;

    int getSumfromTable(String payboxname) throws SQLException;

    int getSumAlltimeTable(String teamTable) throws SQLException;

    void uppdatePlayerAlltime(Player player, String payboxname) throws SQLException;

    void uppdatePlayerTime(Player player, String payboxname, String time) throws SQLException;

}
