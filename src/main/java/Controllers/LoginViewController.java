package Controllers;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginViewController implements Initializable {




        @FXML
        private Button forgotyourpassword_login;

        @FXML
        private Button login_login;

        @FXML
        private PasswordField passwordlogin;

        @FXML
        private CheckBox rememberme_login;

        @FXML
        private Button signuplogin;

        @FXML
        private TextField usernamelogin;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        usernamelogin.setStyle("-fx-text-inner-color:#a0a2ab;");
        passwordlogin.setStyle("-fx-text-inner-color:#a0a2ab;");

    }
}


