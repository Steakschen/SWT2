/*
 * Softwaretechnik 2 
 * Uebung 3
 * @author Carsten Gross / Moritz Fey
 */
package lagerverwaltung;

/**
 * Klasse fuer eine CD.
 *
 * @author Moritz / Carsten
 */
public class CD extends Artikel {

    /* Konstanten fuer Fehlermeldungen */
    private static final String KEIN_INTERPRET = "\tEs Wurde kein Interpret angegeben!\n";
    private static final String KEIN_TITEL = "\tEs wurde kein Titel angegeben!\n";
    private static final String KEINE_SONGS = "\tEs wurde keine Anzahl der Songs angegeben\n";

    /**
     * Der Interpret der CD.
     */
    private String interpret;

    /**
     * Der Titel der CD.
     */
    private String titel;

    /**
     * Die Anzahl der Musiktitel auf der CD.
     */
    private int anzahlTitel;

    /**
     * Konstruktor fuer eine CD.
     *
     * @param _artikelNummer Artikelnummer der CD
     * @param _bezeichnung Bezeichnung der CD
     * @param _bestand
     * @param _preis Preis der CD
     * @param _interpret Interpret der CD
     * @param _titel Titel der CD
     * @param _anzahlTitel Anzahl der Titel der CD
     * @throws lagerverwaltung.ArtikelException
     */
    public CD(int _artikelNummer, String _bezeichnung, int _bestand, double _preis,
            String _interpret, String _titel, int _anzahlTitel) throws ArtikelException {
        super(_artikelNummer, _bezeichnung, _bestand, _preis);
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
            throw new ArtikelException(msg);
        } else {
            this.interpret = _interpret;
            this.titel = _titel;
            this.anzahlTitel = _anzahlTitel;
        }
    }

    /**
     * Get-Methode fuer die Anzahl der Titel der CD.
     *
     * @return Anzahl der Titel
     */
    public int getAnzahlTitel() {
        return anzahlTitel;
    }

    /**
     * Get-Methode fuer den Interpreten der CD.
     *
     * @return Interpret der CD
     */
    public String getInterpret() {
        return interpret;
    }

    /**
     * Get-Methode fuer den Titel der CD.
     *
     * @return Titel der CD
     */
    public String getTitel() {
        return titel;
    }

    /**
     * Liefert die Beschreibung der CD als String.
     *
     * @return Beschreibung der CD als String
     */
    @Override
    public String getBeschreibung() {
        String beschreibung = interpret + ": " + titel;
        return beschreibung;
    }

    /**
     * toString() Methode der Klasse CD.
     *
     * @return
     */
    @Override
    public String toString() {
        String cdString = super.toString();
        cdString = cdString + " CD: " + this.getBeschreibung() 
                + " Anzahl Titel: "+ this.getAnzahlTitel();
        return cdString;
    }

}
