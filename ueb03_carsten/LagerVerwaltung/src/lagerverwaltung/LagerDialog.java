/*
 * Softwaretechnik 2 
 * Uebung 1
 * @author Carsten Gross / Moritz Fey
 */
package lagerverwaltung;

import de.htw.saarland.stl.Stdin;

public class LagerDialog {

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
                        System.out.println("Artikelnummer: ");
                        int artikelNr = Stdin.readInt();
                        System.out.println("Artikelname: ");
                        String artikelName = Stdin.readString();
                        System.out.println("Artikelbestand: ");
                        int artikelBestand = Stdin.readInt();
                        System.out.println("Artikelpreis: ");
                        double artikelPreis = Stdin.readDouble();
                        meinLager.erstelleArtikel(artikelNr, artikelName, 
                                artikelBestand, artikelPreis);
                        break;
                    case 2:
                        System.out.println("Bitte Artikelnummer des zu "
                                + "entfernenden Artikels: ");
                        artikelNr = Stdin.readInt();
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
     * @return 
     */
    private int printMenu() {
        System.out.println("\n Menu:\n");
        System.out.println("1 - Artikel anlegen\n");
        System.out.println("2 - Artikel löschen\n");
        System.out.println("3 - Buche Zugang\n");
        System.out.println("4 - Buche Abgang\n");
        System.out.println("5 - aendere Preis\n");
        System.out.println("6 - Lager ausgeben\n");
        return Stdin.readInt();
    }

    /**
     * Weitermachen ?
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
}
