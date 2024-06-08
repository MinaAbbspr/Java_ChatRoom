package view;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class MessengerController implements Initializable
{
    public VBox group1;
    @FXML private Label memberCount;
    @FXML private Label groupName;
    @FXML private Label d;
    @FXML private VBox chats;
    @FXML private VBox group;
    @FXML private VBox usersSideList;
    @FXML private VBox options;
    @FXML private Circle groupImage;
    @FXML private Circle groupImg;
    @FXML private Circle back;
    @FXML private Circle close;
    @FXML private ScrollPane members;
    @FXML private AnchorPane groupP;
    @FXML private AnchorPane sideBar;
    @FXML private HBox topBar;
    @FXML private HBox messageBar;
    @FXML private TextField message;
    @FXML private Circle gBack;
    @FXML private Circle H;
    private boolean isGroup;
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        isGroup = false;
        Image groupI = new Image(Objects.requireNonNull(MessengerController.class.getResource("Images-6.jpeg")).toExternalForm());
        H.setFill(new ImagePattern(groupI));
        groupImage.setFill(new ImagePattern(groupI));
        groupImg.setFill(new ImagePattern(groupI));
        Image backImg = new Image(Objects.requireNonNull(MessengerController.class.getResource("Image.jpg")).toExternalForm());
        back.setFill(new ImagePattern(backImg));
        options.setVisible(false);
        groupP.setVisible(false);
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
    public void o(MouseEvent event) {
        Image closeImg = new Image(Objects.requireNonNull(MessengerController.class.getResource("IMG_0075.jpg")).toExternalForm());
        close.setFill(new ImagePattern(closeImg));
    }

    public void c(MouseEvent event)
    {
        Image closeImg = new Image(Objects.requireNonNull(MessengerController.class.getResource("Screenshot 2024-06-08 at 12.26.57 AM.jpg")).toExternalForm());
        close.setFill(new ImagePattern(closeImg));
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

    public void member(MouseEvent event)
    {

        members.setVisible(true);
        TranslateTransition slide1 = new TranslateTransition();
        slide1.setDuration(Duration.seconds(2));
        slide1.setNode(members);
        slide1.setToY(-400);
        slide1.play();
        BoxBlur blur = new BoxBlur();
        sideBar.setEffect(blur);
        messageBar.setEffect(blur);
        topBar.setEffect(blur);
        message.setDisable(true);
    }

    public void close(MouseEvent event)
    {
        TranslateTransition slide1 = new TranslateTransition();
        slide1.setDuration(Duration.seconds(2));
        slide1.setNode(members);
        slide1.setToY(400);
        slide1.play();
        sideBar.setEffect(null);
        topBar.setEffect(null);
        messageBar.setEffect(null);
        message.setDisable(false);
    }

    public void previous(MouseEvent event)
    {
    }

    public void groupIn(MouseEvent event)
    {

        groupP.setVisible(true);
    }
}
