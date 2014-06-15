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
public class CD extends Artikel {
    
    private static final String KEIN_INTERPRET  = "\tEs Wurde kein Interpret angegeben!\n";
    private static final String KEIN_TITEL      = "\tEs wurde kein Titel angegeben!\n";
    private static final String KEINE_SONGS     = "\tEs wurde keine Anzahl der Songs angegeben\n";
    
    private String interpret;
    private String titel;
    private int anzahlTitel;
    
    public CD(int _artikelNummer, String _bezeichnung, double _preis, String _interpret, String _titel, int _anzahlTitel) throws MyException {
       super(_artikelNummer, _bezeichnung, _preis);
       String msg = null;
       if (_interpret == null || _interpret.trim().length() == 0) {
           msg += KEIN_INTERPRET;
       }
       if (_titel == null || _titel.trim().length() == 0) {
           msg += KEIN_TITEL;
       }
       if (_anzahlTitel <= 0) {
           msg += KEINE_SONGS;
       }
       if (msg != null) {
           throw new MyException(msg);
       }
       else {
           this.interpret   = _interpret;
           this.titel       = _titel;
           this.anzahlTitel = _anzahlTitel;
       }
    }
    
    public String getBeschreibung() {
        String beschreibung = new String(interpret + ": " + titel);
        return beschreibung;
    }
    
    public String toString() {
        String cdString = super.toString();
        cdString = cdString + " " + this.getBeschreibung();
        return cdString;
    }
    
}
