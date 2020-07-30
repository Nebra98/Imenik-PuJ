package imenik.controller;

import imenik.model.Baza;
import imenik.model.ContactsModel;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ContactsController extends LoginController implements Initializable {

    // glavni view kontakata

    public static int user_id;

    @FXML
    TextField fiterField;

    @FXML
     private Label userLbl;

    @FXML
    TableView kontaktiTbl;

    @FXML
    TableColumn imeTblCol;

    @FXML
    TableColumn prezimeTblCol;

    @FXML
    TableColumn emailTblCol;

    @FXML
    TableColumn adresaTblCol;

    @FXML
    TableColumn telefonTblCol;

    @FXML
    Button dodajKontaktVBtn;

    @FXML
    Button urediKontaktVBtn;

    @FXML
    Button refreshBtn;

    @FXML
    Button odjavaBtn;

    @FXML
    Button deleteBtn;

    ContactsModel odabraniKontakt;
    
    @FXML
    public static Label successLbl;

    public void setUserLbl(String name){
        userLbl.setText(name);
    }


    public void dodajKontaktView(){

        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("imenik/view/AddContact.fxml"));
        Parent root = null;
        try {
            root = loader.load();
            AddContactController addContactController = loader.getController();
            Stage stage = new Stage();
            stage.setTitle("Dodaj kontakt");
            stage.setScene(new Scene(root, 503, 311));
            stage.show();
            
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void urediKontaktView(){

        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("imenik/view/EditContacts.fxml"));
        Parent root = null;
        try {
            root = loader.load();
            EditContactsController editContactController = loader.getController();
            Stage stage = new Stage();
            stage.setTitle("Uredi kontakt");
            stage.setScene(new Scene(root, 503, 311));
            editContactController.odabraniKontakt = (ContactsModel) this.kontaktiTbl.getSelectionModel().getSelectedItem();
            editContactController.setuImeTxtFld(this.odabraniKontakt.getC_ime());
            editContactController.setuPrezimeTxtFld(this.odabraniKontakt.getC_prezime());
            editContactController.setuEmailTxtFld(this.odabraniKontakt.getC_email());
            editContactController.setuBrojTxtFld(this.odabraniKontakt.getC_telefon());
            editContactController.setuAdresaTxtFld(this.odabraniKontakt.getC_adresa());
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void postavi(){
        ObservableList<ContactsModel> data = ContactsModel.listaKontakata(user_id);

        imeTblCol.setCellValueFactory(new PropertyValueFactory<ContactsModel, String>("c_ime"));
        prezimeTblCol.setCellValueFactory(new PropertyValueFactory<ContactsModel, String>("c_prezime"));
        emailTblCol.setCellValueFactory(new PropertyValueFactory<ContactsModel, String>("c_email"));
        adresaTblCol.setCellValueFactory(new PropertyValueFactory<ContactsModel, String>("c_adresa"));
        telefonTblCol.setCellValueFactory(new PropertyValueFactory<ContactsModel, String>("c_telefon"));
        kontaktiTbl.setItems(data);

        FilteredList<ContactsModel> filteredData = new FilteredList<>(data, b->true);

        // 2. Set the filter Predicate whenever the filter changes.
        fiterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(contacts -> {
                // If filter text is empty, display all persons.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (contacts.getC_ime().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true; // Filter matches first name.
                } else if (contacts.getC_prezime().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                }
                else if (contacts.getC_email().toLowerCase().indexOf(lowerCaseFilter) != -1)
                    return true;
                else
                    return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList.
        SortedList<ContactsModel> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(kontaktiTbl.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        kontaktiTbl.setItems(sortedData);
    }

    @FXML
    public void odjaviSe(){
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("imenik/view/Login.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Prijavi se");
            stage.setScene(new Scene(root, 500, 350));
            stage.show();
            odjavaBtn.getScene().getWindow().hide();
            System.out.println("Uspješno ste se odjavili!");

        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void odaberiKorisnika(Event e){
        this.odabraniKontakt = (ContactsModel) this.kontaktiTbl.getSelectionModel().getSelectedItem();
        // this.imeTxtFld.setText(this.odabraniKontakt.getIme());
        // this.prezimeTxtFld.setText(this.odabraniKontakt.getPrezime());
        // this.emailTxtFld.setText(this.odabraniKontakt.getEmail());
    }

    @FXML
    public void izbrisiKontakt(Event e){
        if(this.odabraniKontakt != null){
            this.odabraniKontakt.brisi();
            ObservableList<ContactsModel> data = ContactsModel.listaKontakata(user_id);
            this.kontaktiTbl.setItems(data);
            System.out.println("Kontakt uspješno izbrisan.");
        }

    }

    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //TODO
        user_id = LoginController.user_id;
        
        ObservableList<ContactsModel> data = ContactsModel.listaKontakata(user_id);

        imeTblCol.setCellValueFactory(new PropertyValueFactory<ContactsModel, String>("c_ime"));
        prezimeTblCol.setCellValueFactory(new PropertyValueFactory<ContactsModel, String>("c_prezime"));
        emailTblCol.setCellValueFactory(new PropertyValueFactory<ContactsModel, String>("c_email"));
        adresaTblCol.setCellValueFactory(new PropertyValueFactory<ContactsModel, String>("c_adresa"));
        telefonTblCol.setCellValueFactory(new PropertyValueFactory<ContactsModel, String>("c_telefon"));
        kontaktiTbl.setItems(data);

        FilteredList<ContactsModel> filteredData = new FilteredList<>(data, b->true);

        // 2. Set the filter Predicate whenever the filter changes.
        fiterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(contacts -> {
                // If filter text is empty, display all persons.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (contacts.getC_ime().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true; // Filter matches first name.
                } else if (contacts.getC_prezime().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                }

                else if (contacts.getC_email().toLowerCase().indexOf(lowerCaseFilter) != -1)
                    return true;

                else if (contacts.getC_adresa().toLowerCase().indexOf(lowerCaseFilter) != -1)
                    return true;
                else if (contacts.getC_telefon().toLowerCase().indexOf(lowerCaseFilter) != -1)
                    return true;
                else
                    return false; // Does not match.
            });
        });
        
        // 3. Wrap the FilteredList in a SortedList.
        SortedList<ContactsModel> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(kontaktiTbl.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        kontaktiTbl.setItems(sortedData);
        

    }
}
