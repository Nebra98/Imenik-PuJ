package imenik.controller;

import imenik.model.ContactsModel;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class EditContactsController implements Initializable {

    public static int user_id;

    @FXML
    Button urediBtn;
    @FXML
    TextField uImeTxtFld;
    @FXML
    TextField uPrezimeTxtFld;
    @FXML
    TextField uBrojTxtFld;
    @FXML
    TextField uEmailTxtFld;
    @FXML
    TextField uAdresaTxtFld;

    @FXML
    Label titleLbl;

    ContactsModel odabraniKontakt;

    public void setuImeTxtFld(String uImeTxtFld) {
        this.uImeTxtFld.setText(uImeTxtFld);
    }

    public void setuPrezimeTxtFld(String uPrezimeTxtFld) {
        this.uPrezimeTxtFld.setText(uPrezimeTxtFld);
    }

    public void setuEmailTxtFld(String uEmailTxtFld) {
        this.uEmailTxtFld.setText(uEmailTxtFld);
    }

    public void setuBrojTxtFld(String uBrojTxtFld) {
        this.uBrojTxtFld.setText(uBrojTxtFld);
    }

    public void setuAdresaTxtFld(String uAdresaTxtFld) {
        this.uAdresaTxtFld.setText(uAdresaTxtFld);
    }

    @FXML
    public void urediKontakt(Event e){

        String ime = this.uImeTxtFld.getText();
        String prezime = this.uImeTxtFld.getText();

        if(!ime.trim().isEmpty() && !prezime.trim().isEmpty()) {
            this.odabraniKontakt.setC_ime(this.uImeTxtFld.getText());
            this.odabraniKontakt.setC_prezime(this.uPrezimeTxtFld.getText());
            this.odabraniKontakt.setC_email(this.uEmailTxtFld.getText());
            this.odabraniKontakt.setC_telefon(this.uBrojTxtFld.getText());
            this.odabraniKontakt.setC_adresa(this.uAdresaTxtFld.getText());
            this.odabraniKontakt.uredi();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Kontakt ažuriran!");
            alert.setHeaderText(null);
            alert.setContentText("Kontakt je uspješno ažuriran.\nKliknite gumb 'Osvježi' da bi ste ažurirali listu.");
            alert.showAndWait();
            urediBtn.getScene().getWindow().hide();
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
