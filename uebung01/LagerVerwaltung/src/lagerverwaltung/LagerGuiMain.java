/*
 * Softwaretechnik 2 
 * Uebung 5
 * @author Carsten Gross / Moritz Fey
 */
package lagerverwaltung;

import java.awt.BorderLayout;
import java.awt.Event;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author Moritz
 */
public class LagerGuiMain extends JFrame {
    
    public static Lager meinLager;

    public LagerGuiMain() {
        setTitle("Lagerverwaltung");
        setSize(650, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Label für den Standort anlegen
        JLabel lagerOrt = new JLabel("Lagerort: " + meinLager.getStandort());

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
                LagerGuiArtikelAnlegen artikelAnlegen = new LagerGuiArtikelAnlegen(meinLager);
                artikelAnlegen.setVisible(true);

            }
        });

        loeschenButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //Artikel loeschen
                LagerGuiArtikelLoeschen artikelLoeschen = new LagerGuiArtikelLoeschen();
                artikelLoeschen.setVisible(true);
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
        LagerGuiLagerAnlegen lagerAnlegen = new LagerGuiLagerAnlegen();
        lagerAnlegen.setVisible(true);
        
        while (lagerAnlegen.isActive()){
            
        }
        
        JFrame mainFrame = new LagerGuiMain();
        System.out.println("penis");
        mainFrame.setVisible(true);
    }

    public class LagerGuiArtikelAnlegen extends JFrame {
        
        public LagerGuiArtikelAnlegen(Lager _meinLager) throws HeadlessException {
            meinLager = _meinLager;
            setTitle("Artikel anlegen");
            setSize(300, 200);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            setLayout(new GridLayout(0, 2));

            //Artikelnummer
            JLabel artikelNummerLabel = new JLabel("Artikelnummer");
            JTextField artikelNummerFeld = new JTextField();

            //Artikelbezeichnung
            JLabel artikelBezeichnungLabel = new JLabel("Artikelbezeichnung");
            JTextField artikelBezeichnungFeld = new JTextField();

            //Artikelbestand
            JLabel artikelBestandLabel = new JLabel("Artikelbestand");
            JTextField artikelBestandFeld = new JTextField();

            //Artikelpreis
            JLabel artikelPreisLabel = new JLabel("Preis");
            JTextField artikelPreisFeld = new JTextField();

            //Buttons für das Flowlayout
            JButton okButton = new JButton("Ok");
            JButton abbrechenButton = new JButton("Abbrechen");

            //Komponenten in den JFrame einfügen        
            add(artikelNummerLabel);
            add(artikelNummerFeld);
            add(artikelBezeichnungLabel);
            add(artikelBezeichnungFeld);
            add(artikelBestandLabel);
            add(artikelBestandFeld);
            add(artikelPreisLabel);
            add(artikelPreisFeld);
            add(okButton);
            add(abbrechenButton);

            okButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    //hinzufuegen
                }
            });

            abbrechenButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    //beenden nicht anlegen ins menu
                }
            });
        }
    }

    private class LagerGuiArtikelLoeschen extends JFrame {

        public LagerGuiArtikelLoeschen() {

            setTitle("Artikel löschen");
            setSize(300, 200);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            setLayout(new GridLayout(0, 2));

            //Artikelnummer
            JLabel artikelNummerLabel = new JLabel("Artikelnummer");
            JTextField artikelNummerFeld = new JTextField();

            //Buttons für das Flowlayout
            JButton okButton = new JButton("Ok");
            JButton abbrechenButton = new JButton("Abbrechen");

            //Komponenten in den JFrame einfügen        
            add(artikelNummerLabel);
            add(artikelNummerFeld);
            add(okButton);
            add(abbrechenButton);

            okButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    //hinzufuegen
                }
            });

            abbrechenButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    //beenden nicht anlegen ins menu
                }
            });
        }
    }

    private static class LagerGuiLagerAnlegen extends JFrame {

        public LagerGuiLagerAnlegen() {

            setTitle("Artikel löschen");
            setSize(300, 200);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            setLayout(new GridLayout(0, 2));

            //Artikelnummer
            JLabel lagerNameLabel = new JLabel("Lagername");
            JTextField lagerNameFeld = new JTextField();

            //Anzahl Plaetze
            JLabel lagerPlaetzeLabel = new JLabel("Lagerplaetze");
            JTextField lagerPlaetzeFeld = new JTextField();

            //Buttons für das Flowlayout
            JButton okButton = new JButton("Ok");
            JButton abbrechenButton = new JButton("Abbrechen");

            //Komponenten in den JFrame einfügen        
            add(lagerNameLabel);
            add(lagerNameFeld);
            add(lagerPlaetzeLabel);
            add(lagerPlaetzeFeld);
            add(okButton);
            add(abbrechenButton);

            okButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    //hinzufuegen
                    String standort = lagerNameFeld.getText();
                    int plaetze = Integer.parseInt(lagerPlaetzeFeld.getText());
                    try {
                        meinLager = new Lager(standort, plaetze);
                    } catch (LagerException ex) {
                        System.out.println("Fehler beim Lageranlegen: " +ex);
                    }
                    //TODO 
                    //wie komm ich von hier ins Menu?
                    setVisible(false);
                }
            });

            abbrechenButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    //beenden nicht anlegen abbrechen
                    System.exit(0);
                }
            });
        }

    }

}// Ende LagerGuiMain
