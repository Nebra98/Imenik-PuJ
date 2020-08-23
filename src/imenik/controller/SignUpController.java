package imenik.controller;

import imenik.model.ContactsModel;
import imenik.model.UsersModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class SignUpController implements Initializable {

    @FXML
    Label statusLbl;
    @FXML
    TextField uimeTxt;
    @FXML
    TextField uprezimeTxt;
    @FXML
    TextField uemailTxt;
    @FXML
    PasswordField upasswordTxt;
    @FXML
    TextField utelefonTxt;

    @FXML
    Button registrirajseBtn;

    @FXML
    public void redirectPrijava(){
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("imenik/view/Login.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Prijavi se");
            stage.setScene(new Scene(root, 500, 350));
            stage.setResizable(false);
            stage.show();
            statusLbl.getScene().getWindow().hide();

        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void registrirajse (ActionEvent e){

        String uime = this.uimeTxt.getText();
        String uprezime = this.uprezimeTxt.getText();
        String uemail = this.uemailTxt.getText();
        String upassword = this.upasswordTxt.getText();
        String utelefon = this.utelefonTxt.getText();

        if(uime.equals("") || uprezime.equals("") || upassword.equals("")){
            statusLbl.setTextFill(Color.RED);
            statusLbl.setText("Polja sa * ne smiju biti prazna!");
        }
        else if(upassword.length() < 8){
            statusLbl.setTextFill(Color.RED);
            statusLbl.setText("Lozinka mora imati 8 i vise karaktera!");
        }
        else{
            UsersModel noviUser = new UsersModel(0, uime, uprezime, uemail, upassword, utelefon);
            noviUser.spasi();
            statusLbl.setTextFill(Color.GREEN);
            statusLbl.setText("Uspješno ste se registrirali. Izvršite prijavu");
        }
        
    }

    public void setStatusLbl(String statusLbl) {
        this.statusLbl.setText(statusLbl);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        
    }

}
