package Controllers;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginViewController implements Initializable {
    @FXML
    private Button login;

    @FXML
    private PasswordField password;

    @FXML
    private CheckBox remember;

    @FXML
    private Button signup;

    @FXML
    private TextField username;
    @FXML
    private ImageView progress;



    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        progress.setVisible(false);
        username.setStyle("-fx-text-inner-color:#a0a2ab;");
        password.setStyle("-fx-text-inner-color:#a0a2ab;");

    }
    @FXML
    public void loginAction(ActionEvent e){
        progress.setVisible(true);
        PauseTransition pt=new PauseTransition();
        pt.setDuration(Duration.seconds(3));
        pt.setOnFinished(ev->{
            System.out.print("Login Succesfully");
        });
        pt.play();

    }


}
