/*
 * Softwaretechnik 2 
 * Uebung 1
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

    /**
     * Konstruktor
     */
    private LagerDialog() {
    }

    /**
     * Main-Methode Startet die Dialogklasse
     *
     * @param args
     * @throws MyException
     */
    public static void main(String[] args) throws MyException {
        LagerDialog lagerDialog = new LagerDialog();
        lagerDialog.start();
    }

    /**
     * Die IO / Bearbeitungs-Methode
     *
     * @throws MyException
     */
    private void start() throws MyException {

        System.out.println("1 - Lager anlegen \n");
        System.out.println("Lagername: ");
        String lagerName = Stdin.readString();
        System.out.println("Lagerplaetze: ");
        int maxLagerPlaetze = Stdin.readInt();
        meinLager = new Lager(lagerName, maxLagerPlaetze);

        do {
            int menu = printMenu();
            try {
                switch (menu) {
                    case 1:
                        int artikelMenu = printArtikelMenu();
                        if (artikelMenu == ARTIKEL) {
                            Artikel artikel = artikelAnlegen();
                            meinLager.erstelleArtikel(artikel);
                        }else if (artikelMenu == BUCH) {
                            Buch buch = buchAnlegen();
                            meinLager.erstelleArtikel(buch);
                        }else if (artikelMenu == DVD) {
                            DVD dvd = dvdAnlegen();
                            meinLager.erstelleArtikel(dvd);
                        }else if (artikelMenu == CD) {
                            CD cd = cdAnlegen();
                            meinLager.erstelleArtikel(cd);
                        }else {
                            artikelAnlegen();
                        }
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
                    default:
                        System.out.println("Default got hit O_o");
                        break;
                }
            } catch (MyException e) {
                System.out.println(e);
            }

        } while (weitermachen());
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
        System.out.println(" 6 - Lager ausgeben\n");
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
     * Weitermachen ?
     *
     * @return
     */
    public boolean weitermachen() {
        char antwort = ' ';
        System.out.println("\n Weitermachen?");
        while ((antwort != 'j') && (antwort != 'n')) {
            antwort = Stdin.readChar();
        }
        return antwort == 'j';
    }

    private Artikel artikelAnlegen() throws MyException {
        System.out.println("Artikelnummer: ");
        int artikelNr = Stdin.readInt();
        System.out.println("Artikelname: ");
        String artikelName = Stdin.readString();
        System.out.println("Artikelbestand: ");
        int artikelBestand = Stdin.readInt();
        System.out.println("Artikelpreis: ");
        double artikelPreis = Stdin.readDouble();
        Artikel artikel 
            = new Artikel (artikelNr,artikelName,artikelBestand,artikelPreis);
        return artikel;
    }
    
    private Buch buchAnlegen() throws MyException {
        Artikel artikel = artikelAnlegen();
        System.out.println("Buch Titel: ");
        String titel = Stdin.readString();
        System.out.println("Buch Autor: ");
        String autor = Stdin.readString();
        System.out.println("Buch Verlag: ");
        String verlag = Stdin.readString();
        Buch buch = new Buch(artikel.getArtikelNr(), artikel.getBezeichnung(), 
                artikel.getBestand(), artikel.getPreis(),titel, autor, verlag);
        return buch;
    }

    private DVD dvdAnlegen() throws MyException {
        Artikel artikel = artikelAnlegen();
        System.out.println("DVD Titel: ");
        String titel = Stdin.readString();
        System.out.println("DVD Spieldauer: ");
        float spieldauer = (float) Stdin.readDouble(); //readFloat geht net
        System.out.println("DVD Erscheinungsjahr: ");
        int erscheinungsjahr = Stdin.readInt();
        DVD dvd = new DVD(artikel.getArtikelNr(), artikel.getBezeichnung(), 
                artikel.getBestand(), artikel.getPreis(),titel, spieldauer, erscheinungsjahr);
        return dvd;
    }

    private CD cdAnlegen() throws MyException {
        Artikel artikel = artikelAnlegen();
        System.out.println("CD Interpret: ");
        String titel = Stdin.readString();
        System.out.println("CD Titel: ");
        String interpret = Stdin.readString();
        System.out.println("CD Anzahl Musiktitel: ");
        int anzahl = Stdin.readInt();
        CD cd = new CD(artikel.getArtikelNr(), artikel.getBezeichnung(), 
                artikel.getBestand(), artikel.getPreis(), titel, interpret, anzahl);
        return cd;
    }
}
