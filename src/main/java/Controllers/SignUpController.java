package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

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
        username.setStyle("-fx-text-inner-color:#a0a2ab");
        password.setStyle("-fx-text-inner-color:#a0a2ab");
        location.setStyle("-fx-text-inner-color:#a0a2ab");
    }

    public void signup(ActionEvent ae1){

    }

}
