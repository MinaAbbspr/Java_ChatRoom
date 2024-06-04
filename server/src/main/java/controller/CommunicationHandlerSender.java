package controller;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Objects;

public class CommunicationHandlerSender extends Thread
{
    private Socket socket;
    private String message;

    public CommunicationHandlerSender(Socket socket) {
        this.socket = socket;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public void run() {
        try
        {
            ObjectOutputStream writer = new ObjectOutputStream(socket.getOutputStream());
            while(true)
            {
                wait();
                if(Objects.equals(message, "exit"))
                    break;
                writer.writeObject(message);
            }
            writer.close();
            socket.close();
        }
        catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }
}
