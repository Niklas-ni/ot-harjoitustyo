package domain;

import dao.CashBoxdao;

public class Payboxservice {

    private PayBoxTable loggedIn;
    private CashBoxdao userDao;

    public Payboxservice(CashBoxdao userDao) {
        this.userDao = userDao;
    }

    


    public boolean login(String username) {
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

   
    public boolean createUser(String teamname, String password) {
        if (userDao.findByname(teamname) != null) {
            return false;
        }
        PayBoxTable user = new PayBoxTable(teamname, password);
        try {
            userDao.create(user);
        } catch (Exception e) {
            return false;
        }

        return true;
    }
}
