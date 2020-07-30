package imenik.controller;

import imenik.model.ContactsModel;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
        this.odabraniKontakt.setC_ime(this.uImeTxtFld.getText());
        this.odabraniKontakt.setC_prezime(this.uPrezimeTxtFld.getText());
        this.odabraniKontakt.setC_email(this.uEmailTxtFld.getText());
        this.odabraniKontakt.setC_telefon(this.uBrojTxtFld.getText());
        this.odabraniKontakt.setC_adresa(this.uAdresaTxtFld.getText());
        this.odabraniKontakt.uredi();
        System.out.println("Kontakt je uspješno ažuriran, kliknite gumb 'Osvježi' da bi ste ažurirali listu.");
        urediBtn.getScene().getWindow().hide();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        user_id = LoginController.user_id;
        titleLbl.setFocusTraversable(true);
    }

}
