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
    public Artikel[] artikelArray;
    
    public LagerC(String standort, int lagerGroesse) {
        this.standort = standort;
        this.lagerGroesse = lagerGroesse;
        this.artikelAnzahl = 0;
        this.artikelArray = new Artikel[lagerGroesse];
                
    }
    
    
    public void artikelAnlegen(int artikelNr, String bezeichnung, int bestand, double preis) throws MyException{
        artikelArray[artikelAnzahl] = new Artikel(artikelNr, bezeichnung, bestand, preis);
        artikelAnzahl += 1;
        
    }
    public void artikelAnlegen(int artikelNr, String bezeichnung, double preis) throws MyException{
        artikelArray[artikelAnzahl] = new Artikel(artikelNr, bezeichnung, preis);
        artikelAnzahl += 1;
    }
    
    public void artikelLöschen(int artikelNr){
        int postition = findeArtikel(artikelNr);
        if (postition >= 0){
            for (int i = postition; i < artikelAnzahl ; i++) {
                artikelArray[postition] = artikelArray[postition+1];
            }
            artikelAnzahl -=1;
        }else{
            System.out.println("Artikel: " +artikelNr + " nicht gefunden!");
        }
        
    }
    public void artikelLöschen(String bezeichnung){
        int postition = findeArtikel(bezeichnung);
        if (postition >= 0){
            for (int i = postition; i < artikelAnzahl ; i++) {
                artikelArray[postition] = artikelArray[postition+1];
            }
            artikelAnzahl -=1;
        }else{
            System.out.println("Artikel: " +bezeichnung + " nicht gefunden!");
        }
    }
    
    public void zugangBuchen(int artikelNr, int menge) throws MyException{
        int position = findeArtikel(artikelNr);
        artikelArray[position].bucheZugang(menge);
    }
    
    public void abgangBuchen(int artikelNr, int menge) throws MyException{
        int position = findeArtikel(artikelNr);
        artikelArray[position].bucheAbgang(menge);
    }
    
    public void preisÄndern(Artikel artikel, int prozentSatz) throws MyException{
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

    private int findeArtikel(int artikelNr) {
        for (int i = 0; i < artikelArray.length ;i++){
            if (artikelArray[i].getBestand() == artikelNr){
                return i;
            }
        }
        return -1;
    }

    private int findeArtikel(String bezeichnung) {
        for (int i = 0; i < artikelArray.length ;i++){
            if (artikelArray[i].getBezeichnung().equals(bezeichnung)){
                return i;
            }
        }
        return -1;
    }
    
}
