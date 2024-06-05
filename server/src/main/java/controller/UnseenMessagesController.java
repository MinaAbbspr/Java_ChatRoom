package controller;

import model.DataBase;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UnseenMessagesController {
    private static UnseenMessagesController unseenMessagesController;

    private UnseenMessagesController() {
    }

    public static UnseenMessagesController getUnseenMessagesController() {
        if(unseenMessagesController == null)
            unseenMessagesController = new UnseenMessagesController();
        return unseenMessagesController;
    }

    public void loginShowUnseenMessages(String ID) throws SQLException {
        StringBuilder stringBuilder = new StringBuilder();
        String sqlCmd = String.format("SELECT messageID FROM unseenMessage WHERE accountID = '%s'",ID);
        ResultSet resultSet = SQLConnection.getSqlConnection().executeSelect(sqlCmd);
        if(resultSet != null){
            while (resultSet.next()) {
                sqlCmd = String.format("SELECT * FROM massage WHERE ID = %s", resultSet.getInt("messageID"));
                ResultSet messages = SQLConnection.getSqlConnection().executeSelect(sqlCmd);
                messages.next();
                stringBuilder.append(messages.getString("senderID")).append("\n")
                        .append(messages.getString("massage")).append("\n").append(messages.getString("time")).append("\n");
            }
        }

        if(stringBuilder.isEmpty())
            stringBuilder.append("there is no unseen message");
        DataBase.getDataBase().getThread(Thread.currentThread().getName()).setMessage(String.valueOf(stringBuilder));

    }
    public void signupShowUnseenMessages(String ID) throws SQLException {
        StringBuilder unseenMessages = new StringBuilder();
        String sqlCmd = String.format("SELECT * FROM massage WHERE receiverID = '%s'", "group");
        ResultSet messages = SQLConnection.getSqlConnection().executeSelect(sqlCmd);
        if(messages != null)
        {
            while (messages.next())
            {
                unseenMessages.append(messages.getString("senderID")).append("\n")
                        .append(messages.getString("massage")).append("\n").append(messages.getString("time")).append("\n");
            }
        }
        if(unseenMessages.isEmpty())
            unseenMessages.append("there is no message");
        DataBase.getDataBase().getThread(Thread.currentThread().getName()).setMessage(String.valueOf(unseenMessages));
    }
}
