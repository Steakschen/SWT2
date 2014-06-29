/*
 * Softwaretechnik 2 
 * Uebung 3
 * @author Carsten Gross / Moritz Fey
 */
package lagerverwaltung;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DecimalFormat;
import java.util.Formatter;
import java.util.Locale;

/**
 * Fachklasse für die Verwaltung eines Lagers.
 * 
* @author Moritz Fey, Carsten Gross
 * 
*/
public class Lager {

    /* Konstanten fuer Exceptions */
    private static final String STANDORT_FEHLT_EX = "\tStandort ist nicht "
            + "übergeben worden!\n";
    private static final String ANZAHL_ARTIKEL_EX = "\tDie übergebene Anzahl "
            + "Artikel ist kleiner als das Minimum!\n";
    private static final String LAGER_VOLL_EX = "\tDas Lager ist voll!\n";
    private static final String LAGER_LEER_EX = "\tDas Lager ist leer!\n";
    private static final String ARTIKEL_NICHT_VORHANDEN_EX = "\tArtikel nicht "
            + "vorhanden!\n";
    private static final String ARTIKEL_BEREITS_VORHANDEN_EX = "\tArtikel "
            + "bereits vorhanden!\n";
    private static final String PROZENTSATZ_WERTEBEREICH_EX = "\tAngegebener "
            + "Prozentsatz ausserhalb des Wertebereichs\n";

    /* Weitere Konstanten */
    private static final int MIN_ANZAHL_ARTIKEL = 1;
    private static final int MIN_PROZENT_SATZ = -90;
    private static final int MAX_PROZENT_SATZ = 100;

    /**
     * Standort des Lagers.
     */
    private String standort;

    /**
     * Liste die die Artikel beinhaltet.
     */
    private Liste artikelListe;

    /**
     * Maximale Anzahl Artikel im Lager.
     */
    private int maxArtikel;

    /**
     * Zähler für die Anzahl der Artikel im Array.
     */
    private int artikelAnzahl;

    /**
     * Konstruktor der Klasse Lager.
     *
     * @param standort Name des Lagerstandorts
     * @param maxArtikel Maximale Anzahl Artikel die das Lager beinhalten kann
     * @throws lagerverwaltung.LagerException
     */
    public Lager(String standort, int maxArtikel) throws LagerException {
        String exMsg = null;

        if (standort == null || standort.trim().length() == 0) {
            exMsg += STANDORT_FEHLT_EX;
        }
        /*if (maxArtikel <= MIN_ANZAHL_ARTIKEL) {       
         exMsg += ANZAHL_ARTIKEL_EX;
         }*/
        if (exMsg != null) {
            throw new LagerException(exMsg);
        }

        this.standort = standort;
        this.maxArtikel = maxArtikel;
        this.artikelAnzahl = 0;
        this.artikelListe = new Liste();
    }

    /**
     * Methode zum Anlegen eines neuen Artikels im Lager
     *
     * @param artikel Artikel der im Lager abgelegt wird
     * @throws lagerverwaltung.ArtikelException
     *
     */
    public void erstelleArtikel(Artikel artikel) throws ArtikelException {
        String exMsg = null;

        if (artikelListe.contains(artikel)) {
            exMsg += ARTIKEL_BEREITS_VORHANDEN_EX;
        }

        if (isFull()) {
            exMsg += LAGER_VOLL_EX;
        }

        if (exMsg != null) {
            throw new ArtikelException(exMsg);
        }
        artikelAnzahl += 1;
        artikelListe.add(artikel);
    }

    /**
     * Methode zum entfernen eines Artikels.
     *
     * @param artikelNummer Artikelnummer des zu entfernenden Artikels
     * @throws lagerverwaltung.ArtikelException
     *
     */
    public void entferneArtikel(int artikelNummer) throws ArtikelException {
        String exMsg = null;
        //if (artikelListe.getArtikel(artikelNummer) == null) {
        if (!artikelListe.contains(artikelListe.getArtikel(artikelNummer))) {
            exMsg += ARTIKEL_NICHT_VORHANDEN_EX;
            throw new ArtikelException(exMsg);
        } else {
            artikelAnzahl -= 1;
            artikelListe.delete(artikelNummer);
        }
    }

    public void bucheZugang(int artikelNummer, int menge) throws ArtikelException {
        String exMsg = null;
        if (!artikelListe.contains(artikelListe.getArtikel(artikelNummer))) {
            exMsg += ARTIKEL_NICHT_VORHANDEN_EX;
            throw new ArtikelException(exMsg);
        } else {
            artikelListe.getArtikel(artikelNummer).bucheZugang(menge);
        }
    }

    public void bucheAbgang(int artikelNummer, int menge) throws ArtikelException {
        String exMsg = null;

        if (!artikelListe.contains(artikelListe.getArtikel(artikelNummer))) {
            exMsg += ARTIKEL_NICHT_VORHANDEN_EX;
            throw new ArtikelException(exMsg);
        } else {
            artikelListe.getArtikel(artikelNummer).bucheAbgang(menge);
        }
    }

