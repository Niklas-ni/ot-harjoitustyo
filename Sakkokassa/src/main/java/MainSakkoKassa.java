
import java.sql.SQLException;

public class MainSakkoKassa{
 



    public static void main(String[] args) throws SQLException {
        String team = "TPS";
        String nimi = "Kalle";
        Sqlconnection.CreateSakkokassaTable(team);
        Sqlconnection.AddSakkokassaPlayer(team, nimi);
        Sqlconnection.AddSakkokassaPlayerMoney(team, "Isa", 20);
        Sqlconnection.AddSakkokassaPlayerMoney(team, "Miike", 100);
        Sqlconnection.SakkokassaPlayerNewPayment(team, nimi, 40);
        
    
    }
    
    
}
