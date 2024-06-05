package controller;

import controller.exceptions.IDNotFound;
import controller.exceptions.RepeatedAccount;
import controller.exceptions.WrongPassword;
import model.DataBase;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {
    private static LoginController loginController;

    private LoginController() {
    }

    public static LoginController getLoginController() {
        if(loginController == null)
            loginController = new LoginController();
        return loginController;
    }

    public boolean login(String ID, String password) throws IDNotFound, SQLException, WrongPassword, RepeatedAccount {
        String sqlCmd = String.format("SELECT password,isOnline FROM accounts WHERE ID = '%s'",ID);
        ResultSet resultSet = SQLConnection.getSqlConnection().executeSelect(sqlCmd);
        if(resultSet == null)
            throw new IDNotFound("This id is not available");

        resultSet.next();
        if(!resultSet.getString("password").equals(password))
            throw new WrongPassword("Your password is not correct");

        if(resultSet.getBoolean("isOnline")){
            throw new RepeatedAccount("some one login with this account");
        }

        DataBase.getDataBase().getThread(Thread.currentThread().getName()).setName(ID);
        Thread.currentThread().setName(ID);
        UnseenMessagesController.getUnseenMessagesController().loginShowUnseenMessages(ID);
        return true;
    }
}
