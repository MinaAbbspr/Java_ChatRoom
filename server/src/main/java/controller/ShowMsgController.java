package controller;

import model.DataBase;
import model.Message;
import view.CommandHandler;

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
    public void showMessage(Message message) throws SQLException, ClassNotFoundException {
        String sqlCmd = String.format("SELECT isPV FROM accounts WHERE ID = '%s'", message.getSender());
        ResultSet rs1 = SQLConnection.getSqlConnection().executeSelect(sqlCmd);
        if (rs1 != null && rs1.next() && rs1.getBoolean("isPV"))
        {
            if (message.getReceiver() != null) {
                String sqlCmd2 = String.format("SELECT  FROM accounts WHERE ID = '%s'", message.getSender());
            }
        } else {
            String cmd2 = String.format("INSERT INTO massage (ID,massage,time,senderID,receiverID) VALUES (%s,'%s','%s','%s','%s')", getMaxId() + 1, message.getText(), message.getTime(), message.getSender(), "group");
            SQLConnection.getSqlConnection().execute(cmd2);
            cmd2 = String.format("SELECT ID FROM accounts WHERE isOnline =%s AND isPV = %s", true, false);
            ResultSet rs2 = SQLConnection.getSqlConnection().executeSelect(cmd2);
            if (rs2 != null) {
                while (rs2.next())
                {
                    DataBase.getDataBase().getThread(Thread.currentThread().getName()).setMessage(message.toString());
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
    }
    public int getMaxId() throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLConnection.getSqlConnection().executeSelect("Select max(ID) from massage");
        if (rs != null && rs.next()) {
            return rs.getInt(1);
        }
        return 0;
    }
}
