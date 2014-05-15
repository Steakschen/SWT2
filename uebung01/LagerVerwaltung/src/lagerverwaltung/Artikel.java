/**
 * Softwaretechnik 2 
 * Übung 1
 * @author Carsten Gross / Moritz Fey
 */

package lagerverwaltung;

/**
 *
 * @author Carsten
 */
public class Artikel {
    
    private int artikelNr;
    private String bezeichnung;
    private int bestand;
    private double preis;

    public Artikel(int artikelNr, String bezeichnung, int bestand, double preis) {
        
        this.artikelNr = artikelNr;
        this.bezeichnung = bezeichnung;
        this.bestand = bestand;
        this.preis = preis;
    }
    
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
        this.bestand = bestand;
    }
    public void setPreis(double preis) {
        this.preis = preis;
    }
    
        
    public void bucheZugang(int menge){
        bestand += menge;
    }  
    public void bucheAbgang(int menge){
        bestand -= menge;
    }   
    @Override
    public String toString() {       
        return "Artikel: " + artikelNr + " Bezeichnung: "+bezeichnung
                + " Bestand: " +bestand+" Preis: "+preis;
    }
    
    
}
