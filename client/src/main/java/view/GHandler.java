package view;

public class GHandler {
    private static GHandler gHandler;
    private boolean loginExceptionID;
    private boolean loggedInException;

    public GHandler() {
        loginExceptionID = false;
        loggedInException = false;
    }

    public static GHandler getgHandler() {
        if(gHandler == null)
            gHandler = new GHandler();
        return gHandler;
    }

    public void setLoginExceptionID(boolean loginExceptionID) {
        this.loginExceptionID = loginExceptionID;
        synchronized (this){
            this.notify();
        }
    }

    public boolean isLoggedInException() {
        return loggedInException;
    }

    public void setLoggedInException(boolean loggedInException) {
        this.loggedInException = loggedInException;
    }

    public void senderAndReceiverConnector(){
    }
    public boolean login(String message) throws InterruptedException {
        SenderHandlerG.getCommandHandlerG().setCommand(message);

        synchronized (this){
            this.wait();
        }

        return loginExceptionID;
    }
    public void signup(String message) {
        SenderHandlerG.getCommandHandlerG().setCommand(message);
    }
}
