package view;

import controller.CommunicationHandlerReceiver;
import controller.CommunicationHandlerSender;
import model.DataBase;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HelloApplication{

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(6666);

        while(true)
        {
            long start = System.nanoTime();
            Socket clientSocket = serverSocket.accept();
            long end = System.nanoTime();

            CommunicationHandlerSender sender = new CommunicationHandlerSender(clientSocket);
            CommunicationHandlerReceiver receiver = new CommunicationHandlerReceiver(clientSocket);
            if(Integer.parseInt(sender.getName()) %2 == 1) {
                sender.setName(String.valueOf(Integer.parseInt(sender.getName())-1));
                DataBase.getDataBase().getThreadList().add(sender);
            }
            receiver.start();
            sender.start();

            sender.setMessage("ping: " + (end-start)/1000000 + "ms");
        }
    }
}