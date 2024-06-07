package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class MessengerController implements Initializable
{
    public VBox group1;
    @FXML private Label memberCount;
    @FXML private Label groupName;
    @FXML private VBox chats;
   @FXML private VBox group;
    @FXML private VBox usersSideList;
    @FXML private Circle groupImage;
    @FXML private Circle groupImg;
    @FXML private Circle back;
    @FXML private Circle gBack;
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        Image groupI = new Image(Objects.requireNonNull(MessengerController.class.getResource("images-11.jpeg")).toExternalForm());
        groupImage.setFill(new ImagePattern(groupI));
        groupImg.setFill(new ImagePattern(groupI));
        Image backImg = new Image(Objects.requireNonNull(MessengerController.class.getResource("Image.jpg")).toExternalForm());
        back.setFill(new ImagePattern(backImg));
        gBack.setFill(new ImagePattern(backImg));
    }


    public void chatF(MouseEvent event) {
        chats.setStyle("-fx-border-width: 0 0 2 0 ");
    }

    public void chatE(MouseEvent event)
    {
        chats.setStyle("-fx-border-width: 0 0 0 0 ");
    }

    public void groupF(MouseEvent event)
    {
        group1.setStyle("-fx-border-width: 0 0 2 0 ");
    }

    public void groupE(MouseEvent event) {
        group1.setStyle("-fx-border-width: 0 0 0 0 ");
    }

    public void search(MouseEvent event) {
    }
    public void showSideUsers()
    {
//        try {
//            for (UserAccount userAccount : getDataBase().getUsers()) {
//                FXMLLoader fxmlLoader = new FXMLLoader();
//                fxmlLoader.setLocation(getClass().getResource("account.fxml"));
//                HBox vBox = fxmlLoader.load();
//                ArtistAdminController artistAdminController = fxmlLoader.getController();
//                if(userAccount instanceof Artist) {
//                    artistAdminController.set((Artist) userAccount);
//                    artistList2.getChildren().add(vBox);
//                }
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }

    public void gEnter(MouseEvent event) {
    }
}
