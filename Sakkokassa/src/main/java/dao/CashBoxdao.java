package dao;

import domain.PayBoxTable;
import java.sql.SQLException;

public interface CashBoxdao {

    boolean create(PayBoxTable teamstable) throws SQLException;

    PayBoxTable findByname(String teamname) throws SQLException;

}
