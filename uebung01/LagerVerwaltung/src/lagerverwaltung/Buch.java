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
public class Buch extends Artikel {
    
    String titel;
    String autor;
    String verlag;
    
    public Buch (int artikelNr, String bezeichnung, int bestand, double preis,
            String titel, String autor, String verlag) throws MyException {
        super(artikelNr, bezeichnung, bestand, preis);
        
    }
}
