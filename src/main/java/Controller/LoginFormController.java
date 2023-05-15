package Controller;

import app.LoginForm;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import ConnectionMysql.DBHandler;
import models.User;
import models.Car;
import models.LangEnum;


public class LoginFormController implements Initializable {
    @FXML
    private Button loginid;

    @FXML
    private TextField passwordid;

    @FXML
    private Button signupid;

    @FXML
    private Button close;
    @FXML
    private TextField usernameid;

    private DBHandler handler;
    private Connection connection;
    private PreparedStatement pst;


    public void close(){
        System.exit(0);
    }


    @FXML
    void loginaction(ActionEvent event) {
        PauseTransition pt=new PauseTransition();
        pt.setDuration(Duration.seconds(3));
        pt.setOnFinished(ev->{

        });
        pt.play();

        connection = handler.getConnection();
        String query1 = "SELECT * FROM klientet_signup where klient_username=? and fjalekalimi_i_klientit=?";

        try {
            pst = connection.prepareStatement(query1);
            pst.setString(1,usernameid.getText());
            pst.setString(2,passwordid.getText());
            ResultSet rst = pst.executeQuery();



            int count=0;

            while(rst.next()){
                count=count+1;
            }
            if(count==1){
                loginid.getScene().getWindow().hide();
                Stage home = new Stage();

                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(LoginForm.class.getResource("/views/Dashboard.fxml"));
                    Parent root = fxmlLoader.load();
                    Scene scene = new Scene(root);
                    home.setScene(scene);
                    home.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else{
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Username and Password is not correct");
                alert.show();
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    @FXML
   public void signupaction(ActionEvent event) throws IOException {
        loginid.getScene().getWindow().hide();
        Stage signup=new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(LoginForm.class.getResource("/views/SignUpForm.fxml"));
        Pane pane = fxmlLoader.load();
        Scene scene=new Scene(pane);
        signup.setScene(scene);
        signup.show();
        signup.setResizable(false);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
handler = new DBHandler();
    }
}

