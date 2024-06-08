package view.graphic.fxmlController;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import model.Message;
import view.HelloApplication;
import view.graphic.ReceiverHandlerG;
import view.graphic.View;

import java.io.IOException;
import java.sql.Time;

public class NewMessage {
    private VBox vBox;
    private Thread thread;
    private String message;
    private boolean set;
    private static NewMessage newMessage;

    public static NewMessage getNewMessage() {
        if(newMessage == null)
            newMessage = new NewMessage();
        return newMessage;
    }

    public NewMessage() {
        set = false;

        thread();
        thread.start();
    }

    public void setvBox(VBox vBox) {
        this.vBox = vBox;
    }

    public void setMessages(String message) {
//        String[] chats = ReceiverHandlerG.getReceiverHandlerG().getSaveMessage().split("\n");
//        for (int i = 0; i < chats.length; i++) {
//            String[] parts = chats[i].split(" ");
//            View.getView().setMessage(new Message(parts[1], parts[0], Time.valueOf(parts[2])));
//
//            try {
//                vBox.getChildren().add(new FXMLLoader(HelloApplication.class.getResource("message.fxml")).load());
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }

        String[] parts = message.split(" ");
        View.getView().setMessage(new Message(parts[1], parts[0], Time.valueOf(parts[2])));

        try {
            vBox.getChildren(). add(new FXMLLoader(HelloApplication.class.getResource("message.fxml")).load());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void thread(){
        thread =new Thread(() -> {
            while (true){
                while (!set)
                    synchronized (thread) {
                        try {
                            thread.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                Platform.runLater(() -> {
                    String[] parts = message.split(" ");
                    View.getView().setMessage(new Message(parts[1], parts[0], Time.valueOf(parts[2])));

                    try {
                        vBox.getChildren(). add(new FXMLLoader(HelloApplication.class.getResource("message.fxml")).load());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
                set = false;
            }
        });
    }

    public void setMessage(String message) {
        this.message = message;
        set = true;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (thread) {
            thread.notify();
        }
    }
}
