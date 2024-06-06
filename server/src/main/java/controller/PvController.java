package controller;

import controller.exceptions.IDNotFound;
import controller.exceptions.NoOnlineUser;
import model.Account;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PvController
{
    private String senderID;
    private String receiverID;

    public String getSenderID() {
        return senderID;
    }

    public void setSenderID(String senderID) {
        this.senderID = senderID;
    }

    public String getReceiverID() {
        return receiverID;
    }

    public void setReceiverID(String receiverID) {
        this.receiverID = receiverID;
    }
    public StringBuilder showOnlineUsers() throws NoOnlineUser, SQLException {
        StringBuilder users = new StringBuilder();
        String sqlCmd = String.format("SELECT * FROM accounts WHERE isOnline = %s",1);
        ResultSet resultSet = SQLConnection.getSqlConnection().executeSelect(sqlCmd);
        if(resultSet == null)
            throw new NoOnlineUser("There is no online user");

        while (resultSet.next())
        {
            users.append(resultSet.getString("ID")).append("\t").append(resultSet.getString("name"));
        }
        return users;
    }

}
