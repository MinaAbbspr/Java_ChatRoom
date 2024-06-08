package view;

import view.graphic.ReceiverHandlerG;

import java.io.*;
import java.net.Socket;

public class CommunicationHandlerReceiver extends Thread
{
    private final Socket clientSocket;

    public CommunicationHandlerReceiver() throws IOException {
        clientSocket = new Socket("localhost",6666);
    }

    @Override
    public void run() {
        try {
            ObjectInputStream reader = new ObjectInputStream(clientSocket.getInputStream());

            while (true) {
                String message = reader.readUTF();
                ReceiverHandlerG.getReceiverHandlerG().handler(message);
                if(ReceiverHandler.getReceiverHandler().handler(message))
                    break;
            }
            reader.close();
            clientSocket.close();
        }
        catch (IOException ignored) { }
    }

}

