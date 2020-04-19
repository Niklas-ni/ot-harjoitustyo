package domain;

import dao.CashBoxdao;
import java.sql.SQLException;

public class Payboxservice {

    private PayBoxTable loggedIn;
    private CashBoxdao userDao;

    public Payboxservice(CashBoxdao userDao) {
        this.userDao = userDao;
    }

    public boolean login(String username) throws SQLException {
        PayBoxTable user = userDao.findByname(username);
        if (user == null) {
            return false;
        }
        loggedIn = user;
        return true;
    }

    public PayBoxTable getLoggedUser() {
        return loggedIn;
    }

    public void logout() {
        loggedIn = null;
    }

    public boolean createUser(String teamname, String password) throws SQLException {
        if (userDao.findByname(teamname) != null) {
            return false;
        }
        PayBoxTable user = new PayBoxTable(teamname, password);
        userDao.create(user);
        return true;
    }
}
