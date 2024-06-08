package controller;

import java.io.*;
import java.net.Socket;
import java.util.Objects;

public class CommunicationHandlerSender extends Thread
{
    private Socket socket;
    private String message;
    private static int code = 0;

    public CommunicationHandlerSender(Socket socket) {
        this.socket = socket;
        this.message = null;
        this.setName(String.valueOf(code++));
    }

    public void setMessage(String message) {
        this.message = message;
        synchronized (this){
            this.notify();
        }
    }

    @Override
    public void run() {
        try
        {
            ObjectOutputStream writer = new ObjectOutputStream(socket.getOutputStream());
            while (true) {
                while (message == null)
                    synchronized (this) {
                        this.wait();
                    }
                writer.writeUTF(message);
                message = null;
            }

        }
        catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
