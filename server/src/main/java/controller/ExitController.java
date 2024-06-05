package controller;

public class ExitController
{
    private static ExitController exitController;

    private ExitController() {
    }

    public static ExitController getExitController() {
        if(exitController == null)
            exitController = new ExitController();
        return exitController;
    }
    public void exitFromAccount()
    {
        String innerCmd = String.format("UPDATE accounts SET isOnline = %s WHERE ID = '%s'",false,Thread.currentThread().getName());
        SQLConnection.getSqlConnection().execute(innerCmd);
    }
}
