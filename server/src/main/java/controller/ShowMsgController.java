package controller;

import controller.exceptions.BlockedUser;
import model.DataBase;
import model.Message;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ShowMsgController
{
    private static ShowMsgController showMsgController;

    private ShowMsgController() {
    }
    public static ShowMsgController getShowMsgController()
    {
        if (showMsgController == null)
            showMsgController = new ShowMsgController();
        return showMsgController;
    }
    public void showMessage(Message message) throws SQLException, BlockedUser {
        String sqlCmd = String.format("SELECT isPV FROM accounts WHERE ID = '%s'", message.getSender());
        ResultSet rs1 = SQLConnection.getSqlConnection().executeSelect(sqlCmd);

        if (rs1 != null && rs1.next() && rs1.getBoolean("isPV"))
        {
            showMessagePV(message);
        }
        else{

        }
    }
    private void showMessagePV(Message message) throws SQLException, BlockedUser {
        String sqlCmd = String.format("SELECT blocked FROM blocklist WHERE blocker = '%s'", Thread.currentThread().getName());
        ResultSet rs1 = SQLConnection.getSqlConnection().executeSelect(sqlCmd);
        if(rs1!=null)
            while (rs1.next())
                if(rs1.getString("blocked").equals(message.getReceiver()))
                    throw new BlockedUser("user is blocked");

        sqlCmd = String.format("SELECT blocked FROM blocklist WHERE blocker = '%s'", message.getReceiver());
        rs1 = SQLConnection.getSqlConnection().executeSelect(sqlCmd);
        if(rs1!=null)
            while (rs1.next())
                if(rs1.getString("blocked").equals(Thread.currentThread().getName()))
                    throw new BlockedUser("user is blocked");

        String cmd2 = String.format("INSERT INTO massage (ID,massage,time,senderID,receiverID) VALUES (%s,'%s','%s','%s','%s')", getMaxId() + 1, message.getText(), message.getTime(), message.getSender(), message.getReceiver());
        SQLConnection.getSqlConnection().execute(cmd2);
        sqlCmd = String.format("SELECT isOnline,isPV,PVID FROM accounts WHERE ID = '%s'", message.getReceiver());
        rs1 = SQLConnection.getSqlConnection().executeSelect(sqlCmd);
        rs1.next();
        if(rs1.getBoolean("isOnline") && rs1.getBoolean("isPV") && message.getSender().equals(rs1.getString("PVID"))){
            DataBase.getDataBase().getThread(message.getReceiver()).setMessage(message.toString());
        }
    }
    private void setShowMessageGroup(Message message) throws SQLException {
        String cmd2 = String.format("INSERT INTO massage (ID,massage,time,senderID,receiverID) VALUES (%s,'%s','%s','%s','%s')", getMaxId() + 1, message.getText(), message.getTime(), message.getSender(), "group");
        SQLConnection.getSqlConnection().execute(cmd2);
        cmd2 = String.format("SELECT ID FROM accounts WHERE isOnline =%s AND isPV = %s", true, false);
        ResultSet rs2 = SQLConnection.getSqlConnection().executeSelect(cmd2);
        if (rs2 != null) {
            while (rs2.next())
            {
                DataBase.getDataBase().getThread(rs2.getString("ID")).setMessage(message.toString());
            }
        }
        cmd2 = String.format("SELECT ID FROM accounts WHERE isOnline =%s OR isPV = %s", false,true);
        rs2 = SQLConnection.getSqlConnection().executeSelect(cmd2);
        if (rs2 != null) {
            while (rs2.next())
            {
                cmd2 = String.format("INSERT INTO unseenMessage (massageID,accountID) Values (%s,'%s')",getMaxId(),rs2.getString("ID"));
                SQLConnection.getSqlConnection().execute(cmd2);
            }
        }
    }
    private int getMaxId() throws SQLException {
        ResultSet rs = SQLConnection.getSqlConnection().executeSelect("Select max(ID) from massage");
        if (rs != null && rs.next()) {
            return rs.getInt(1);
        }
        return 0;
    }
}
