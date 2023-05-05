package Controllers;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class SignUpController implements Initializable {

    @FXML
    private ImageView carphoto;

    @FXML
    private CheckBox femalegender;

    @FXML
    private ImageView genderphoto;

    @FXML
    private TextField location;

    @FXML
    private ImageView locationphoto;
    @FXML
    private ImageView loadingid;


    @FXML
    private Button login;

    @FXML
    private CheckBox malegender;

    @FXML
    private AnchorPane parentpane;

    @FXML
    private PasswordField password;

    @FXML
    private ImageView passwordphoto;

    @FXML
    private Button signup;

    @FXML
    private TextField username;

    @FXML
    private ImageView usernamephoto;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1){
        loadingid.setVisible(false);
        username.setStyle("-fx-text-inner-color:#a0a2ab");
        password.setStyle("-fx-text-inner-color:#a0a2ab");
        location.setStyle("-fx-text-inner-color:#a0a2ab");
    }
@FXML
    public void signUP(ActionEvent ae1){
    loadingid.setVisible(true);
    PauseTransition pt = new PauseTransition();
    pt.setDuration(Duration.seconds(3));
    pt.setOnFinished(e -> {
        System.out.println("Signup succesfuly");
    });
    pt.play();

    }

    public void LoginViewController(ActionEvent ae2) throws IOException {
        signup.getScene().getWindow().hide();
        Stage login = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/Login.fxml"));
        Scene scene = new Scene(root);
        login.setScene(scene);
        login.show();
        login.setResizable(false);
    }

}
