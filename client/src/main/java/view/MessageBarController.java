package view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class MessageBarController implements Initializable
{

    @FXML private HBox messageBar;
    @FXML private TextField type;
    @FXML private ImageView send;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void send(MouseEvent event) {
    }
}
