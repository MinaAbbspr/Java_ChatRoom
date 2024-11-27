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

    private String[] parts;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        parts = View.getView().getUser();
        name.setText(parts[0]);
        id.setText(parts[1]);
    }
    public void setInfo()
    {
    }

    public void PV()
    {
        View.getView().setUser(parts);
    }
}
