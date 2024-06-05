package controller;

import model.Message;
import view.CommandHandler;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.sql.SQLException;

public class CommunicationHandlerReceiver extends Thread{
    private Socket socket;
    private static int code = 0;

    public CommunicationHandlerReceiver(Socket socket) {
        this.socket = socket;
        this.setName(String.valueOf(code++));
    }

    @Override
    public void run() {
        try {
            ObjectInputStream reader = new ObjectInputStream(socket.getInputStream());
            Message message;
            while (true) {
                message = (Message) reader.readObject();
                if (message.getText().equals("exit")) {
                    System.out.println("Good bye");
                    break;
                } else {
                    CommandHandler.getCommandHandler().scanner(message);
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
