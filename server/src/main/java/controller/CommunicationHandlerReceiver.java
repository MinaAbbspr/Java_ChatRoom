package controller;

import model.Message;
import view.CommandHandler;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.sql.SQLException;

public class CommunicationHandlerReceiver extends Thread{
    private Socket socket;


    public CommunicationHandlerReceiver(Socket socket) {
        this.socket = socket;
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
            throw new RuntimeException(e);
        }
    }

}
