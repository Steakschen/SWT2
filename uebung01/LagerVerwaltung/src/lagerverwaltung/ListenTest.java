/*
 * Softwaretechnik 2 
 * Uebung 1
 * @author Carsten Gross / Moritz Fey
 */
package lagerverwaltung;

import de.htw.saarland.stl.Stdin;

/**
 *
 * @author Moritz
 */
public class ListenTest {

    private Liste list;
    private int elemente;
    public ListenTest() {
        list = new Liste();
        elemente = 1;
    }

    public static void main(String[] args) {
        ListenTest test = new ListenTest();
        test.run();
    }

    public void addArtikel() throws MyException {
        int artikelNummer           = Stdin.readInt("Artikelnummer: ");
        String bezeichnung          = Stdin.readString("Bezeichnung: ");
        int bestand                 = Stdin.readInt("Bestand: ");
        double preis                = Stdin.readDouble("Preis: ");
        list.add(elemente, new Artikel(artikelNummer, bezeichnung, bestand, preis));
        elemente++;
    }

    public void printListe() {
        System.out.print(list);
    }

    public void printMenu() {
        System.out.println("\n1 - Artikel hinzufügen");
        System.out.println("2 - Print Liste");
    }
    
    public void run() {
        int eingabe = -1;
        try {
            while (eingabe != 0) {
                printMenu();
                eingabe = Stdin.readInt("Auswahl: ");
                switch (eingabe) {
                    case 1:
                        addArtikel();
                        break;
                    case 2:
                        printListe();
                        break;
                }
            }
        } catch (MyException e) {
            e.printStackTrace();
        }

    }
}
