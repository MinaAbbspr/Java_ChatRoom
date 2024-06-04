package controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class CommunicationHandlerReceiver extends Thread{
    private Socket socket;

    public CommunicationHandlerReceiver(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            ObjectInputStream reader = new ObjectInputStream(socket.getInputStream());
            String message;
            while (!(message = reader.readUTF()).isEmpty()) {
                if (message.equals("exit")) {
                    System.out.println("Good bye");
                    break;
                } else {
                    ///controllers
                }
            }
            //---------------
            reader.close();
            socket.close();
        }
        catch (IOException ex) { }
    }

}