    /**
     * Ändern des Preises für alle Artikel.
     *
     * @param prozentSatz Prozentsatz um den der Preis geändert werden soll,
     * positiv oder negativ
     * @throws lagerverwaltung.ArtikelException
     */
    public void aenderePreis(int prozentSatz) throws ArtikelException {
        String exMsg = null;

        if ((prozentSatz < MIN_PROZENT_SATZ)
                || (prozentSatz > MAX_PROZENT_SATZ)) {
            exMsg += PROZENTSATZ_WERTEBEREICH_EX;
            throw new ArtikelException(exMsg);
        }

        for (int i = 0; i < artikelListe.getSize(); i++) {
            artikelListe.getArtikelAtPos(i).setPreis(artikelListe.getArtikelAtPos(i).getPreis()
                    * ((100.0 + (double) prozentSatz) / 100.0));
            System.out.println(artikelListe.getArtikelAtPos(i));
        }
    }

    /**
     * Gib die Anzahl der Artikel im Lager zurück.
     *
     * @return Anzahl der Artikel im Lager
     */
    public int getArtikelAnzahl() {
        return this.artikelAnzahl;
    }

    /**
     * Pr&uuml;ft, ob das Lager voll ist.
     *
     * @return true wenn die maximale Anzahl Artikel erreicht ist, ansonsten
     * false
     */
    public boolean isFull() {
        return (artikelListe.getSize() == maxArtikel);
    }

    /**
     * Pr&uuml;ft, ob das Lager leer ist.
     *
     * @return true wenn keine Artikel vorhanden, ansonsten false
     */
    public boolean isEmpty() {
        return (artikelListe.getSize() == 0);
    }

    //TODO: Exceptions überarbeiten, testen, zur Not Liste neu schreiben
    /**
     * Funktion zum Laden des Lagers aus einer Datei.
     *
     * @param dateiName Name der Datei in der das Lager gespeichert wurde
     * @throws lagerverwaltung.DateiException
     * @throws java.lang.ClassNotFoundException
     */
    public void laden(String dateiName) throws DateiException, ClassNotFoundException {
        File inputDatei = new File(dateiName);

        try (ObjectInputStream inputStream = 
                new ObjectInputStream(
                    new BufferedInputStream(
                            new FileInputStream(inputDatei)))){

            artikelListe = (Liste) inputStream.readObject();
            
        } catch (IOException e) {
            throw new DateiException("Bla bla laden" + e);
        }

    }

    //TODO: Exceptions überarbeiten, testen, zur Not Liste neu schreiben
    /**
     * Funktion zum Speichern des Lagers in einer Datei.
     *
     * @param dateiName Name der Datei in der das Lager gespeichert wird
     * @throws lagerverwaltung.DateiException
     * @throws java.lang.ClassNotFoundException
     */
    public void speichern(String dateiName) throws DateiException, ClassNotFoundException {
        File outputDatei = new File(dateiName);

        try (ObjectOutputStream outputStream = 
                new ObjectOutputStream(
                    new BufferedOutputStream(
                            new FileOutputStream(outputDatei)))){

            outputStream.writeObject(artikelListe);

        } catch (IOException e) {
            throw new DateiException("Bla Bla speichern" + e);
        }
    }

    /**
     * Bereitet den Lagerbestand auf und gibt ihn aus
     *
     * @return
     * @throws lagerverwaltung.ArtikelException
     */
    public String ausgebenBestandsListe() throws ArtikelException {
        String lagerString = "Lagerort: " + standort + '\n';

        StringBuilder sbhl = new StringBuilder();
        StringBuilder sb = new StringBuilder();
        Formatter formatterHL = new Formatter(sbhl, Locale.GERMAN);
        Formatter formatter = new Formatter(sb, Locale.GERMAN);

        formatterHL.format("\n%-7s %-50s %-9s %-8s %-9s %-4s", "ArtNr",
                "Beschreibung", "Netto", "Mwst", "Brutto", "Bestand");
        String striche = "\n---------------------------------------------"
                + "---------------------------------------------------\n";

        DecimalFormat f = new DecimalFormat("#0.00");

        Artikel tempArtikel;
        String headline = lagerString + formatterHL.toString() + striche;
        String daten = null;

        for (int i = 0; i < artikelListe.getSize(); i++) {
            tempArtikel = artikelListe.getArtikelAtPos(i);

            formatter.format("%-7s %-50s %-9s %-8s %-9s %-4s\n",
                    tempArtikel.getArtikelNr(), tempArtikel.getBeschreibung(),
                    f.format(tempArtikel.getNettoPreis()),
                    f.format(tempArtikel.getMwstAnteil()),
                    f.format(tempArtikel.getPreis()), tempArtikel.getBestand());

            daten = formatter.toString();
        }

        return headline + daten;
    }

    /**
     * toString zur Ausgabe des kompletten Lagers.
     *
     * @return Lager als String
     */
    @Override
    public String toString() {
        String lagerString = "Lager am Standort: " + standort + '\n';

        if (artikelListe.getSize() == 0) {
            lagerString += LAGER_LEER_EX;
        } else {
            lagerString += artikelListe.toString();
        }

        return lagerString;
    }

    public boolean dateiVorhanden(String dateiName) {
        File datei = new File(dateiName);
        if (datei.exists()) {
            return true;
        } else {
            return false;
        }
    }
}
