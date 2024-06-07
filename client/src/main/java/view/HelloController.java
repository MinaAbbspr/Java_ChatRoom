package view;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
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

    @FXML
    private Label lbl_loggedInError;

    @FXML
    private Circle image;

    private boolean animationS;

    private File file;

    @FXML
    void animation(MouseEvent event)
    {
        if(!animationS) {
            animationS = true;
            signupV.setVisible(false);
            loginV.setVisible(true);
            btn_login_signup.setText("Signup");
            lbl_welcome.setText("Hello!");
            lbl_inner.setText("enjoy happy chat...");
            TranslateTransition slide1 = new TranslateTransition();
            slide1.setDuration(Duration.seconds(1.5));
            slide1.setNode(blackPane);
            slide1.setToX(950);
            slide1.play();
            TranslateTransition slide2 = new TranslateTransition();
            slide2.setDuration(Duration.seconds(1.5));
            slide2.setNode(whitePane);
            slide2.setToX(-343);
            slide2.play();
        }
        else
        {
            animationS = false;
            signupV.setVisible(true);
            loginV.setVisible(false);
            btn_login_signup.setText("Login");
            lbl_welcome.setText("Welcome Back!");
            lbl_inner.setText("contact your friends and send unlimited messages");
            TranslateTransition slide1 = new TranslateTransition();
            slide1.setDuration(Duration.seconds(1.5));
            slide1.setNode(blackPane);
            slide1.setToX(0);
            slide1.play();
            TranslateTransition slide2 = new TranslateTransition();
            slide2.setDuration(Duration.seconds(1.5));
            slide2.setNode(whitePane);
            slide2.setToX(0);
            slide2.play();
        }
    }

    @FXML
    void complete(MouseEvent event) throws InterruptedException {
        if(svpass.getText().isEmpty())
            svpass.setText(spas.getText());
        if(login1.getText().isEmpty() || log1.getText().isEmpty() || svpass.getText().isEmpty()){
            return;
        }
        GHandler.getgHandler().signup("Signup-" + log1.getText() + "-" + login1.getText() + "-" + svpass.getText(),file);
        usernameES.setVisible(true);
    }

    @FXML
    void invisible(MouseEvent event) {

    }

    @FXML
    void login(MouseEvent event) throws InterruptedException {
        if(vlpass1.getText().isEmpty())
            vlpass1.setText(invlpass.getText());
        if(login.getText().isEmpty() || vlpass1.getText().isEmpty()){
            return;
        }

        if(GHandler.getgHandler().login("Login-" + login.getText() + "-" + vlpass1.getText())) {
            usernameInE1.setVisible(true);
            passInE1.setVisible(false);
        }else {
            if(GHandler.getgHandler().isLoggedInException()){
                usernameInE1.setVisible(false);
                passInE1.setVisible(false);
                lbl_loggedInError.setVisible(true);
            }
            else {
                usernameInE1.setVisible(false);
                passInE1.setVisible(true);
            }
        }
    }

    @FXML
    void visible(MouseEvent event) {

    }

    @FXML
    void profile(MouseEvent event) {
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        file = fileChooser.showOpenDialog(stage);
        image.setFill(new ImagePattern(new Image(file.toURI().toString())));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        animationS = false;
        loginV.setVisible(false);
        signupV.setVisible(true);
    }
}
