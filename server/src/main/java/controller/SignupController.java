package controller;

import controller.exceptions.RepeatedID;
import model.Account;

import java.sql.ResultSet;
import java.sql.SQLException;


public class SignupController
{
    private Account account;
    private static SignupController signupController;

    private SignupController() {
    }
    public static SignupController getSignupController()
    {
        if (signupController == null)
            signupController = new SignupController();
        return signupController;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public static void setSignupController(SignupController signupController) {
        SignupController.signupController = signupController;
    }
    public String signup(String name,String id,String password) throws RepeatedID {
        String sqlCmd = String.format("SELECT name FROM accounts WHERE ID = '%s'",id);
        ResultSet resultSet = SQLConnection.getSqlConnection().executeSelect(sqlCmd);
        if(resultSet != null)
        {
            throw new RepeatedID("This id is not available");
        }
        String innerCmd = String.format("INSERT INTO accounts (name,ID,password,isOnline,isPV) VALUES ('%s','%s','%s',%s,%s)",name,id,password,true,false);
        SQLConnection.getSqlConnection().execute(innerCmd);
        return "You signed up successfully";
    }
}
