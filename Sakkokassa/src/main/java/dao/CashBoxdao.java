
package dao;

import domain.PayBoxTable;
import java.sql.SQLException;
import java.util.ArrayList;


public interface CashBoxdao {
    
    boolean create(PayBoxTable teamstable) throws SQLException;

    PayBoxTable findByname(String teamname) throws SQLException;

    ArrayList<PayBoxTable> getAll() throws  SQLException;

}