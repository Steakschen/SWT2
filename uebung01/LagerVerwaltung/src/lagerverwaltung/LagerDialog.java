/*
 * Softwaretechnik 2 
 * Uebung 1
 * @author Carsten Gross / Moritz Fey
 */

package lagerverwaltung;
import de.htw.saarland.stl.Stdin;
import java.text.DecimalFormat;

/**
 *
 * @author Carsten
 */
public class LagerDialog {
    
    public static void main(String[] args) {
        Lager meinLager = new Lager("Saarbruecken", 10);
        meinLager.erstelleArtikel(4711, "Parfum", 5, 49.99);
        meinLager.erstelleArtikel(1254, "Klebeband", 50, 2.50);
        System.out.println(meinLager.toString());
        meinLager.bucheZugang(4711, 20);
        meinLager.erstelleArtikel(5846, "Toaster", 2, 15.99);
        meinLager.entferneArtikel(1254);
        meinLager.aenderePreis(20);
        System.out.println(meinLager.toString());        
    }
    
}
