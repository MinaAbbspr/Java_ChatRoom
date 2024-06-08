package view.graphic.fxmlController;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import model.Message;
import view.HelloApplication;
import view.graphic.ReceiverHandlerG;
import view.graphic.View;

import java.sql.Time;

public class NewMessage {
    private VBox vBox;
    private static NewMessage newMessage;

    public static NewMessage getNewMessage() {
        if(newMessage == null)
            newMessage = new NewMessage();
        return newMessage;
    }

    public void setvBox(VBox vBox) {
        this.vBox = vBox;
    }

    public void setMessages() {
        String[] chats = ReceiverHandlerG.getReceiverHandlerG().getSaveMessage().split("\n");
        for (int i = 0; i < chats.length; i++) {
            String[] parts = chats[i].split(" ");
            View.getView().setMessage(new Message(parts[1], parts[0], Time.valueOf(parts[2])));

            try {
                vBox.getChildren().add(new FXMLLoader(HelloApplication.class.getResource("message.fxml")).load());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
