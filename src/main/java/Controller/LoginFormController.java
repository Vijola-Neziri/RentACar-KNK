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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import ConnectionMysql.DBHandler;

public class LoginFormController implements Initializable {
    @FXML
    private Button loginid;

    @FXML
    private TextField passwordid;

    @FXML
    private Button signupid;

    @FXML
    private TextField usernameid;

    private DBHandler handler;
    private Connection connection;
    private PreparedStatement pst;

    @FXML
    void loginaction(ActionEvent event) {
        PauseTransition pt=new PauseTransition();
        pt.setDuration(Duration.seconds(3));
        pt.setOnFinished(ev->{
            System.out.print("Login Succesfully");
        });
        pt.play();

        connection = handler.getConnection();
        String query1 = "SELECT * FROM klientet where klient_username=? and fjalekalimi_i_klientit=?";

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
                System.out.println("Login Successful");
            }else{
                System.out.println("Username and password is not correct");
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
        Parent root= FXMLLoader.load(getClass().getResource("/SignUpForm.fxml"));
        Scene scene=new Scene(root);
        signup.setScene(scene);
        signup.show();
        signup.setResizable(false);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
handler = new DBHandler();
    }
}

