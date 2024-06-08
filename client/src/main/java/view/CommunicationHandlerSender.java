package view;

import model.Message;

import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Objects;

public class CommunicationHandlerSender extends Thread{
    private final Socket clientSocket;
    private Message message;
    private File file;

    public synchronized void setMessage(Message message) {
        this.message = message;
        synchronized (this){
            this.notify();
        }
    }

    public synchronized void setFile(File file) {
        this.file = file;
        this.message = new Message("*signup*","","");
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
                if(message.getText().equals("exit")) {
                    writer.writeObject(message);
                    break;
                }
                else if(message.getText().equals("*signup*"))
                    writer.writeObject(file);

                else
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
