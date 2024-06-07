package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MessengerController implements Initializable
{
   @FXML private VBox chats;
   @FXML private VBox group;
    @FXML private VBox list1;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    public void chatF(MouseEvent event) {
        chats.setStyle("-fx-border-width: 0 0 2 0 ");
    }

    public void chatE(MouseEvent event)
    {chats.setStyle("-fx-border-width: 0 0 0 0 ");
    }

    public void groupF(MouseEvent event)
    {
        group.setStyle("-fx-border-width: 0 0 2 0 ");
    }

    public void groupE(MouseEvent event) {
        group.setStyle("-fx-border-width: 0 0 0 0 ");
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
