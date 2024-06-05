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
            DataBase.getDataBase().getThreadMap().put(sender.getName(), sender);
            receiver.start();
            sender.start();
            DataBase.getDataBase().getThreadMap().put("mina",sender);

            sender.setMessage("ping: " + (end-start)/1000000 + "ms");
        }
    }
}