package view.graphic.fxmlController;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import model.Message;
import view.graphic.View;
import view.graphic.SenderHandlerG;

import java.net.URL;
import java.util.ResourceBundle;

public class MessageController implements Initializable
{

    @FXML private VBox message;

    @FXML
    private Label senderName;

    @FXML
    private Label text;

    @FXML
    private Label time;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Message messageClass = View.getView().getMessage();
        senderName.setText(messageClass.getSender());
        text.setText(messageClass.getText());
        time.setText(messageClass.getTime());
        message.setMaxWidth(400);

        if (SenderHandlerG.getCommandHandlerG().getSenderID().equals(messageClass.getSender())) {
            senderName.setAlignment(Pos.TOP_RIGHT);
            text.setAlignment(Pos.TOP_RIGHT);
            time.setAlignment(Pos.TOP_LEFT);
            message.setAlignment(Pos.TOP_RIGHT);
            message.setStyle("-fx-background-color: #150101; -fx-border-radius: 20;-fx-background-radius: 20;-fx-text-fill:white");
            senderName.setStyle("-fx-background-color: #150101; -fx-border-radius: 20;-fx-background-radius: 20;-fx-text-fill:white");
            text.setStyle("-fx-background-color: #150101; -fx-border-radius: 20;-fx-background-radius: 20;-fx-text-fill:white");
            text.setPadding(new Insets(0,10,0,0));
            senderName.setPadding(new Insets(0,10,0,0));
            VBox.setMargin(message, new Insets(10,10,10,450));
        } else {
            senderName.setAlignment(Pos.TOP_LEFT);
            text.setAlignment(Pos.TOP_LEFT);
            time.setAlignment(Pos.TOP_RIGHT);
            message.setAlignment(Pos.TOP_LEFT);
        }
    }
}
