package controller;

import java.io.*;
import java.net.Socket;
import java.util.Objects;

public class CommunicationHandlerSender extends Thread
{
    private Socket socket;
    private String message;

    public CommunicationHandlerSender(Socket socket) {
        this.socket = socket;
        this.message = null;
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
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            while (true) {
                while (message == null)
                    synchronized (this) {
                        this.wait();
                    }
                out.println(message);
                message = null;
                out.flush();
            }

        }
        catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
