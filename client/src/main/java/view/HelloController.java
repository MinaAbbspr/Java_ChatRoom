package view;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    public TextField login1;
    @FXML
    private AnchorPane blackPane;

    @FXML
    private Button btn_login_signup;

    @FXML
    private ImageView invisible;

    @FXML
    private ImageView invisible1;

    @FXML
    private PasswordField invlpass;

    @FXML
    private Label lbl_inner;

    @FXML
    private Label lbl_welcome;

    @FXML
    private TextField log1;

    @FXML
    private TextField login;

    @FXML
    private VBox loginV;

    @FXML
    private HBox pas1;

    @FXML
    private Label passInE1;

    @FXML
    private HBox signup;

    @FXML
    private VBox signupV;

    @FXML
    private PasswordField spas;

    @FXML
    private TextField svpass;

    @FXML
    private Label usernameES;

    @FXML
    private Label usernameInE1;

    @FXML
    private ImageView visible;

    @FXML
    private ImageView visible1;

    @FXML
    private TextField vlpass1;

    @FXML
    private HBox vpas1;

    @FXML
    private AnchorPane whitePane;
    private boolean animationS;

    @FXML
    void animation(MouseEvent event)
    {
        if(!animationS) {
            TranslateTransition slide1 = new TranslateTransition();
            slide1.setDuration(Duration.seconds(1.5));
            slide1.setNode(blackPane);
            slide1.setToX(950);
            slide1.play();
            signupV.setVisible(false);
            loginV.setVisible(true);
            TranslateTransition slide2 = new TranslateTransition();
            slide2.setDuration(Duration.seconds(1.5));
            slide2.setNode(whitePane);
            slide2.setToX(-343);
            slide2.play();
            animationS = true;
        }
        else
        {
            TranslateTransition slide1 = new TranslateTransition();
            slide1.setDuration(Duration.seconds(1.5));
            slide1.setNode(blackPane);
            slide1.setToX(0);
            slide1.play();
            signupV.setVisible(true);
            loginV.setVisible(false);
            TranslateTransition slide2 = new TranslateTransition();
            slide2.setDuration(Duration.seconds(1.5));
            slide2.setNode(whitePane);
            slide2.setToX(0);
            slide2.play();
            animationS = false;
        }
    }

    @FXML
    void complete(MouseEvent event) {

    }

    @FXML
    void invisible(MouseEvent event) {

    }

    @FXML
    void login(MouseEvent event) {

    }

    @FXML
    void visible(MouseEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        animationS = false;
        loginV.setVisible(false);
        signupV.setVisible(true);
    }
}
