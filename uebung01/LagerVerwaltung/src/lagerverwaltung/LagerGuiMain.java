/*
 * Softwaretechnik 2 
 * Uebung 5
 * @author Carsten Gross / Moritz Fey
 */

package lagerverwaltung;

import javax.swing.JFrame;

/**
 *
 * @author Moritz
 */
public class LagerGuiMain extends JFrame {
    public LagerGuiMain() {
        setTitle("Lagerverwaltung");
        setSize(550, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public static void main(String[] args) {
        JFrame mainFrame = new LagerGuiMain();
        mainFrame.setVisible(true);
    }
}
