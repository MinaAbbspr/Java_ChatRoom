package view;

import java.io.IOException;

public class ReceiverHandlerG {
    private static ReceiverHandlerG receiverHandlerG;

    private String saveMessage;

    private ReceiverHandlerG() {
    }

    public static ReceiverHandlerG getReceiverHandlerG() {
        if(receiverHandlerG == null)
            receiverHandlerG = new ReceiverHandlerG();
        return receiverHandlerG;
    }

    public void handler(String message) throws IOException {
        if(SenderHandlerG.getCommandHandlerG().isUse())
            switch (SenderHandlerG.getCommandHandlerG().getCommandSaver()){
                case "Login" -> loginCheck(message);
                case "Signup" -> signupCheck(message);
                case "PV" ->{
                }
                case "ShowOnline", "search", "finish", "Block" -> saveMessage = message;
                default -> {
                    ///notify update new message
                }
            }
        else {
            ///notify update new message
        }
    }

    public String getSaveMessage() {
        return saveMessage;
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
                saveMessage = message;
//                View.getView().showMessenger();
            }
        }
    }
    private void signupCheck(String message) throws IOException
    {
        if (message.equals("This id exist")) {
        } else {
            saveMessage = message;
//            View.getView().showMessenger();
        }
    }
}
