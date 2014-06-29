/*
 * Softwaretechnik 2 
 * Uebung 3
 * @author Carsten Gross / Moritz Fey
 */
package lagerverwaltung;

import de.htw.saarland.stl.Stdin;

public class LagerDialog {

    /* Konstanten */
    private static final int ARTIKEL = 1;
    private static final int BUCH = 2;
    private static final int DVD = 3;
    private static final int CD = 4;

    private Lager meinLager;
    
    private String dateiname;

    /**
     * Konstruktor
     */
    private LagerDialog() {
    }

    /**
     * Main-Methode Startet die Dialogklasse
     *
     * @param args
     * @throws lagerverwaltung.ArtikelException
     * @throws lagerverwaltung.LagerException
     * @throws lagerverwaltung.DateiException
     * @throws java.lang.ClassNotFoundException
     */
    public static void main(String[] args) throws ArtikelException, LagerException, DateiException, ClassNotFoundException {
        LagerDialog lagerDialog = new LagerDialog();
        lagerDialog.start();
    }

    /**
     * Die IO / Bearbeitungs-Methode
     *
     * @throws MyException
     */
    private void start() throws LagerException, ArtikelException, DateiException, ClassNotFoundException {
        
        char lagerVorhanden = Stdin.readChar("\n Lager bereits vorhanden und laden? (j/n)");
        if (lagerVorhanden == 'j') {
            meinLager = new Lager();
            dateiLaden();
        }
        else {
            System.out.println("\nLager anlegen");
            System.out.println("Lagername: ");
            String lagerName = Stdin.readString();
            System.out.println("Lagerplaetze: ");
            int maxLagerPlaetze = Stdin.readInt();
            meinLager = new Lager(lagerName, maxLagerPlaetze); 
        }

        int menu = 0;
        do {
            menu = printMenu();
            try {
                switch (menu) {
                    case 1:
                        artikelAnlegen();
                        break;
                    case 2:
                        System.out.println("Bitte Artikelnummer des zu "
                                + "entfernenden Artikels: ");
                        int artikelNr = Stdin.readInt();
                        meinLager.entferneArtikel(artikelNr);
                        break;
                    case 3:
                        System.out.println("Artikelnummer des Artikels: ");
                        artikelNr = Stdin.readInt();
                        System.out.println("Bitte Anzahl angeben: ");
                        int menge = Stdin.readInt();
                        meinLager.bucheZugang(artikelNr, menge);
                        break;
                    case 4:
                        System.out.println("Artikelnummer des Artikels: ");
                        artikelNr = Stdin.readInt();
                        System.out.println("Bitte Anzahl angeben: ");
                        menge = Stdin.readInt();
                        meinLager.bucheAbgang(artikelNr, menge);
                        break;
                    case 5:
                        System.out.println("Bitte Prozentsatz angeben: ");
                        int preisPro = Stdin.readInt();
                        meinLager.aenderePreis(preisPro);
                        break;
                    case 6:
                        System.out.println(meinLager.toString());
                        break;
                    case 7:
                        System.out.println(meinLager.ausgebenBestandsListe());
                        break;
                    case 8:
                        dateiLaden();
                        System.out.println("Laden von: "+dateiname + " erfolgreich!");
                        break;
                    case 9:
                        dateiSpeichern();
                        System.out.println("Speichern von: "+dateiname+ " erfolgreich!");
                        break;
                    case 10:
                        System.out.println("\nDas Programm Beendet sich, Vielen Dank\n");
                        break;
                    default:
                        System.out.println("Default got hit O_o");
                        break;
                }
            } catch (ArtikelException e) {
                System.out.println(e);
            }

        } while (menu != 10);
    }

    /**
     * Menu ausgeben
     *
     * @return
     */
    private int printMenu() {
        System.out.println("\n Menu:");
        System.out.println(" 1 - Artikel anlegen");
        System.out.println(" 2 - Artikel löschen");
        System.out.println(" 3 - Buche Zugang");
        System.out.println(" 4 - Buche Abgang");
        System.out.println(" 5 - aendere Preis");
        System.out.println(" 6 - Lager ausgeben");
        System.out.println(" 7 - Bestandsliste ausgeben");
        System.out.println(" 8 - Laden");
        System.out.println(" 9 - Speichern");
        System.out.println(" 10 - Beenden\n");
        return Stdin.readInt();
    }

