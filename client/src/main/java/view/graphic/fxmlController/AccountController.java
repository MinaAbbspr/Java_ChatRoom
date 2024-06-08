package view.graphic.fxmlController;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import view.graphic.View;

import java.net.URL;
import java.util.ResourceBundle;

public class AccountController implements Initializable
{

    @FXML private Circle profSide;
    @FXML private  VBox userSide;
    @FXML private  Label usernameSide;
    @FXML private  Label lastMessage;
    private String[] user;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.user = View.getView().getUser();
        usernameSide.setText(user[0]);
    }
}
