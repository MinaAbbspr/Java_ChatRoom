package controller;

import controller.exceptions.IDNotFound;
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

    public void login(String ID, String password) throws IDNotFound, SQLException, WrongPassword {
        String sqlCmd = String.format("SELECT password FROM accounts WHERE ID = '%s'",ID);
        ResultSet resultSet = SQLConnection.getSqlConnection().executeSelect(sqlCmd);
        if(resultSet == null)
            throw new IDNotFound("This id is not available");

        if(!resultSet.getString("password").equals(password))
            throw new WrongPassword("Your password is not correct");

        DataBase.getDataBase().getThread(Thread.currentThread().getName()).setName(ID);
        Thread.currentThread().setName(ID);
        UnseenMessagesController.getUnseenMessagesController().loginShowUnseenMessages(ID);
    }
}
