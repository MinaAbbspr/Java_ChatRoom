package view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class MessengerController implements Initializable
{
   @FXML private VBox chats;
   @FXML private VBox group;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    public void chatF(MouseEvent event) {
        chats.setStyle("-fx-border-width: 0 0 2 0 ");
    }

    public void chatE(MouseEvent event)
    {
        chats.setStyle("-fx-border-width: 0 0 0 0 ");
    }

    public void groupF(MouseEvent event) {
        group.setStyle("-fx-border-width: 0 0 2 0 ");
    }

    public void groupE(MouseEvent event) {
        group.setStyle("-fx-border-width: 0 0 0 0 ");
    }

    public void search(MouseEvent event) {
    }
}
