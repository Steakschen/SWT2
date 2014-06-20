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
        //list = new Liste();
        elemente = 3;
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
        list.add(new Artikel(artikelNummer, bezeichnung, bestand, preis));
        elemente++;
    }

    public void printListe() {
        System.out.print(list);
    }

    public void printMenu() {
        System.out.println("\n1 - Artikel hinzufügen");
        System.out.println("2 - Print Liste");
    }
    
    public void deleteArtikel() throws MyException {
        int artikelNummer = Stdin.readInt("Bitte Artikelnummer angeben: ");
        list.delete(artikelNummer);
    }
    
    public void run() {
        int eingabe = -1;
        try {
            list.add(new Artikel(4711,"Banane",6,6));
            list.add(new Artikel(1337,"Apfel",5,5));
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
                    case 3:
                        deleteArtikel();
                        break;
                }
            }
        } catch (MyException e) {
            e.printStackTrace();
        }

    }
}
