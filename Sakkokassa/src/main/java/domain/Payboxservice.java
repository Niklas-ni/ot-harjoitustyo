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
     * @param username user chosen to login
     *
     * @return true
     * @throws java.sql.SQLException
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
     * 
     *
     * @return loggedUser
     */
    public PayBoxTable getLoggedUser() {
        return loggedIn;
    }

    /**
     * method setts loggedin user to null loggedIn = null;
     */
    public void logout() {
        loggedIn = null;
    }

    /**
     * method creates a user with password if it do not exist Also return false
     * if incorrect user. starts with number.
     *
     * @param teamname user given team (not a number)
     * @param password user given password to team
     * @return true
     * @throws java.sql.SQLException
     */
    public boolean createUser(String teamname, String password) throws SQLException {
        if (teamname.substring(0, 1).matches("[0-9]")) {
            return false;
        } else if (userDao.findByname(teamname) != null) {
            return false;
        } else {
            PayBoxTable user = new PayBoxTable(teamname, password);
            userDao.create(user);
            return true;
        }
    }
}
