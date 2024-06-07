package controller;

import controller.exceptions.NoOnlineUser;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ShowUsersController
{
    private static ShowUsersController showUsersController;

    private ShowUsersController() {}

    public static ShowUsersController getShowUsersController() {
        if(showUsersController == null)
            showUsersController = new ShowUsersController();
        return showUsersController;
    }
    public StringBuilder showAllTheUsers() throws NoOnlineUser, SQLException {
        StringBuilder users = new StringBuilder();
        String sqlCmd = String.format("SELECT * FROM accounts");
        ResultSet resultSet = SQLConnection.getSqlConnection().executeSelect(sqlCmd);
        if(resultSet == null)
            throw new NoOnlineUser("There is no user yetp-[");
        while (resultSet.next())
        {
            String ID = resultSet.getString("ID");
            if(!ID.equals(Thread.currentThread().getName()))
                users.append(ID).append("\t@").append(resultSet.getString("name")).append("\n");
        }
        return users;
    }
}
