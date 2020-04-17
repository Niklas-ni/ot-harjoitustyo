
package dao;

import domain.PayBoxTable;
import java.util.Set;


public interface CashBoxdao {
    boolean createPayBoxTable();

    boolean insertPayBoxTable(PayBoxTable payboxtable);

    boolean deletePayboxtable(PayBoxTable payboxtable);
    
    String getpasswordPayboxtable(PayBoxTable payBoxTable);
    
    
}