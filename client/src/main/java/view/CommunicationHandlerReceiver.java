package view;

import model.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class CommunicationHandlerReceiver extends Thread
{
    private Socket clientSocket;
    private Message message;

    public CommunicationHandlerReceiver(Message message) throws IOException {
        clientSocket = new Socket("localhost",6666);
        this.message = message;
    }
    @Override
    public void run() {
        try {
            ObjectInputStream reader = new ObjectInputStream(clientSocket.getInputStream());
            String message;
            while (!(message = reader.readUTF()).isEmpty()) {
                if (message.equals("exit")) {
                    System.out.println("Good bye");
                    break;
                } else {
                    System.out.println(message);
                }
            }
            reader.close();
            clientSocket.close();
        }
        catch (IOException ex) { }
    }

}

