/*
 * Softwaretechnik 2 
 * Uebung 5
 * @author Carsten Gross / Moritz Fey
 */
package lagerverwaltung;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Moritz
 */
public class LagerGuiMain extends JFrame {

    public LagerGuiMain() {
        setTitle("Lagerverwaltung");
        setSize(650, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Label für den Standort anlegen
        JLabel lagerOrt = new JLabel("Lagerort");

        //Textarea für die Ausgabe des Lagers, als Scrollpane
        JTextArea textFeld = new JTextArea("Test");
        JScrollPane scrollPane = new JScrollPane(textFeld);

        //Flowlayout für die unteren Buttons
        JPanel buttonFeld = new JPanel(new FlowLayout());

        //Buttons für das Flowlayout
        JButton neuButton = new JButton("Artikel anlegen");
        JButton loeschenButton = new JButton("Artikel loeschen");
        JButton zugangButton = new JButton("Zugang");
        JButton abgangButton = new JButton("Abgang");
        JButton ausgabeButton = new JButton("Lager ausgeben");

        //Buttons zum Flowlayout hinzufügen
        buttonFeld.add(neuButton);
        buttonFeld.add(loeschenButton);
        buttonFeld.add(zugangButton);
        buttonFeld.add(abgangButton);
        buttonFeld.add(ausgabeButton);

        //Komponenten in den JFrame einfügen        
        add(scrollPane, BorderLayout.CENTER);
        add(lagerOrt, BorderLayout.NORTH);
        add(buttonFeld, BorderLayout.SOUTH);

        //Actionlistener für die Buttons 
        neuButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //neuen Artikel anlegen
                //öfnne Artikel anlegen Fenster
                //int artikelNummer = Integer.parseInt(JOptionPane.showInputDialog(rootPane, "Artikelnummer eingeben", "Eingabe Artikelnummer", WIDTH));
                LagerGuiArtikelAnlegen artikelAnlegen = new LagerGuiArtikelAnlegen();
                artikelAnlegen.getContentPane();

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

        ausgabeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //ausgeben
            }
        });
    }

    public static void main(String[] args) {
        JFrame mainFrame = new LagerGuiMain();
        mainFrame.setVisible(true);
    }

    public class LagerGuiArtikelAnlegen extends JFrame {

        public LagerGuiArtikelAnlegen() throws HeadlessException {
            setTitle("Artikel anlegen");
            setSize(300, 200);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            //Textfeld zum Eingeben
            JTextField textFeld = new JTextField();

            //Flowlayout für die unteren Buttons
            JPanel buttonFeld = new JPanel(new FlowLayout());

            //Buttons für das Flowlayout
            JButton okButton = new JButton("Ok");
            JButton abbrechenButton = new JButton("Abbrechen");

            //Buttons zum Flowlayout hinzufügen
            buttonFeld.add(okButton);
            buttonFeld.add(abbrechenButton);

            //Komponenten in den JFrame einfügen        
            add(textFeld, BorderLayout.CENTER);
            add(buttonFeld, BorderLayout.SOUTH);
        }

    }
}
