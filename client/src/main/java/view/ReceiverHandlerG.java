package view;

import java.io.IOException;

public class ReceiverHandlerG {
    private static ReceiverHandlerG receiverHandlerG;

    private String chats;

    private ReceiverHandlerG() {
    }

    public static ReceiverHandlerG getReceiverHandlerG() {
        if(receiverHandlerG == null)
            receiverHandlerG = new ReceiverHandlerG();
        return receiverHandlerG;
    }

    public void handler(String message) throws IOException {
        signupCheck(message);
        loginCheck(message);
    }

    public String getChats() {
        return chats;
    }

    private void loginCheck(String message) throws IOException {
        switch (message){
            case "This id is not available" -> GHandler.getgHandler().setLoginExceptionID(true);
            case "Your password is not correct" -> GHandler.getgHandler().setLoginExceptionID(false);
            case "This user is already logged in" ->{
                GHandler.getgHandler().setLoggedInException(true);
                GHandler.getgHandler().setLoginExceptionID(false);
            }
            default ->{
                chats = message;
                View.getView().showMessenger();
            }
        }
    }
    private void signupCheck(String message) throws IOException {
        if (message.equals("This id exist")) {
        } else {
            chats = message;
            View.getView().showMessenger();
        }
    }
}
