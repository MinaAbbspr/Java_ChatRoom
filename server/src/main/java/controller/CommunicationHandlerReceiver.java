package controller;

import model.Message;
import view.CommandHandler;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.sql.SQLException;

public class CommunicationHandlerReceiver extends Thread{
    private Socket socket;
    private CommandHandler commandHandler;
    private static int code = 0;

    public CommunicationHandlerReceiver(Socket socket) {
        this.socket = socket;
        this.setName(String.valueOf(code++));
        this.commandHandler = new CommandHandler();
    }

    @Override
    public void run() {
        try {
            ObjectInputStream reader = new ObjectInputStream(socket.getInputStream());
            Message message;
            while (true) {
                Object obj = reader.readObject();
                try {
                    message = (Message) obj;
                    if (message.getText().equals("exit")) {
                        commandHandler.scanner(message);
                        break;
                    } else {
                        commandHandler.scanner(message);
                    }
                }catch (Exception e){
                    File file = (File) obj;
                    commandHandler.fileHandler(file);
                }

            }
            reader.close();
            socket.close();
        }
        catch (IOException ignored) { } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

}
