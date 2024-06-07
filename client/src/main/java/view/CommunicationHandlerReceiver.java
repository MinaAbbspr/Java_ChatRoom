package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class CommunicationHandlerReceiver extends Thread
{
    private Socket clientSocket;

    public CommunicationHandlerReceiver() throws IOException {
        clientSocket = new Socket("localhost",6666);
    }
    @Override
    public void run() {
        try {
            InputStreamReader reader = new InputStreamReader(clientSocket.getInputStream());
            BufferedReader in = new BufferedReader(reader);
            String message;

            while (true) {
                message = in.readLine();
                ReceiverHandlerG.getReceiverHandlerG().handler(message);
                if(ReceiverHandler.getReceiverHandler().handler(message))
                    break;
            }
            in.close();
            clientSocket.close();
        }
        catch (IOException ignored) { }
    }

}

