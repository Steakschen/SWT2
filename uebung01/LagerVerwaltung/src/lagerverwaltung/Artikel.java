/**
 * Softwaretechnik 2 
 * Übung 1
 * @author Carsten Gross / Moritz Fey
 */
package lagerverwaltung;

/**
 * Die Klasse Artikel stellt ein Artikelobjekt mit 4 Attributen bereit.
 * 
 * @author Carsten
 */
public class Artikel {
 
    private int artikelNr;
    private String bezeichnung;
    private int bestand;
    private double preis;

    /**
     * Konstrukter
     * @param artikelNr     Artikelnummer des Artikels
     * @param bezeichnung   Bezeichnung des Artikels
     * @param bestand       Bestand des Artikels
     * @param preis         Preis des Artikels
     */
    public Artikel(int artikelNr, String bezeichnung, int bestand, double preis) {
        assert artikelNr < 1000 : "Artikelnummer nicht 4 stellig!"; 
        assert bezeichnung == null : "Bezeichnung ist Null-Referenz!"; 
        assert bezeichnung.trim().length() == 0 : "Bezeichnung darf nicht leer sein!";
        assert bezeichnung.equals(" ") : "Bezeichnung darf keine Leerzeichen enthalten!";
        assert bezeichnung.equals("\t") : "Bezeichnung darf keine Tabulatoren enthalten!";
        assert bezeichnung.equals("\n") : "Bezeichnung darf keine Zeile-Ende-Zeichen enthalten!";
        assert bezeichnung.equals("\r") : "Bezeichnung darf keine Zeile-Ende-Zeichen enthalten!";
        assert bestand < 0 : "Bestand ist kleiner als 0!";
        assert preis < 0 : "Preis ist kleiner als 0!";
        this.artikelNr = artikelNr;
        this.bezeichnung = bezeichnung;
        this.bestand = bestand;
        this.preis = preis;
    }
   
    /**
     * Konstruktor
     * @param artikelNr     Artikelnummer des Artikels
     * @param bezeichnung   Bezeichnung des Artikels
     * @param preis         Preis des Artikels
     */
    public Artikel(int artikelNr, String bezeichnung,double preis){
        this(artikelNr, bezeichnung ,0 ,preis);
    }

    public int getArtikelNr() {
        return artikelNr;
    }
    public int getBestand() {
        return bestand;
    }
    public String getBezeichnung() {
        return bezeichnung;
    }
    public double getPreis() {
        return preis;
    }

    public void setBestand(int bestand) {
        assert bestand < 0 : "Bestand ist kleiner als 0!";
        this.bestand = bestand;
    }
    public void setPreis(double preis) {
        assert preis < 0 : "Preis ist kleiner als 0!";
        this.preis = preis;
    }
    
    /**
     * bucheZugang erhöht um die in menge mitgegebene Anzahl den Bestand
     * @param menge     Anzahl um die siche der Bestand verändert
     */
    public void bucheZugang(int menge){
        assert menge < 0 : "Menge ist kleiner als 0!";
        bestand += menge;
    }  
    /**
     * bucheAbgang verringert um die in menge mitgebene Anzahl den Bestand
     * @param menge      Anzahl um die siche der Bestand verändert
     */
    public void bucheAbgang(int menge){
        assert menge < 0 : "Menge ist kleiner als 0!";
        assert (bestand-menge) < 0 : "Bestand wird kleiner als 0!";
        bestand -= menge;
    }   
    /**
     * toString gibt einen String für die Ausgabe zurück
     * @return 
     */
    @Override
    public String toString() {       
        return "\n Artikel: " + artikelNr + " Bezeichnung: "+bezeichnung
                + " Bestand: " +bestand+" Preis: "+preis;
    }
}


/**
 * Wieso kein Standard Konstruktor?
 * Weil wir sonst Objekte haben mit gleicher Artikelnummer und 
 * diese nicht mehr diefferenzieren können.
 */