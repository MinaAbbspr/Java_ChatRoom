package view;

public class ReceiverHandlerG {
    private static ReceiverHandlerG receiverHandlerG;

    private ReceiverHandlerG() {
    }

    public static ReceiverHandlerG getReceiverHandlerG() {
        if(receiverHandlerG == null)
            receiverHandlerG = new ReceiverHandlerG();
        return receiverHandlerG;
    }

    public void handler(String message){
        signupCheck(message);
        loginCheck(message);
    }
    private void loginCheck(String message){
        switch (message){
            case "This id is not available" -> GHandler.getgHandler().setLoginExceptionID(true);
            case "Your password is not correct" -> GHandler.getgHandler().setLoginExceptionID(false);
            case "This user is already logged in" ->{
                GHandler.getgHandler().setLoggedInException(true);
                GHandler.getgHandler().setLoginExceptionID(false);
            }
            default ->{//////open chat
            }
        }
    }
    private void signupCheck(String message){
        switch (message){
            case "This id exist" ->{}
            default ->{////// open chat
            }
        }
    }
}
