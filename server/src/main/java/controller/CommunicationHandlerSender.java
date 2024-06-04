package controller;

import java.io.*;
import java.net.Socket;
import java.util.Objects;

public class CommunicationHandlerSender extends Thread
{
    private Socket socket;
    private String message;
    private Object lock;

    public CommunicationHandlerSender(Socket socket) {
        lock = new Object();
        this.socket = socket;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getLock() {
        return lock;
    }

    @Override
    public void run() {
        try
        {
//            ObjectOutputStream writer = new ObjectOutputStream(socket.getOutputStream());
//            while(true)
//            {
//                synchronized (this){
//                this.wait();
//                }
//                if(Objects.equals(message, "exit"))
//                    break;
//                writer.writeUTF(message);
//            }
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            while (true) {
                synchronized (this) {
                    wait();
                }
                out.println(message);
            }

        }
        catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
