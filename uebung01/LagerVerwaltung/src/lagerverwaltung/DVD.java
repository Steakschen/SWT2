/*
 * Softwaretechnik 2 
 * Uebung 1
 * @author Carsten Gross / Moritz Fey
 */

package lagerverwaltung;

/**
 *
 * @author Moritz
 */
public class DVD extends Artikel {
    
    String titel;
    double spieldauer;
    int erscheinungsjahr;
    public DVD(int artikelNr, String bezeichnung, int bestand, double preis, 
            String titel, double spieldauer, int erscheinungsjahr) throws MyException {
        super(artikelNr, bezeichnung, bestand, preis);
    }
}
