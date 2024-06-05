package controller;

import controller.exceptions.RepeatedID;
import model.Account;
import model.DataBase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Objects;


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
    public synchronized String signup(String ID,String name,String password) throws RepeatedID, SQLException
    {
        String sqlCmd = String.format("SELECT name FROM accounts WHERE ID = '%s'",ID);
        ResultSet resultSet = SQLConnection.getSqlConnection().executeSelect(sqlCmd);
        if(resultSet != null || ID.equals("group"))
        {
            throw new RepeatedID("This id already exist");
        }
        String innerCmd = String.format("INSERT INTO accounts (name,ID,password,isOnline,isPV) VALUES ('%s','%s','%s',%s,%s)",name,ID,password,true,false);
        SQLConnection.getSqlConnection().execute(innerCmd);
        DataBase.getDataBase().getThreadMap().put(ID,DataBase.getDataBase().getThreadMap().get(Thread.currentThread().getName()));
        DataBase.getDataBase().getThreadMap().remove(Thread.currentThread().getName());
        UnseenMessagesController.getUnseenMessagesController().signupShowUnseenMessages(ID);
        return "You signed up successfully";
    }
}
