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
        
        //Label für den Standort anlegen
        JLabel lagerOrt         = new JLabel("Lagerort");
        
        //Textarea für die Ausgabe des Lagers, als Scrollpane
        JTextArea textFeld      = new JTextArea("Test");
        JScrollPane scrollPane  = new JScrollPane(textFeld);
        
        //Flowlayout für die unteren Buttons
        JPanel buttonFeld       = new JPanel(new FlowLayout());
        
        //Buttons für das Flowlayout
        JButton neuButton       = new JButton("neu");
        JButton loeschenButton  = new JButton("loeschen");
        JButton zugangButton    = new JButton("Zugang");
        JButton abgangButton    = new JButton("Abgang");

        //Buttons zum Flowlayout hinzufügen
        buttonFeld.add(neuButton);
        buttonFeld.add(loeschenButton);
        buttonFeld.add(zugangButton);
        buttonFeld.add(abgangButton);
        
        //Komponenten in den JFrame einfügen 
        add(scrollPane, BorderLayout.CENTER);
        add(lagerOrt, BorderLayout.NORTH);
        add(buttonFeld, BorderLayout.SOUTH);
        
        //Actionlistener für die Buttons 
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
