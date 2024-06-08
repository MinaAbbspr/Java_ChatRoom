package view.graphic;

import view.HelloApplication;
import view.graphic.fxmlController.NewMessage;

import java.io.IOException;

public class ReceiverHandlerG {
    private static ReceiverHandlerG receiverHandlerG;

    private String saveMessage;
    private String commandSaver;

    private ReceiverHandlerG() {
        commandSaver = "PV";
    }

    public static ReceiverHandlerG getReceiverHandlerG() {
        if(receiverHandlerG == null)
            receiverHandlerG = new ReceiverHandlerG();
        return receiverHandlerG;
    }

    public void setCommandSaver(String commandSaver) {
        this.commandSaver = commandSaver;
    }

    public void handler(String message) throws IOException {
        if(! SenderHandlerG.getCommandHandlerG().isUse())
            switch (commandSaver){
                case "Login" -> loginCheck(message);
                case "Signup" -> signupCheck(message);
                case "PV" ->
                {}
                case "ShowOnline", "search", "finish", "Block" -> saveMessage = message;
                default ->NewMessage.getNewMessage().setMessages();
            }
        else {
            NewMessage.getNewMessage().setMessages();
        }
    }

    public String getSaveMessage() {
        return saveMessage;
    }

    private void loginCheck(String message) throws IOException {
        switch (message){
            case "This id is not available" -> GHandler.getgHandler().setLoginExceptionID(true);
            case "Your password is not correct" -> {
                GHandler.getgHandler().setLoginExceptionID(false);
                GHandler.getgHandler().setLoggedInException(false);
            }
            case "This user is already logged in" ->{
                GHandler.getgHandler().setLoggedInException(true);
                GHandler.getgHandler().setLoginExceptionID(false);
            }
            default ->
            {
                saveMessage = message;
                HelloApplication.setIsLogin(true);
            }
        }
    }
    private void signupCheck(String message) throws IOException
    {
        if (message.equals("This id exist")) {
        } else {
            saveMessage = message;
            HelloApplication.setIsLogin(true);
        }
    }
}
