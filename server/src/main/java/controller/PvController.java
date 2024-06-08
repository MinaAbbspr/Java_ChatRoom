package controller;

import controller.exceptions.IDNotFound;
import controller.exceptions.NoOnlineUser;
import model.Account;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PvController
{
    private static PvController pvController;

    private PvController() {}

    public static PvController getPvController() {
        if(pvController == null)
            pvController = new PvController();
        return pvController;
    }

    public StringBuilder showOnlineUsers() throws NoOnlineUser, SQLException {
        StringBuilder users = new StringBuilder();
        String sqlCmd = String.format("SELECT * FROM accounts WHERE isOnline = %s",1);
        ResultSet resultSet = SQLConnection.getSqlConnection().executeSelect(sqlCmd);
        if(resultSet == null)
            throw new NoOnlineUser("There is no online user");

        while (resultSet.next())
        {
            String ID = resultSet.getString("ID");
            if(!ID.equals(Thread.currentThread().getName()))
                users.append(ID).append(" @").append(resultSet.getString("name")).append("\n");
        }
        return users;
    }

    public void goToPV(String receiverID) throws SQLException {
        UnseenMessagesController.getUnseenMessagesController().pvShowUnseenMessages(receiverID);
        String innerCmd = String.format("UPDATE accounts SET isPV = %s, PVID = '%s' WHERE ID = '%s'",1,receiverID,Thread.currentThread().getName());
        SQLConnection.getSqlConnection().execute(innerCmd);
    }

}
