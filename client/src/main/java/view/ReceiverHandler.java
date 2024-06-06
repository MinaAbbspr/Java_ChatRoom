package view;

public class ReceiverHandler {
    private static ReceiverHandler receiverHandler;

    public ReceiverHandler() {
    }

    public static ReceiverHandler getReceiverHandler() {
        if(receiverHandler == null)
            receiverHandler = new ReceiverHandler();
        return receiverHandler;
    }

    public boolean handler(String message){
        if (message.equals("exit")) {
            System.out.println("Good bye");
            return true;
        }else if(message.equals("ping")){
            long endTime = System.nanoTime();
            System.out.println("ping: " + (endTime- SenderHandler.getStartTime())/1000000 + "ms");
        }
        else {
            System.out.println(message);
        }
        return false;
    }
}
