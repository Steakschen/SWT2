/*
 * Softwaretechnik 2 
 * Uebung 3
 * @author Carsten Gross / Moritz Fey
 */
package lagerverwaltung;

import java.util.Calendar;

/**
 * DVD Klasse
 *
 * @author Moritz / Carsten
 */
public class DVD extends Artikel {

    /* Konstanten fuer Fehlermeldungen */
    private static final String KEIN_TITEL
            = "\tEs wurde kein Titel angegeben!\n";
    private static final String NEGATIVE_SPIELDAUER
            = "\tEs wurde eine negative/keine Spieldauer angegeben!\n";
    private static final String UNGUELTIGES_ERSCHEINUNGSJAHR
            = "\tEs wurde ein negatives oder zukünftiges Erscheinungsjahr eingegeben!\n";

    /* Attribute */
    String titel;
    double spieldauer;
    int erscheinungsjahr;

    /* Kalender für das aktuelle Jahr zu prüfen */
    Calendar cal = Calendar.getInstance();
    int aktuellesJahr = cal.get(Calendar.YEAR);

    /**
     * Konstruktor der DVD
     *
     * @param artikelNr
     * @param bezeichnung
     * @param bestand
     * @param preis
     * @param titel
     * @param spieldauer
     * @param erscheinungsjahr
     * @throws lagerverwaltung.ArtikelException
     */
    public DVD(int artikelNr, String bezeichnung, int bestand, double preis,
            String titel, double spieldauer, int erscheinungsjahr)
            throws ArtikelException {

        super(artikelNr, bezeichnung, bestand, preis);

        String msg = null;
        if (titel == null || titel.trim().length() == 0) {
            msg += KEIN_TITEL;
        }
        if (spieldauer <= 0) {
            msg += NEGATIVE_SPIELDAUER;
        }
        if (erscheinungsjahr < 0 || erscheinungsjahr > aktuellesJahr) {
            msg += UNGUELTIGES_ERSCHEINUNGSJAHR;
        }
        if (msg != null) {
            throw new ArtikelException(msg);
        } else {
            this.titel = titel;
            this.spieldauer = spieldauer;
            this.erscheinungsjahr = erscheinungsjahr;
        }
    }

    /**
     * Liefert den Titel der DVD
     *
     * @return DVD Titel
     */
    public String getTitel() {
        return titel;
    }

    /**
     * Liefert die Spieldauer der DVD
     *
     * @return Spieldauer der DVD
     */
    public double getSpieldauer() {
        return spieldauer;
    }

    /**
     * Liefert das Erscheinungsjahr der DVD
     *
     * @return Erscheinungsjahr der DVD
     */
    public int getErscheinungsjahr() {
        return erscheinungsjahr;
    }

    /**
     * Liefert den Titel der DVD als String.
     *
     * @return Titel der DVD als String
     */
    @Override
    public String getBeschreibung() {
        String beschreibung = titel;
        return beschreibung;
    }

    /**
     * toString() Methode der Klasse DVD.
     *
     * @return
     */
    @Override
    public String toString() {
        String cdString = super.toString();
        cdString = cdString + " DVD: " + this.getBeschreibung() 
                + " Spieldauer: " + this.getSpieldauer() 
                + "Erscheinungsjahr: " + this.getErscheinungsjahr();
        return cdString;
    }
}
