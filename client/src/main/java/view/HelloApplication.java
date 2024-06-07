package view;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class HelloApplication extends Application
{
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),1290,799);
        stage.setTitle("Messenger!");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) throws IOException {
        CommunicationHandlerSender sender = new CommunicationHandlerSender();
        CommunicationHandlerReceiver receiver = new CommunicationHandlerReceiver();
        sender.start();
        receiver.start();
        SenderHandlerG.setSender(sender);
        SenderHandler senderHandler = new SenderHandler();
        new Thread(() -> {
            launch();
        }).start();
        senderHandler.scanner(sender);
    }


}