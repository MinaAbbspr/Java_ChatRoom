package view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ResourceBundle;

public class GroupMembersController implements Initializable
{
    @FXML private Label name;
    @FXML private Label id;
    @FXML private Circle profile;
    @FXML private ImageView info;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {

    }
    public void setInfo()
    {
        //set info here
    }
}
