package imenik.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContactsModel {

    SimpleIntegerProperty c_sifra = new SimpleIntegerProperty();
    SimpleIntegerProperty c_user_id = new SimpleIntegerProperty();
    SimpleStringProperty c_ime = new SimpleStringProperty();
    SimpleStringProperty c_prezime = new SimpleStringProperty();
    SimpleStringProperty c_telefon = new SimpleStringProperty();
    SimpleStringProperty c_email = new SimpleStringProperty();
    SimpleStringProperty c_adresa = new SimpleStringProperty();

    public ContactsModel(Integer c_sifra, Integer c_user_id, String c_ime, String c_prezime, String c_telefon, String c_email, String c_adresa){
        this.c_sifra = new SimpleIntegerProperty(c_sifra);
        this.c_user_id = new SimpleIntegerProperty(c_user_id);
        this.c_ime = new SimpleStringProperty(c_ime);
        this.c_prezime = new SimpleStringProperty(c_prezime);
        this.c_telefon = new SimpleStringProperty(c_telefon);
        this.c_email = new SimpleStringProperty(c_email);
        this.c_adresa = new SimpleStringProperty(c_adresa);
    }

    public Integer getC_sifra() {
        return c_sifra.get();
    }

    public int getC_user_id() {
        return c_user_id.get();
    }

    public String getC_ime() {
        return c_ime.get();
    }

    public String getC_prezime() {
        return c_prezime.get();
    }

    public String getC_telefon() {
        return c_telefon.get();
    }

    public String getC_email() {
        return c_email.get();
    }

    public String getC_adresa() {
        return c_adresa.get();
    }

    //setteri
    public void setC_user_id(Integer c_user_id) {
        this.c_user_id = new SimpleIntegerProperty(c_user_id);
    }

    public void setC_ime(String c_ime) {
        this.c_ime = new SimpleStringProperty(c_ime);
    }

    public void setC_prezime(String c_prezime) {
        this.c_prezime = new SimpleStringProperty(c_prezime);
    }

    public void setC_telefon(String c_telefon) {
        this.c_telefon = new SimpleStringProperty(c_telefon);
    }

    public void setC_email(String c_email) {
        this.c_email = new SimpleStringProperty(c_email);
    }

    public void setC_adresa(String c_adresa) {
        this.c_adresa = new SimpleStringProperty(c_adresa);
    }

    public void spasi(){
        PreparedStatement upit = Baza.DB.exec("INSERT INTO contacts VALUES (null, ?, ?, ?, ?, ?, ?)");

        try {
            upit.setInt(1, this.getC_user_id());
            upit.setString(2, this.getC_ime());
            upit.setString(3,this.getC_prezime());
            upit.setString(4,this.getC_telefon());
            upit.setString(5,this.getC_email());
            upit.setString(6,this.getC_adresa());
            upit.executeUpdate();
            

        } catch (SQLException ex) {
            System.out.println("Greška prilikom unosa novnog kontakta u bazu: " + ex.getMessage());
        }

    }

    public void uredi(){
        PreparedStatement upit = Baza.DB.exec("UPDATE contacts SET c_ime=?, c_prezime=?, c_email=?, c_telefon=?, c_adresa=? WHERE id=?");
        try {
            upit.setString(1, this.getC_ime());
            upit.setString(2, this.getC_prezime());
            upit.setString(3, this.getC_email());
            upit.setString(4, this.getC_telefon());
            upit.setString(5, this.getC_adresa());
            upit.setInt(6, this.getC_sifra());
            upit.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Greska prilikom uredjivanja korisnika: " + e.getMessage());
        }
    }

    public void brisi(){
        PreparedStatement upit = Baza.DB.exec("DELETE FROM contacts WHERE id=?");
        try {
            upit.setInt(1, this.getC_sifra());
            upit.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Greska prilikom brisanja korisnika: " + ex.getMessage());
        }
    }

    public static ObservableList<ContactsModel> listaKontakata (int user_id){
        ObservableList<ContactsModel> lista = FXCollections.observableArrayList();

        PreparedStatement ps = Baza.DB.exec("SELECT * from contacts where user_id=?");

       // ResultSet rs;

        try {
            ps.setInt(1, user_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                lista.add(new ContactsModel(rs.getInt("id"), rs.getInt("user_id"), rs.getString("c_ime"), rs.getString("c_prezime"), rs.getString("c_telefon"), rs.getString("c_email"), rs.getString("c_adresa") ));
            }
        } catch (SQLException ex) {
            System.out.println("Nastala je greška prilikom iteriranja: " + ex.getMessage());
        }

        return lista;
    }



}
