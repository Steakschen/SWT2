/*
 * Softwaretechnik 2 
 * Uebung 5
 * @author Carsten Gross / Moritz Fey
 */

package lagerverwaltung;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author Moritz
 */
public class LagerGuiMain extends JFrame {
    public LagerGuiMain() {
        setTitle("Lagerverwaltung");
        setSize(550, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel lagerOrt         = new JLabel("Lagerort");
        JPanel buttonFeld       = new JPanel(new FlowLayout());
        JButton neuButton       = new JButton("neu");
        JButton loeschenButton  = new JButton("loeschen");
        JButton zugangButton    = new JButton("Zugang");
        JButton abgangButton    = new JButton("Abgang");
        JTextArea textFeld      = new JTextArea("Test");
        JScrollPane scrollPane  = new JScrollPane(textFeld);
        add(scrollPane, BorderLayout.CENTER);
        add(lagerOrt, BorderLayout.NORTH);
        buttonFeld.add(neuButton);
        buttonFeld.add(loeschenButton);
        buttonFeld.add(zugangButton);
        buttonFeld.add(abgangButton);
        add(buttonFeld, BorderLayout.SOUTH);
        
        neuButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //neuen Artikel anlegen
            }
        });
        
        loeschenButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //Artikel loeschen
            }
        });
        
        zugangButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //Zugang buchen
            }
        });
        
        abgangButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //Abgang buchen
            }
        });
        
    }
    
    public static void main(String[] args) {
        JFrame mainFrame = new LagerGuiMain();
        mainFrame.setVisible(true);
    }
}
