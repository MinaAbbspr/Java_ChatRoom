package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Message;

import java.io.IOException;

public class View {
    private static View view;
    private Stage stage;
    private Message message;

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

    public void showMessenger() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("messenger.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Messenger!");
        stage.setScene(scene);
        stage.show();
    }

    public void showLogin_signup() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("messenger.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Messenger!");
        stage.setScene(scene);
        stage.show();
    }
}
