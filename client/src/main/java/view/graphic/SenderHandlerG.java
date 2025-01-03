package view.graphic;

import model.Message;
import view.CommunicationHandlerSender;

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

    public String getSenderID() {
        return senderID;
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
                        ReceiverHandlerG.getReceiverHandlerG().setCommandSaver("ShowOnline");

                case "search" ->
                        ReceiverHandlerG.getReceiverHandlerG().setCommandSaver("search");

                case "clearHistory" ->
                        ReceiverHandlerG.getReceiverHandlerG().setCommandSaver("clearHistory");

                case "finish" ->
                        ReceiverHandlerG.getReceiverHandlerG().setCommandSaver("finish");

                case "Block" ->
                        ReceiverHandlerG.getReceiverHandlerG().setCommandSaver("Block");
                case "showUsers" ->
                        ReceiverHandlerG.getReceiverHandlerG().setCommandSaver("showUsers");
                case "Ping" ->{}

                default -> isUse = true;
            }
            sender.setMessage(new Message(command,senderID,receiverID));
    }
    public void sendFile(File file){
        sender.setFile(file);
    }
}
