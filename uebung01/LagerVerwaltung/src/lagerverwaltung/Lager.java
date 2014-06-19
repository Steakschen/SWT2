/*
 * Softwaretechnik 2 
 * Uebung 3
 * @author Carsten Gross / Moritz Fey
 */
package lagerverwaltung;

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
     */
    public Lager(String standort, int maxArtikel) throws MyException {
        String exMsg = null;

        if (standort == null || standort.trim().length() == 0) {
            exMsg += STANDORT_FEHLT_EX;
        }

        /*if (maxArtikel <= MIN_ANZAHL_ARTIKEL) {
         exMsg += ANZAHL_ARTIKEL_EX;
         }*/
        if (exMsg != null) {
            throw new MyException(exMsg);
        }

        this.standort = standort;
        this.maxArtikel = maxArtikel;
        this.artikelAnzahl = 0;
        //this.artikel            = new Artikel[maxArtikel];
        this.artikelListe = new Liste();
    }

    /**
     * Methode zum Anlegen eines neuen Artikels im Lager. Mit Bestand.
     *
     * @param artikelNummer Artikelnummer des Artikels
     * @param bezeichnung	Bezeichnung des Artikels
     * @param bestand	Bestand des Artikels
     * @param preis Preis des Artikels
     *
     */
    public void erstelleArtikel(Artikel artikel) throws MyException {
        /*String exMsg = null;
        
         if (findeArtikel(artikelNummer) >= 0) {
         exMsg += ARTIKEL_BEREITS_VORHANDEN_EX;
         }
        
         if (artikelAnzahl >= (maxArtikel - 1) ) {
         exMsg += LAGER_VOLL_EX;
         }
        
         if (exMsg != null) {
         throw new MyException(exMsg); 
         }
        
         artikel[artikelAnzahl] = new Artikel(artikelNummer, bezeichnung, 
         bestand, preis);
         artikelAnzahl += 1;*/
        artikelListe.add(artikel);
    }

    /**
     * Methode zum Anlegen eines neuen Artikels im Lager. Ohne Bestand.
     *
     * @param artikelNummer Artikelnummer des Artikels
     * @param bezeichnung	Bezeichnung des Artikels
     * @param preis Preis des Artikels
     *
     */
    /*public void erstelleArtikel(int artikelNummer, String bezeichnung, 
     double preis) throws MyException {
     erstelleArtikel(artikelNummer, bezeichnung, 0, preis);
     }/*

     /**
     * Methode zum entfernen eines Artikels.
     *
     * @param artikelName Artikelname des zu entfernenden Artikels
     *
     */
    /*public void entferneArtikel(String artikelName) throws MyException {
     String exMsg = null;
        
     int pos = findeArtikel(artikelName);
     if (pos < 0) {
     exMsg += ARTIKEL_NICHT_VORHANDEN_EX;
     throw new MyException(exMsg);
     }
        
     else {
     artikel[pos] = null;
     for (int i = pos; i < artikelAnzahl; i++) {
     artikel[i] = artikel[i + 1];
     }
     artikelAnzahl -= 1;
     }
     }*/
    /**
     * Methode zum entfernen eines Artikels.
     *
     * @param artikelNummer Artikelnummer des zu entfernenden Artikels
     *
     */
    public void entferneArtikel(int artikelNummer) throws MyException {
        String exMsg = null;
        artikelListe.delete(artikelNummer);
        /*int pos = findeArtikel(artikelNummer);
         if (pos < 0) {
         exMsg += ARTIKEL_NICHT_VORHANDEN_EX;
         throw new MyException(exMsg);
         }
        
         else {
         artikel[pos] = null;
         for (int i = pos; i < artikelAnzahl; i++) {
         artikel[i] = artikel[i + 1];
         }
         artikelAnzahl -= 1;
         }*/
    }

    /**
     * Methode zum Buchen eines Artikelzugangs.
     *
     * @param artikelName Name des Artikels
     * @param menge Menge des Zugangs
     */
    /*public void bucheZugang(String artikelName, int menge) throws MyException {
     String exMsg = null;
        
     int pos = findeArtikel(artikelName);
     if (pos < 0) {
     exMsg += ARTIKEL_NICHT_VORHANDEN_EX;
     throw new MyException(exMsg);
     }
     else {
     artikel[pos].bucheZugang(menge);
     }
     }*/
    /**
     * Methode zum Buchen eines Artikelzugangs.
     *
     * @param artikelNummer Artikelnummer des Artikels
     * @param menge Menge des Zugangs
     */
    public void bucheZugang(int artikelNummer, int menge) throws MyException {
        String exMsg = null;

        artikelListe.getArtikel(artikelNummer).bucheZugang(menge);
        /*int pos = findeArtikel(artikelNummer);
         if (pos < 0) {
         exMsg += ARTIKEL_NICHT_VORHANDEN_EX;
         throw new MyException(exMsg);
         }
         else {
         artikel[pos].bucheZugang(menge);
         }*/
    }

    /**
     * Methode zum Buchen eines Artikelabgangs.
     *
     * @param artikelName Name des Artikels
     * @param menge Menge des Abgangs
     */
    /*public void bucheAbgang(String artikelName, int menge) throws MyException{
     String exMsg = null;
        
     int pos = findeArtikel(artikelName);
     if (pos < 0) {
     exMsg += ARTIKEL_NICHT_VORHANDEN_EX;
     throw new MyException(exMsg);
     }
     else{
     artikel[pos].bucheAbgang(menge);
     }
     }*/
    /**
     * Methode zum Buchen eines Artikelabgangs.
     *
     * @param artikelNummer Artikelnummer des Artikels
     * @param menge Menge des Abgangs
     */
    public void bucheAbgang(int artikelNummer, int menge) throws MyException {
        String exMsg = null;

        artikelListe.getArtikel(artikelNummer).bucheAbgang(menge);
        /*int pos = findeArtikel(artikelNummer);
         if (pos < 0) {
         exMsg += ARTIKEL_NICHT_VORHANDEN_EX;
         throw new MyException(exMsg);
         }
         else{
         artikel[pos].bucheAbgang(menge);
         }*/
    }

    /**
     * Ändern des Preises für alle Artikel.
     *
     * @param prozentSatz Prozentsatz um den der Preis geändert werden soll,
     * positiv oder negativ
     */
    public void aenderePreis(int prozentSatz) throws MyException {
        String exMsg = null;

        if ((prozentSatz < MIN_PROZENT_SATZ)
                || (prozentSatz > MAX_PROZENT_SATZ)) {
            exMsg += PROZENTSATZ_WERTEBEREICH_EX;
            throw new MyException(exMsg);
        }

        for (int i = 0; i < artikelListe.getSize(); i++) {
            artikelListe.getArtikelAtPos(i).setPreis(artikelListe.getArtikelAtPos(i).getPreis()
                    * ((100.0 + (double) prozentSatz) / 100.0));
            System.out.println(artikelListe.getArtikelAtPos(i));
        }
    }

    /**
     * Finde den Array-Index des gesuchten Artikels.
     *
     * @param artikelNummer Artikelnummer des Artikels
     */
    /*private int findeArtikel(int artikelNummer) {
     for (int i = 0; i < artikelAnzahl; i++) {
     if (artikel[i].getArtikelNr() == artikelNummer) {
     return i;
     }
     }
     return -1;
     }*/
    /**
     * Finde den Array-Index des gesuchten Artikels.
     *
     * @param artikelName Artikelname des Artikels
     */
    /*private int findeArtikel(String artikelName) {
     for (int i = 0; i < artikelAnzahl; i++) {
     if (artikel[i].getBezeichnung().equalsIgnoreCase(artikelName)) {
     return i;
     }
     }
     return -1;
     }*/
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
        return (artikelAnzahl == maxArtikel);
    }

    /**
     * Pr&uuml;ft, ob das Lager leer ist.
     *
     * @return true wenn keine Artikel vorhanden, ansonsten false
     */
    public boolean isEmpty() {
        return (artikelListe.getSize() == 0);
    }

    /**
     * Bereitet den Lagerbestand auf und gibt ihn aus
     *
     * @return
     */
    public String ausgebenBestandsListe() throws MyException {
        String lagerString = new String("Lagerort: " + standort + '\n');
        //System.out.println(lagerString);
        
        StringBuilder sbhl = new StringBuilder();
        StringBuilder sb = new StringBuilder();
        Formatter formatterHL = new Formatter(sbhl, Locale.GERMAN);
        Formatter formatter = new Formatter(sb, Locale.GERMAN);

        formatterHL.format("\n%-7s %-20s %-9s %-8s %-9s %-4s", "ArtNr", "Beschreibung", "Netto", "Mwst", "Brutto", "Bestand");
        String striche = "\n------------------------------------------------------------------\n";
        
        DecimalFormat f = new DecimalFormat("#0.00"); 

        Artikel tempArtikel = new Artikel();
        String headline = lagerString + formatterHL.toString() + striche;
        String daten = null;
        
        for (int i = 0; i < artikelListe.getSize(); i++) {
            tempArtikel = artikelListe.getArtikelAtPos(i);
            formatter.format("%-7s %-20s %-9s %-8s %-9s %-4s\n", tempArtikel.getArtikelNr(), 
                    tempArtikel.getBeschreibung(), f.format(tempArtikel.getPreis()/1.19), 
                    f.format(tempArtikel.getPreis()-(tempArtikel.getPreis()/1.19)), 
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
    public String toString() {
        String lagerString = new String("Lager am Standort: " + standort + '\n');

        if (artikelListe.getSize() == 0) {
            lagerString += LAGER_LEER_EX;
        } else {
            lagerString += artikelListe.toString();
        }

        return lagerString;
    }
}
