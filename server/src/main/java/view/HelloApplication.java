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

            Socket clientSocketSender = serverSocket.accept();
            CommunicationHandlerSender sender = new CommunicationHandlerSender(clientSocketSender);

            Socket clientSocketReceiver = serverSocket.accept();
            CommunicationHandlerReceiver receiver = new CommunicationHandlerReceiver(clientSocketReceiver);

            DataBase.getDataBase().getThreadList().add(sender);
            receiver.start();
            sender.start();

            long end = System.nanoTime();

            sender.setMessage("ping: " + (end-start)/1000000 + "ms");
        }
    }
}