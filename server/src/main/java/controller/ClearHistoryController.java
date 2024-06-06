package controller;

import model.Message;

public class ClearHistoryController
{
    private static ClearHistoryController clearHistoryController;

    private ClearHistoryController() {
    }

    public static ClearHistoryController getClearHistoryController() {
        if(clearHistoryController == null)
            clearHistoryController = new ClearHistoryController();
        return clearHistoryController;
    }
    public String clearHistory(Message message)
    {
        String sqlCmd = String.format("DELETE FROM massage WHERE senderID = '%s' AND receiverID = '%s'", message.getSender(),message.getReceiver());
        SQLConnection.getSqlConnection().execute(sqlCmd);
        sqlCmd = String.format("DELETE FROM massage WHERE senderID = '%s' AND receiverID = '%s'", message.getReceiver(),message.getSender());
        SQLConnection.getSqlConnection().execute(sqlCmd);
        return "messages deleted successfully";
    }
}
