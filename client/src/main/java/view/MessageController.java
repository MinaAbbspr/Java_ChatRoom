package view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class MessageController implements Initializable
{

    @FXML private VBox message;
    @FXML private Label senderName;
    @FXML private  Label time;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
