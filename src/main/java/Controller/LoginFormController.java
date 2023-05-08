package Controller;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginFormController implements Initializable {
    @FXML
    private Button loginid;

    @FXML
    private TextField passwordid;

    @FXML
    private Button signupid;

    @FXML
    private TextField usernameid;

    @FXML
    void loginaction(ActionEvent event) {
        PauseTransition pt=new PauseTransition();
        pt.setDuration(Duration.seconds(3));
        pt.setOnFinished(ev->{
            System.out.print("Login Succesfully");
        });
        pt.play();

    }

    @FXML
   public void signupaction(ActionEvent event) throws IOException {
        loginid.getScene().getWindow().hide();
        Stage signup=new Stage();
        Parent root= FXMLLoader.load(getClass().getResource("/SignUpForm.fxml"));
        Scene scene=new Scene(root);
        signup.setScene(scene);
        signup.show();
        signup.setResizable(false);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

