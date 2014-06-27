/*
 * Softwaretechnik 2 
 * Uebung 3
 * @author Carsten Gross / Moritz Fey
 */
package lagerverwaltung;

/**
 * Buch Klasse
 *
 * @author Moritz / Carsten
 */
public class Buch extends Artikel {

    /* Konstanten fuer Fehlermeldungen */
    private static final String KEIN_TITEL
            = "\tEs wurde kein Titel angegeben!\n";
    private static final String KEIN_AUTOR
            = "\tEs wurde kein Autor angegeben!\n";
    private static final String KEIN_VERLAG
            = "\tEs wurde kein Verlag angegeben!\n";
    
    static final double MWST = 7;

    /**
     * Attribute
     */
    String titel;
    String autor;
    String verlag;

    /**
     * Konstruktor des Buchs
     *
     * @param artikelNr
     * @param bezeichnung
     * @param bestand
     * @param preis
     * @param titel
     * @param autor
     * @param verlag
     * @throws lagerverwaltung.ArtikelException
     */
    public Buch(int artikelNr, String bezeichnung, int bestand, double preis,
            String titel, String autor, String verlag) throws ArtikelException {

        super(artikelNr, bezeichnung, bestand, preis);

        String msg = null;
        if (titel == null || titel.trim().length() == 0) {
            msg += KEIN_TITEL;
        }
        if (autor == null || autor.trim().length() == 0) {
            msg += KEIN_AUTOR;
        }
        if (verlag == null || autor.trim().length() == 0) {
            msg += KEIN_VERLAG;
        }
        if (msg != null) {
            throw new ArtikelException(msg);
        } else {
            this.titel = titel;
            this.autor = autor;
            this.verlag = verlag;
        }
    }

    /**
     * Liefert den Titel des Buchs
     *
     * @return Buchtitel
     */
    public String getTitel() {
        return titel;
    }

    /**
     * Liefert den Autor des Buchs
     *
     * @return Buchautor
     */
    public String getAutor() {
        return autor;
    }

    /**
     * Liefert den Verlag
     *
     * @return Verlag des Buchs
     */
    public String getVerlag() {
        return verlag;
    }

    /**
     * Liefert den Autor und Titel des Buchs als String.
     *
     * @return Titel der DVD als String
     */
    @Override
    public String getBeschreibung() {
        String beschreibung = new String(autor + ": " + titel);
        return beschreibung;
    }

    /**
     * toString() Methode der Klasse Buch.
     *
     * @return
     */
    @Override
    public String toString() {
        String cdString = super.toString();
        cdString = cdString + " Buch: " + this.getBeschreibung()
                + " Verlag: " +this.getVerlag();
        return cdString;
    }
}
