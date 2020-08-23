1. Što je potrebno: 
    - instaliran lokalni server npr. xampp
    - instaliran phpadminpanel (mysql)
    
2. Pokrenuti komandu: git clone https://github.com/Nebra98/Imenik-PuJ.git , ili Download ZIP
    
6. Napraviti novu bazu podataka u phpmyadmin (npr. ime: puj3), te je potrebno napraviti import file-a puj3.sql koji se nalazi u samom projektu

7. U \src\imenik\model\Konekcija.java podesiti konstruktor za spajanje s bazom podataka: 
   ```
     // konstruktor sa parametrima za spajanje na bazu
    public Konekcija(){
        this.host = "localhost";
        this.korisnik = "root";
        this.lozinka = "";
        this.baza = "ime_baze_podataka"; // npr puj3
        this.spoji();
    }
    
   ```

8. Pokrenuti aplikaciju


## Slike sučelja aplikacije aplikacije <h2>

![GitHub Logo](/screenshots/prijava.PNG)

![GitHub Logo](/screenshots/registracija.PNG)

![GitHub Logo](/screenshots/lista_kontakata.PNG)

![GitHub Logo](/screenshots/dodaj_kontakt.PNG)

![GitHub Logo](/screenshots/dialog.PNG)

![GitHub Logo](/screenshots/dialog2.PNG)

![GitHub Logo](/screenshots/dialog3.PNG)
