package controller;

import model.DataBase;
import model.Message;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collections;

public class UnseenMessagesController {
    private static UnseenMessagesController unseenMessagesController;

    private UnseenMessagesController() {
    }

    public static UnseenMessagesController getUnseenMessagesController() {
        if(unseenMessagesController == null)
            unseenMessagesController = new UnseenMessagesController();
        return unseenMessagesController;
    }

    public void finishShowUnseenMessages(String ID) throws SQLException {
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
        sqlCmd = String.format("DELETE FROM unseenMessage WHERE accountID = '%s'",ID);
        SQLConnection.getSqlConnection().execute(sqlCmd);
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

    public void pvShowUnseenMessages(String receiverID) throws SQLException {
        ArrayList<Message> receiverMessage = new ArrayList<>();
        ArrayList<Message> senderMessage = new ArrayList<>();

        String sqlCmd = String.format("SELECT * FROM massage WHERE senderID = '%s' AND receiverID = '%s'", Thread.currentThread().getName(),receiverID);
        ResultSet resultSet = SQLConnection.getSqlConnection().executeSelect(sqlCmd);
        while (resultSet.next()) {
            senderMessage.add(new Message(resultSet.getString("massage"),resultSet.getString("senderID"),resultSet.getString("receiverID")));
            senderMessage.getLast().setTime(Time.valueOf(resultSet.getString("time")).toLocalTime());
        }

        sqlCmd = String.format("SELECT * FROM massage WHERE senderID = '%s' AND receiverID = '%s'",receiverID, Thread.currentThread().getName());
        resultSet = SQLConnection.getSqlConnection().executeSelect(sqlCmd);
        while (resultSet.next()) {
            receiverMessage.add(new Message(resultSet.getString("massage"),resultSet.getString("senderID"),resultSet.getString("receiverID")));
            receiverMessage.getLast().setTime(Time.valueOf(resultSet.getString("time")).toLocalTime());
        }

        ArrayList<Message> allMessage = new ArrayList<>(senderMessage);
        allMessage.addAll(receiverMessage);

        Collections.sort(allMessage);

        StringBuilder stringBuilder = new StringBuilder();
        for(Message message : allMessage){
            stringBuilder.append(message.toString());
        }

        DataBase.getDataBase().getThread(Thread.currentThread().getName()).setMessage(String.valueOf(stringBuilder));
    }
}
