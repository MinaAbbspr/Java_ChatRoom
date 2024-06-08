package view;


import javafx.application.Application;
import javafx.stage.Stage;
import view.graphic.SenderHandlerG;
import view.graphic.View;

import java.io.IOException;

public class HelloApplication extends Application
{
    @Override
    public void start(Stage stage) throws IOException {
        View.getView().setStage(stage);
        View.getView().showLogin_signup();
    }

    public static void main(String[] args) throws IOException {
        new Thread(Application::launch).start();
        CommunicationHandlerSender sender = new CommunicationHandlerSender();
        CommunicationHandlerReceiver receiver = new CommunicationHandlerReceiver();
        sender.start();
        receiver.start();
        SenderHandlerG.setSender(sender);
        SenderHandler senderHandler = new SenderHandler();
        senderHandler.scanner(sender);
    }


}