package controller;

import java.sql.SQLException;

public class FinishController
{
    private static FinishController finishController;

    private FinishController() {}

    public static FinishController getFinishContrller() {
        if(finishController == null)
            finishController = new FinishController();
        return finishController;
    }
    public void finish() throws SQLException {
        String innerCmd = String.format("UPDATE accounts SET isPV = %s WHERE ID = '%s'",0,Thread.currentThread().getName());
        SQLConnection.getSqlConnection().execute(innerCmd);
        UnseenMessagesController.getUnseenMessagesController().finishShowUnseenMessages(Thread.currentThread().getName());
    }
}
