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
    }

    public CommunicationHandlerSender() throws IOException {
        clientSocket = new Socket("localhost",6666);
    }

    @Override
    public void run() {
        try {
            ObjectOutputStream writer = new ObjectOutputStream(clientSocket.getOutputStream());
            while(true)
            {
                synchronized (this) {
                    wait();
                }
                if(Objects.equals(message, "exit"))
                    break;
                writer.writeObject(message);
            }
            writer.close();
            clientSocket.close();
        }
        catch (IOException|InterruptedException e) {
            e.printStackTrace();
        }
    }
}
