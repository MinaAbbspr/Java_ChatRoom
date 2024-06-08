package view;


import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import view.graphic.SenderHandlerG;
import view.graphic.View;

import java.io.IOException;

public class HelloApplication extends Application
{
    private static Thread graphic;
    private static boolean isLogin;

    @Override
    public void start(Stage stage) throws IOException {
        View.getView().setStage(stage);
        View.getView().showLogin_signup();
    }

    public static void main(String[] args) throws IOException {
        isLogin = false;
        CommunicationHandlerSender sender = new CommunicationHandlerSender();
        CommunicationHandlerReceiver receiver = new CommunicationHandlerReceiver();
        sender.start();
        receiver.start();
        SenderHandlerG.setSender(sender);
        SenderHandler senderHandler = new SenderHandler();
        new Thread(Application::launch).start();
        graphic =new Thread(() -> {
            while (!isLogin)
                synchronized (graphic) {
                    try {
                        graphic.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            Platform.runLater(() -> {
                try {
                    View.getView().showMessenger();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        });
        graphic.start();
        senderHandler.scanner(sender);
    }

    public static void setIsLogin(boolean isLogin) {
        HelloApplication.isLogin = isLogin;
        synchronized (graphic){
            graphic.notify();
        }
    }
}