package Application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class SignupFormController {
    @FXML
    private CheckBox female;

    @FXML
    private TextField locationid;

    @FXML
    private Button loginid;

    @FXML
    private CheckBox male;

    @FXML
    private AnchorPane paneid;

    @FXML
    private PasswordField passwordid;

    @FXML
    private Button signupid;

    @FXML
    private TextField usernameid;

    @FXML
   public  void loginaction(ActionEvent event) throws IOException
    {
        signupid.getScene().getWindow().hide();
        Stage login = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/LogInForm.fxml"));
        Scene scene = new Scene(root);
        login.setScene(scene);
        login.show();
        login.setResizable(false);
    }

    @FXML
    void signupaction(ActionEvent event) {

    }

}
