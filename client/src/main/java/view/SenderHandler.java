package view;

import model.Message;

import java.util.Scanner;

public class SenderHandler {
    private String senderID;
    private String receiverID;
    private static long startTime;

    public static long getStartTime() {
        return startTime;
    }

    public void scanner(CommunicationHandlerSender sender){
        Scanner sc = new Scanner(System.in);
        String command;
        boolean con = true;
        while (con){
            command = sc.nextLine();
            String[] commands = command.split("-");

            switch (commands[0]) {
                case "Login" -> {
                    if (commands.length == 3)
                        senderID = commands[1];
                }
                case "Signup" -> {
                    if (commands.length == 4)
                        senderID = commands[1];
                }
                case "Ping" -> {
                    if (commands.length == 1) {
                        startTime = System.nanoTime();
                    }
                }
                case "PV" ->{
                    if(commands.length == 2)
                        receiverID = commands[1];
                }
                case "exit" -> con = false;
            }
            sender.setMessage(new Message(command,senderID,receiverID));
        }
    }
}
