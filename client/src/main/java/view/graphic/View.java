package view.graphic;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Message;
import view.HelloApplication;

import java.io.IOException;

public class View{
    private static View view;
    private Stage stage;
    private Message message;
    private String[] user;

    private View() {
    }

    public static View getView() {
        if(view == null)
            view = new View();
        return view;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public String[] getUser() {
        return user;
    }

    public void setUser(String[] user) {
        this.user = user;
    }

    public void showMessenger() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("messenger.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Messenger!");
        stage.setScene(scene);
        stage.show();
    }

    public void showLogin_signup() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),1290,800);
        stage.setTitle("Messenger!");
        stage.setScene(scene);
        stage.show();
    }
}
