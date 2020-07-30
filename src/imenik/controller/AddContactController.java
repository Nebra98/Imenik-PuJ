package imenik.controller;

import imenik.model.ContactsModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class AddContactController implements Initializable {

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
            System.out.println("Kontakt je uspješno dodan, kliknite gumb 'Osvježi' da bi ste ažurirali listu.");
            dodajBtn.getScene().getWindow().hide();
        }
        else{
            System.out.println("Polje za ime ili prezime ne smije biti prazan");
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        user_id = LoginController.user_id;
        titleLbl.setFocusTraversable(true);
    }

}
