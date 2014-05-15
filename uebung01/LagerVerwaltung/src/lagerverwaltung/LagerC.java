/*
 * Softwaretechnik 2 
 * Uebung 1
 * @author Carsten Gross / Moritz Fey
 */

package lagerverwaltung;

/**
 *
 * @author Carsten
 */
public class LagerC {
    
    public String standort;
    public int lagerGroesse;
    public int artikelAnzahl;
    public Artikel[] artikelArray = new Artikel[lagerGroesse];

    
    public LagerC(String standort, int lagerGroesse, int artikelAnzahl) {
        this.standort = standort;
        this.lagerGroesse = lagerGroesse;
        this.artikelAnzahl = artikelAnzahl;
    }
    
    
    public void artikelAnlegen(int artikelNr, String bezeichnung, int bestand, double preis){
        artikelArray[artikelAnzahl] = new Artikel(artikelNr, bezeichnung, bestand, preis);
    }
    public void artikelAnlegen(int artikelNr, String bezeichnung, double preis){
        artikelArray[artikelAnzahl] = new Artikel(artikelNr, bezeichnung, preis);
    }
    
    public void artikelLöschen(){
        
    }
    
    public void zugangBuchen(Artikel artikel, int menge){
        artikel.bucheZugang(menge);
    }
    
    public void abgangBuchen(Artikel artikel, int menge){
        artikel.bucheAbgang(menge);
    }
    
    public void preisÄndern(Artikel artikel, int prozentSatz){
        assert prozentSatz <= 0 : "Prozentsatz zu niedrig!";
        assert prozentSatz >= 50 : "Prozentsatz zu hoch!";
        for (Artikel artikelIn : artikelArray) {
            double aktuellerPreis = artikelIn.getPreis();
            double neuerPreis = aktuellerPreis * prozentSatz;
            artikelIn.setPreis(neuerPreis);
        }
    }
    
    public void lagerbestandAusgeben(){
        //array durchlaufen und die toString von Artikel aufrufen
        for (Artikel artikelIn : artikelArray) {
            System.out.println(artikelIn);
        }
    }
    
}
