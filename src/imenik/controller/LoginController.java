package imenik.controller;

import imenik.model.Baza;
import imenik.model.UsersModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginController implements Initializable {

    public static int user_id;
    
    @FXML
    Label statusLbl;
    @FXML
    TextField uemailTxt;
    @FXML
    PasswordField upasswordTxt;
    @FXML
    Button prijaviseBtn;

    public void prijavise(ActionEvent e){
        String uime;
        String uprezime;
        String uemail = uemailTxt.getText();
        String upassword = upasswordTxt.getText();

        if(uemail.equals("") || upassword.equals("")){
            statusLbl.setTextFill(Color.RED);
            statusLbl.setText("Polja ne smiju biti prazna!");
        }
        else{
            if(UsersModel.logiraj(uemail, upassword)){
                statusLbl.setTextFill(Color.GREEN);
                statusLbl.setText("Uspješno ste se prijavili");
                Baza DB = new Baza();
                PreparedStatement ps = DB.exec("SELECT * FROM users WHERE u_email=?");
                try {
                    ps.setString(1, uemail);
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()){
                        user_id = rs.getInt("id");
                        uime = rs.getString("u_ime");
                        uprezime = rs.getString("u_prezime");
                        System.out.println("Prijavlili ste se kao: " + uime + " " + uprezime);


                        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("imenik/view/Contacts.fxml"));
                        Parent root = loader.load();
                        ContactsController contactsController = loader.getController();
                        contactsController.setUserLbl(uime + " " + uprezime);
                        Stage stage = new Stage();
                        stage.setTitle("Pregled vaših kontakata");
                        stage.setScene(new Scene(root, 881, 405));
                        stage.setResizable(false);
                        stage.show();
                        prijaviseBtn.getScene().getWindow().hide();
                        
                    }


                } catch (SQLException ex) {
                    System.out.println("Nastala je greska prilikom izvršavanja SQL upita: " + ex.getMessage());
                } catch (IOException ex) {
                    System.out.println("Greska prilikom učitavanja pogleda: " + ex.getMessage());
                }    catch(NullPointerException exs) {
                    System.out.print("NullPointerException Caught");
                }

            }else {
                statusLbl.setTextFill(Color.RED);
                statusLbl.setText("Korisnički podatci nisu ispravni!");
            }
        }
    }
    
    public void redirectRegistracija(ActionEvent e){
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("imenik/view/SignUp.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Registriraj se");
            stage.setScene(new Scene(root, 500, 500));
            stage.setResizable(false);
            stage.show();
            statusLbl.getScene().getWindow().hide();

        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
     
    }
}
