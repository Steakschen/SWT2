/*
 * Softwaretechnik 2 
 * Uebung 1
 * @author Carsten Gross / Moritz Fey
 */

package lagerverwaltung;
import de.htw.saarland.stl.Stdin;

/**
 *
 * @author Carsten
 */
public class LagerDialog {
    
    Lager[] lagerArray;
    int maxLager;
    int lagerAnzahl;

    public LagerDialog(Lager[] lagerArray, int maxLager) {  
        this.maxLager = maxLager;
        this.lagerAnzahl = 0;
        this.lagerArray = new Lager[maxLager];
    }
     
    private LagerDialog() {
    }
    
    
    public static void main(String[] args) {
        Lager meinLager = new Lager("Saarbruecken", 5);
        meinLager.erstelleArtikel(4711, "Kebab", 5, 49.99);
        meinLager.erstelleArtikel(1254, "Klebeband", 50, 2.50);
        System.out.println(meinLager.toString());
        meinLager.bucheZugang(4711, 20);
        meinLager.erstelleArtikel(5846, "Toaster", 2, 15.99);
        meinLager.entferneArtikel(1254);
        meinLager.aenderePreis(20);
        System.out.println(meinLager.toString());     
        
        LagerDialog lagerDialog = new LagerDialog();
        lagerDialog.start();   
    }
    
    private void start() {
        while (weitermachen()){
            int menu = printMenu();
            switch(menu){
                case 1:
                    assert lagerAnzahl < (maxLager -1) : "Maximale Anzahl Lager erreicht!";
                    System.out.println("Lagername: ");
                    String lagerName = Stdin.readString();
                    System.out.println("Lageranzahl: ");
                    int maxLagerAnzahl = Stdin.readInt();
                    lagerArray[lagerAnzahl] = new Lager(lagerName, maxLagerAnzahl);
                    lagerAnzahl += 1;
                    break;
                case 2:
                    int artikelNr = Stdin.readInt();
                    String artikelName = Stdin.readString();
                    int artikelBestand = Stdin.readInt();
                    double artikelPreis = Stdin.readDouble();
                    lagerArray[0].erstelleArtikel(artikelNr, artikelName, artikelBestand, artikelPreis);
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                default:
                    System.out.println("Default got hit O_o");
                    break;       
            }
        }
    }
    
    private int printMenu() {
        System.out.println("\n Menu:\n");
        System.out.println("1 - Lager anlegen \n");
        System.out.println("2 - Artikel anlegen\n");
        System.out.println("3 - Artikel löschen\n");
        System.out.println("4 - Buche Zugang\n");
        System.out.println("5 - Buche Abgang\n");
        System.out.println("6 - aendere Preis\n");
        return Stdin.readInt();
    }
    
    public boolean weitermachen() {
        char antwort = ' ';
        System.out.println("Weitermachen?");
        while ((antwort != 'j') && (antwort != 'n')) {
            antwort = Stdin.readChar();
        }    
        return antwort == 'j';
    }
}
