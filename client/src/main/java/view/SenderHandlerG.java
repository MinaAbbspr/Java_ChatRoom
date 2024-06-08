package view;

import model.Message;

import java.io.File;


public class SenderHandlerG {
    private String senderID;
    private String receiverID;
    private String command;

    private boolean isUse;
    private static CommunicationHandlerSender sender;
    private static SenderHandlerG senderHandlerG;

    private SenderHandlerG() {

    }

    public static void setSender(CommunicationHandlerSender sender) {
        SenderHandlerG.sender = sender;
    }

    public void setCommand(String command) {
        this.command = command;
        scanner();
    }

    public static SenderHandlerG getCommandHandlerG() {
        if(senderHandlerG == null)
            senderHandlerG = new SenderHandlerG();
        return senderHandlerG;
    }

    public boolean isUse() {
        return isUse;
    }

    private void scanner(){
            String[] commands = command.split("-");
            isUse = false;

            switch (commands[0]) {
                case "Login" -> {
                    if (commands.length == 3){
                        senderID = commands[1];
                        ReceiverHandlerG.getReceiverHandlerG().setCommandSaver("Login");
                    }
                }
                case "Signup" -> {
                    if (commands.length == 4){
                        senderID = commands[1];
                        ReceiverHandlerG.getReceiverHandlerG().setCommandSaver("Signup");
                    }
                }
                case "PV" ->{
                    if(commands.length == 2) {
                        receiverID = commands[1];
                        ReceiverHandlerG.getReceiverHandlerG().setCommandSaver("PV");
                    }
                }
                case "ShowOnline" ->
                        ReceiverHandlerG.getReceiverHandlerG().setCommandSaver("Login");

                case "search" ->
                        ReceiverHandlerG.getReceiverHandlerG().setCommandSaver("Login");

                case "clearHistory" ->
                        ReceiverHandlerG.getReceiverHandlerG().setCommandSaver("Login");

                case "finish" ->
                        ReceiverHandlerG.getReceiverHandlerG().setCommandSaver("Login");

                case "Block" ->
                        ReceiverHandlerG.getReceiverHandlerG().setCommandSaver("Login");
                case "Ping" ->{}

                default -> isUse = true;
            }
            sender.setMessage(new Message(command,senderID,receiverID));
    }
    public void sendFile(File file){
        sender.setFile(file);
    }
}
