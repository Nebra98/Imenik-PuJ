package imenik.controller;

import imenik.model.ContactsModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class AddContactController extends ContactsController implements Initializable {

    public static int user_id;

    @FXML
    Button dodajBtn;
    @FXML
    TextField imeTxtFld;
    @FXML
    TextField prezimeTxtFld;
    @FXML
    TextField brojTxtFld;
    @FXML
    TextField emailTxtFld;
    @FXML
    TextField adresaTxtFld;

    @FXML
    Label titleLbl;


    public void dodajKontakt(ActionEvent e){
        String ime = this.imeTxtFld.getText();
        String prezime = this.prezimeTxtFld.getText();
        String broj = this.brojTxtFld.getText();
        String email = this.emailTxtFld.getText();
        String adresa = this.adresaTxtFld.getText();

        if(!ime.trim().isEmpty() && !prezime.trim().isEmpty()) {
            ContactsModel novi = new ContactsModel(0, user_id, ime, prezime, broj, email, adresa);
            novi.spasi();
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Uspješno ste dodali kontakt!");
            alert.setHeaderText(null);
            alert.setContentText("Kontakt je uspješno dodan.\nKliknite gumb 'Osvježi' da bi ste ažurirali listu.");

            alert.showAndWait();
            
            dodajBtn.getScene().getWindow().hide();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Upozorenje");
            alert.setHeaderText("Morate popuniti obavezna polja");
            alert.setContentText("Polja za ime i prezime ne smiju biti prazna!");
            alert.showAndWait();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        user_id = LoginController.user_id;
        titleLbl.setFocusTraversable(true);
    }

}
