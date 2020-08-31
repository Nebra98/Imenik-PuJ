## Programiranje u Javi

### Imenik aplikacija izrađena pomoću JavaFX frameworka

Aplikacija omogućava prijavu i registraciju korisnika kroz jednostavno korisničko sučelje, te pohranu kontakata prijavljenog korisnika.
Prijavljeni korisnik može dodati kontakt, urediti kontakt, izbrisati kontakt te pretražiti kontakt na osnovu bilo kojeg parametra. 
Sam rad unutar aplikacije popraćen je dialoškim okvirima za određenu radnju.

Na projektu su radili: Hrvoje Ramljak, Luka Pervan, Branimir Bulić

Dolje ispod se nalaze upute za instalaciju aplikacije, te snimke zaslona korisničkog sučelja.



###### Kratke upute:

1. 
    - potrebno je instalirati lokalni server npr. xampp
    - instaliran phpadminpanel (mysql)
    
2. Pokrenuti komandu: git clone https://github.com/Nebra98/Imenik-PuJ.git , ili Download ZIP
    
3. Pokrenuti lokalni server: Modeule Apache, Module MySQL, te preko web preglednika otvoriti http://localhost/phpmyadmin/
    
4. Napraviti novu bazu podataka u phpmyadmin (npr. ime: puj3), te je potrebno napraviti import file-a puj3.sql koji se nalazi u samom projektu

5. U Vašem razvojnom okruženju je potrebno importati projekt (morate imati instalirane module za JavaFX framework)

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


## Slike sučelja aplikacije

![GitHub Logo](/screenshots/prijava.PNG)

![GitHub Logo](/screenshots/registracija.PNG)

![GitHub Logo](/screenshots/lista_kontakata.PNG)

![GitHub Logo](/screenshots/dodaj_kontakt.PNG)

![GitHub Logo](/screenshots/dialog.PNG)

![GitHub Logo](/screenshots/dialog2.PNG)

![GitHub Logo](/screenshots/dialog3.PNG)
