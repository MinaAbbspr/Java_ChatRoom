package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import model.Message;

import java.io.IOException;
import java.net.URL;
import java.sql.Time;
import java.util.Objects;
import java.util.ResourceBundle;

public class MessengerController implements Initializable
{
    public VBox group1;
    @FXML
    private ScrollPane allUsers;

    @FXML
    private Circle back;

    @FXML
    private AnchorPane blackPane;

    @FXML
    private VBox chats;

    @FXML
    private VBox group;

    @FXML
    private Circle groupImage;

    @FXML
    private Label lastMessageG;

    @FXML
    private Label on_off_top;

    @FXML
    private Circle topImage;

    @FXML
    private ImageView topInfo;

    @FXML
    private Label topName;

    @FXML
    private TextField txt_search;

    @FXML
    private VBox userSide;

    @FXML
    private Label usernameSide;

    @FXML
    private VBox usersSideList;

    @FXML
    private VBox vBox_users;

    @FXML
    private AnchorPane whitePane;
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        Image groupImg = new Image(Objects.requireNonNull(MessengerController.class.getResource("images/images-11.jpeg")).toExternalForm());
        groupImage.setFill(new ImagePattern(groupImg));
        Image backImg = new Image(Objects.requireNonNull(MessengerController.class.getResource("images/Image.jpg")).toExternalForm());
        back.setFill(new ImagePattern(backImg));
        setMessages();
    }

    private void setMessages(){
        String[] chats = ReceiverHandlerG.getReceiverHandlerG().getChats().split("\n");
        for(int i=0; i< chats.length-1; i+=3){
            View.getView().setMessage(new Message(chats[i],chats[i+1], Time.valueOf(chats[i+2])));

            try {
                //vBox_messages.getChildren().add(new FXMLLoader(HelloApplication.class.getResource("message.fxml")).load());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
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
}
