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
    
    public void bucheZugang(int menge){
        
    }
    
    public void bucheAbgang(int menge){
        
    }
    
    @Override
    public String toString() {       
        return "Artikel: " + artikelNr + " Bezeichnung: "+bezeichnung
                + " Bestand: " +bestand+" Preis: "+preis;
    }
    
    
}
