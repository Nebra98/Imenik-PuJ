// Samo podešavanja za konekciju s bazom podataka, nema nikakve veze s ostatkom projekta

package imenik.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Konekcija {

    private String host;
    private String korisnik;
    private String lozinka;
    private String baza;

    // mora se ukljuciti sql biblioteka
    protected Connection konekcija;

    // konstruktor sa parametrima za spajanje na bazu
    public Konekcija(){
        this.host = "localhost";
        this.korisnik = "root";
        this.lozinka = "";
        this.baza = "puj3";
        this.spoji();
    }

    // konstruktor s parametrima
    public Konekcija(String host, String korisnik, String lozinka, String baza){
        this.host = host;
        this.korisnik = korisnik;
        this.lozinka = lozinka;
        this.baza = baza;
        this.spoji();
    }

    public void spoji(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.konekcija = DriverManager.getConnection("jdbc:mysql://"+this.host+"/"+this.baza+"?"
                    + "user="+this.korisnik+"&password="+this.lozinka);

        } catch (ClassNotFoundException e) {
            System.out.println("Sustav nije uspio pronaći klasu za konekciju na MySql!");
        } catch (SQLException throwables) {
            System.out.println("Sustav se nije mogao spojiti na bazu podataka!");
        }
    }

    public void odspoji(){
        try {
            this.konekcija.close();
        } catch (SQLException throwables) {
            System.out.println("Sustav nije uspio prekinuti konekciju s bazom!");
        }
    }

}
