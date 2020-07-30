package imenik.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersModel {

    SimpleIntegerProperty u_sifra = new SimpleIntegerProperty();
    SimpleStringProperty u_ime = new SimpleStringProperty();
    SimpleStringProperty u_prezime = new SimpleStringProperty();
    SimpleStringProperty u_email = new SimpleStringProperty();
    SimpleStringProperty u_password = new SimpleStringProperty();
    SimpleStringProperty u_telefon = new SimpleStringProperty();

    public UsersModel (Integer u_sifra, String u_ime, String u_prezime, String u_email, String u_password, String u_telefon){
        this.u_sifra = new SimpleIntegerProperty(u_sifra);
        this.u_ime = new SimpleStringProperty(u_ime);
        this.u_prezime = new SimpleStringProperty(u_prezime);
        this.u_email = new SimpleStringProperty(u_email);
        this.u_password = new SimpleStringProperty(u_password);
        this.u_telefon = new SimpleStringProperty(u_telefon);

    }


    public Integer getU_sifra (){return u_sifra.get();}

    public String getU_ime() {
        return u_ime.get();
    }

    public String getU_prezime() {
        return u_prezime.get();
    }

    public String getU_email() {
        return u_email.get();
    }

    public String getU_password() {
        return u_password.get();
    }

    public String getU_telefon() {
        return u_telefon.get();
    }


    public void setU_ime(String u_ime) {
        this.u_ime = new SimpleStringProperty(u_ime);
    }

    public void setU_prezime(String u_prezime) {
        this.u_prezime = new SimpleStringProperty(u_prezime);
    }

    public void setU_email(String u_email) {
        this.u_email = new SimpleStringProperty(u_email);
    }

    public void setU_password(String u_password) {
        this.u_password = new SimpleStringProperty(u_password);
    }

    public void setU_phone(String u_phone) {
        this.u_telefon = new SimpleStringProperty(u_phone);
    }

    public void spasi(){
        PreparedStatement upit =Baza.DB.exec("INSERT INTO users VALUES (null, ?, ?, ?, ?, ?)");
        try {
            upit.setString(1, this.getU_ime());
            upit.setString(2, this.getU_prezime());
            upit.setString(3, this.getU_email());
            upit.setString(4, this.getU_password());
            upit.setString(5, this.getU_telefon());
            upit.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Greška prilikom registracije korisnika: " + ex.getMessage());
        }
    }

    public static boolean logiraj(String uemail, String upassword){
        Baza db = new Baza();
        PreparedStatement ps = db.exec("SELECT * FROM users WHERE u_email =? AND "
                + "password=?");

        try {
            ps.setString(1, uemail);
            ps.setString(2, upassword);
            ResultSet rs =ps.executeQuery();
            if(rs.next()){
                return true;
            }
            else{
                return false;
            }
        } catch (SQLException ex) {
            System.out.println("Nastala je greška: " + ex.getMessage());
            return false;
        }
    }


}
