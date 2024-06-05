package view;

import model.Message;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Objects;

public class CommunicationHandlerSender extends Thread{
    private Socket clientSocket;
    private Message message;

    public void setMessage(Message message) {
        this.message = message;
        synchronized (this){
            this.notify();
        }
    }

    public CommunicationHandlerSender() throws IOException {
        clientSocket = new Socket("localhost",6666);
        message = null;
    }

    @Override
    public void run() {
        try {
            ObjectOutputStream writer = new ObjectOutputStream(clientSocket.getOutputStream());
            while(true)
            {
                while (message == null)
                    synchronized (this) {
                        this.wait();
                    }
                if(message.equals("exit"))
                    break;
                writer.writeObject(message);
                writer.flush();
                message = null;
            }
            writer.close();
            clientSocket.close();
        }
        catch (IOException|InterruptedException e) {
            e.printStackTrace();
        }
    }
}
