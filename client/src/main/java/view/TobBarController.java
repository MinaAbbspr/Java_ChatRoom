package view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Window;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class TobBarController implements Initializable
{
    @FXML private Circle previous;
    @FXML private Circle topImage;
    @FXML private ImageView topInfo;
    @FXML private Label topName;
    @FXML private Label on_off_top;
    private ContextMenu contextMenu;
    private MenuItem block;
    private MenuItem clearHistory;
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        contextMenu = new ContextMenu();
        block = new MenuItem("block");
        clearHistory = new MenuItem("clear history");
        contextMenu.getItems().addAll(block,clearHistory);
        Image backImg = new Image(Objects.requireNonNull(MessengerController.class.getResource("Image.jpg")).toExternalForm());
        previous.setFill(new ImagePattern(backImg));
    }

    public void previous(MouseEvent event) {
    }

    public void info(MouseEvent event)
    {

    }
}
