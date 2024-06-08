package view;

import model.Message;

import java.io.File;


public class SenderHandlerG {
    private String senderID;
    private String receiverID;
    private String command;
    private String commandSaver;
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

    public String getCommandSaver() {
        return commandSaver;
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
                        commandSaver = "Login";
                    }
                }
                case "Signup" -> {
                    if (commands.length == 4){
                        senderID = commands[1];
                        commandSaver = "Signup";
                    }
                }
                case "PV" ->{
                    if(commands.length == 2) {
                        receiverID = commands[1];
                        commandSaver = "PV";
                    }
                }
                case "ShowOnline" -> commandSaver = "ShowOnline";
                case "search" -> commandSaver = "search";
                case "clearHistory" -> commandSaver = "clearHistory";
                case "finish" -> commandSaver = "finish";
                case "Block" -> commandSaver = "Block";
                default -> isUse = true;
            }
            sender.setMessage(new Message(command,senderID,receiverID));
    }
    public void sendFile(File file){
        sender.setFile(file);
    }
}
