package view;

import model.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
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
                if (message.equals("exit")) {
                    System.out.println("Good bye");
                    break;
                }else if(message.equals("ping")){
                    long endTime = System.nanoTime();
                    System.out.println("ping: " + (endTime-CommandHandler.getStartTime())/1000000 + "ms");
                }
                else {
                    System.out.println(message);
                }
            }
            in.close();
            clientSocket.close();
        }
        catch (IOException ex) { }
    }

}

