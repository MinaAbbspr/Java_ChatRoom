package view.graphic.fxmlController;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import view.graphic.View;

import java.net.URL;
import java.util.ResourceBundle;

public class GroupMembersController implements Initializable
{
    @FXML private Label name;
    @FXML private Label id;
    @FXML private Circle profile;
    @FXML private ImageView info;

    private String receiverID;
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        String[] parts = View.getView().getUser();
        name.setText(parts[0]);
        id.setText(parts[1]);
        this.receiverID = parts[1];
    }
    public void setInfo()
    {
        View.getView().setReceiverID(receiverID);
    }
}