    /**
     * Aritikel Menu ausgeben
     *
     * @return
     */
    private int printArtikelMenu() {
        System.out.println("\n Was möchten sie Anlegen:");
        System.out.println(" 1 - Artikel anlegen");
        System.out.println(" 2 - Buch anlegen");
        System.out.println(" 3 - DVD  anlegen");
        System.out.println(" 4 - CD anlegen\n");
        return Stdin.readInt();
    }

    /**
     * Legt die Artikel an
     *
     * @throws ArtikelException
     */
    private void artikelAnlegen() throws ArtikelException {
        int artikelMenu = printArtikelMenu();
        switch (artikelMenu) {
            case ARTIKEL:
                Artikel artikel = new Artikel(artikelNrAnlegen(),
                        artikelNameAnlegen(), artikelBestandAnlegen(),
                        artikelPreisAnlegen());
                meinLager.erstelleArtikel(artikel);
                break;
            case BUCH:
                System.out.println("Buch Titel: ");
                String titel = Stdin.readlnString();
                System.out.println("Buch Autor: ");
                String autor = Stdin.readlnString();
                System.out.println("Buch Verlag: ");
                String verlag = Stdin.readlnString();
                Buch buch = new Buch(artikelNrAnlegen(), artikelNameAnlegen(),
                        artikelBestandAnlegen(), artikelPreisAnlegen(),
                        titel, autor, verlag);
                meinLager.erstelleArtikel(buch);
                break;
            case DVD:
                System.out.println("DVD Titel: ");
                titel = Stdin.readlnString();
                System.out.println("DVD Spieldauer: ");
                float spieldauer = (float) Stdin.readDouble(); //readFloat geht net
                System.out.println("DVD Erscheinungsjahr: ");
                int erscheinungsjahr = Stdin.readInt();
                DVD dvd = new DVD(artikelNrAnlegen(), artikelNameAnlegen(),
                        artikelBestandAnlegen(), artikelPreisAnlegen(), titel,
                        spieldauer, erscheinungsjahr);
                meinLager.erstelleArtikel(dvd);
                break;
            case CD:
                System.out.println("CD Interpret: ");
                titel = Stdin.readlnString();
                System.out.println("CD Titel: ");
                String interpret = Stdin.readlnString();
                System.out.println("CD Anzahl Musiktitel: ");
                int anzahl = Stdin.readInt();
                CD cd = new CD(artikelNrAnlegen(), artikelNameAnlegen(),
                        artikelBestandAnlegen(), artikelPreisAnlegen(), titel,
                        interpret, anzahl);
                meinLager.erstelleArtikel(cd);
                break;
        }
    }

    /**
     * Artikelnummer einlesen
     *
     * @return
     */
    private int artikelNrAnlegen() {
        System.out.println("Artikelnummer: ");
        return Stdin.readInt();
    }

    /**
     * Artikelname anlegen/einlesen
     *
     * @return
     */
    private String artikelNameAnlegen() {
        System.out.println("Artikelname: ");
        return Stdin.readString();
    }

    /**
     * Bestand anlegen/einlesen
     *
     * @return
     */
    private int artikelBestandAnlegen() {
        System.out.println("Artikelbestand: ");
        return Stdin.readInt();
    }

    /**
     * Preis anlegen/einlesen
     *
     * @return
     */
    private double artikelPreisAnlegen() {
        System.out.println("Artikelpreis: ");
        return Stdin.readDouble();
    }

    /**
     * Datei laden
     */
    private void dateiLaden() throws DateiException, ClassNotFoundException {
        meinLager.laden(dateiNameEinlesen());
        //Prüfen ob einlesen Sinn macht??        
    }

    /**
     * Datei Speichern
     */
    private void dateiSpeichern() throws DateiException, ClassNotFoundException {
        dateiname = dateiNameEinlesen();
                
        if (meinLager.dateiVorhanden(dateiname)){
            System.out.println("Datei: "+ dateiname+" vorhanden, überschreiben ? j/n");
            if (Stdin.readChar() == 'j') {
                meinLager.speichern(dateiname);
            }
        } else {
            meinLager.speichern(dateiname);
        }
    }
    
    /**
     * Liest den Dateinamen ein
     * @return dateiname
     */
    private String dateiNameEinlesen() {
        System.out.println("Bitte Dateinamen zum speichern/laden eingeben: ");
        return dateiname = Stdin.readlnString();
    }
}
