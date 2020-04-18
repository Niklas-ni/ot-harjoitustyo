
package dao;

import domain.PayBoxTable;
import java.util.ArrayList;


public interface CashBoxdao {
    
    boolean create(PayBoxTable teamstable);

    PayBoxTable findByname(String teamname);

    ArrayList<PayBoxTable> getAll();

}