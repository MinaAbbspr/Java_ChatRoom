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
    public void setUsers()
    {
//        Image artistPic = new Image(Objects.requireNonNull(BaseHomeController.class.getResource("output-onlinepngtools.png")).toExternalForm());
//        ar.setFill(new ImagePattern(artistPic));
//    }
//        if(artist instanceof Singer)
//        typeL.setText("singer");
//        else if(artist instanceof FreeListener)
//        typeL.setText("free listener");else if(artist instanceof PremiumListener)
//        typeL.setText("premium listener");
//        userL.setText(artist.getUserName());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.user = View.getView().getUser();
        usernameSide.setText(user[0]);
    }
}
