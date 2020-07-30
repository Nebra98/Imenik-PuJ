package imenik.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Baza extends Konekcija{

    private Statement upit;
    private PreparedStatement execUpit;

    // pravi se objekt DB tipa Baza, poziva se defaultni konstruktor
    public static final Baza DB = new Baza();

    public Baza(){
        super();
        try {
            this.upit =this.konekcija.createStatement();
            this.upit.execute("SET NAMES utf8");
        } catch (SQLException ex) {
            System.out.println("Nastala je greška prilikom izvršavanja upita " + ex.getMessage());
        }
    }

    public ResultSet select(String sql){
        try {
            this.upit = this.konekcija.createStatement();
            return this.upit.executeQuery(sql);
        } catch (SQLException ex) {
            System.out.println("Nastala je greška prilikom izvršavanja upita " + ex.getMessage());
            return null;
        }
    }

    public PreparedStatement exec (String sql){
        try {
            this.execUpit = this.konekcija.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            return this.execUpit;
        } catch (SQLException ex) {
            System.out.println("Nastala je greska prilikom izvrsavanja upita " + ex.getMessage());
        }
        return null;
    }

}
