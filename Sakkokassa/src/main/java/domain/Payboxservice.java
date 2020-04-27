package domain;

import dao.CashBoxdao;
import java.sql.SQLException;

/**
 * Payboxservice keeps track of all paybox related methods like login user and
 * create user.
 */
public class Payboxservice {

    private PayBoxTable loggedIn;
    private CashBoxdao userDao;

    public Payboxservice(CashBoxdao userDao) {
        this.userDao = userDao;
    }

    /**
     * method login checks if user exist if user exists it return true and login
     * user
     *
     * @param username
     *
     * @return true
     */
    public boolean login(String username) throws SQLException {
        PayBoxTable user = userDao.findByname(username);
        if (user == null) {
            return false;
        }
        loggedIn = user;
        return true;
    }

    /**
     * method returns logged user
     *
     * @param
     *
     * @return loggedUser
     */
    public PayBoxTable getLoggedUser() {
        return loggedIn;
    }

    public void logout() {
        loggedIn = null;
    }

    /**
     * method creates a user with password if it do not exist
     *
     * @param teamname user given team  
     *@param password user given password to team
     * @return loggedUser
     */
    public boolean createUser(String teamname, String password) throws SQLException {
        if (userDao.findByname(teamname) != null) {
            return false;
        }
        PayBoxTable user = new PayBoxTable(teamname, password);
        userDao.create(user);
        return true;
    }
}
