/**
 * Softwaretechnik 2 Übung 3
 *
 * @author Carsten Gross / Moritz Fey
 */
package lagerverwaltung;

import java.text.DecimalFormat;

/**
 * Die Klasse Artikel stellt ein Artikelobjekt mit 4 Attributen bereit.
 *
 * @author Carsten / Moritz
 */
public class Artikel {

    /* Konstanten */
    static int ARTIKELNR_MIN_SIZE = 1000;
    static int ARTIKELNR_MAX_SIZE = 10000;
    static int BESTAND_MIN = 0;
    static int PREIS_MIN = 0;
    static double MWST = 1.19;
    static String ARTIKELNR_SIZE_EX = "Artikelnummer nicht 4 stellig!";
    static String BEZEICHNUNG_NULL_EX = "Bezeichnung ist Null-Referenz oder leer!";
    static String BESTAND_MIN_EX = "Bestand ist/wird kleiner als 0!";
    static String PREIS_MIN_EX = "Preis ist/wird kleiner als 0!";
    static String MENGE_MIN_EX = "Menge ist kleiner als 0!";

    /* Attribute */
    private int artikelNr;
    private String bezeichnung;
    private int bestand;
    private double preis;

    /**
     * Standardkonstruktor (benötigt für ausgebenBestandsListe in Lager)
     */
    public Artikel() {
    }

    /**
     * Konstrukter
     *
     * @param artikelNr Artikelnummer des Artikels
     * @param bezeichnung Bezeichnung des Artikels
     * @param bestand Bestand des Artikels
     * @param preis Preis des Artikels
     * @throws lagerverwaltung.MyException
     */
    public Artikel(int artikelNr, String bezeichnung, int bestand, double preis) 
            throws MyException {
        String exMsg = null;

        if (artikelNr < ARTIKELNR_MIN_SIZE && artikelNr < ARTIKELNR_MAX_SIZE) {
            exMsg += ARTIKELNR_SIZE_EX;
        }
        if (bezeichnung == null || bezeichnung.trim().length() == 0) {
            exMsg += ARTIKELNR_SIZE_EX;
        }
        if (bestand < 0) {
            exMsg += BESTAND_MIN_EX;
        }
        if (preis < 0) {
            exMsg += PREIS_MIN_EX;
        }
        if (exMsg != null) {
            throw new MyException(exMsg);
        }
        this.artikelNr = artikelNr;
        this.bezeichnung = bezeichnung;
        this.bestand = bestand;
        this.preis = preis;
    }

    /**
     * Konstruktor
     *
     * @param artikelNr Artikelnummer des Artikels
     * @param bezeichnung Bezeichnung des Artikels
     * @param preis Preis des Artikels
     * @throws lagerverwaltung.MyException
     */
    public Artikel(int artikelNr, String bezeichnung, double preis) throws MyException {
        this(artikelNr, bezeichnung, 0, preis);
    }

    /**
     * Gibt die Artikelnummer zurück
     *
     * @return
     */
    public int getArtikelNr() {
        return artikelNr;
    }

    /**
     * Gibt den aktuellen Bestand des Artikels zurück
     *
     * @return
     */
    public int getBestand() {
        return bestand;
    }

    /**
     * Gibt die Bezeichnung des Artikels zurück
     *
     * @return
     */
    public String getBezeichnung() {
        return bezeichnung;
    }

    /**
     * Gibt die Bezeichnung zurück muss so sein da die Namenskonvention in der
     * Übung nicht gleich sind (ueb1 Bezeichnung / ueb3 Beschreibung)
     *
     * @return
     */
    public String getBeschreibung() {
        return bezeichnung;
    }

    /**
     * Gibt den Preis des Artikels zurück
     *
     * @return
     */
    public double getPreis() {
        return preis;
    }

    /**
     * Setzt den Preis des Artikels
     *
     * @param preis
     * @throws MyException
     */
    public void setPreis(double preis) throws MyException {
        String exMsg = null;
        if (preis < 0.0) {
            exMsg += PREIS_MIN_EX;
        }
        if (exMsg != null) {
            throw new MyException(exMsg);
        }
        this.preis = preis;
    }

    /**
     * bucheZugang erhöht um die in menge mitgegebene Anzahl den Bestand
     *
     * @param menge Anzahl, um die sich der Bestand verändert
     * @throws lagerverwaltung.MyException
     */
    public void bucheZugang(int menge) throws MyException {
        String exMsg = null;
        if (menge < 0.0) {
            exMsg += MENGE_MIN_EX;
        }
        if (exMsg != null) {
            throw new MyException(exMsg);
        }
        bestand += menge;
    }

    /**
     * bucheAbgang verringert um die in menge mitgebene Anzahl den Bestand
     *
     * @param menge Anzahl um die siche der Bestand verändert
     * @throws lagerverwaltung.MyException
     */
    public void bucheAbgang(int menge) throws MyException {
        String exMsg = null;
        if (menge < 0.0) {
            exMsg += MENGE_MIN_EX;
        }
        if ((bestand - menge) < 0) {
            exMsg += BESTAND_MIN_EX;
        }
        if (exMsg != null) {
            throw new MyException(exMsg);
        }
        bestand -= menge;
    }

    /**
     * toString gibt einen String für die Ausgabe zurück
     *
     * @return
     */
    @Override
    public String toString() {
        DecimalFormat f = new DecimalFormat("#0.00");
        return "\n Artikel: " + artikelNr + " Bezeichnung: " + bezeichnung
                + " Bestand: " + bestand + " Brutto-Preis: " + f.format(preis)
                + " Netto-Preis: " + f.format(preis / MWST) + " MWST-Anteil: "
                + f.format(preis - (preis / MWST));
    }
}
