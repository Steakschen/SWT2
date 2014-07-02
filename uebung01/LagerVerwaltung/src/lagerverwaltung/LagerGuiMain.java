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
        //JLabel lagerOrt = new JLabel("Lagerort: " + meinLager.getStandort());
        
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
                LagerGuiArtikelZuBuchen zuBuchen = new LagerGuiArtikelZuBuchen();
                zuBuchen.setVisible(true);
            }
        });

        abgangButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //Abgang buchen
                LagerGuiArtikelAbBuchen abBuchen = new LagerGuiArtikelAbBuchen();
                abBuchen.setVisible(true);
            }
        });

        ausgabeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //ausgeben
                textFeld.setText(meinLager.toString());
            }
        });
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
                    Artikel artikel = null;
                    try {
                        artikel = new Artikel(Integer.parseInt(artikelNummerFeld.getText()), artikelBezeichnungFeld.getText(), Integer.parseInt(artikelBestandFeld.getText()), Double.parseDouble(artikelPreisFeld.getText()));
                    } catch (ArtikelException ex) {
                        Logger.getLogger(LagerGuiMain.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        meinLager.erstelleArtikel(artikel);
                    } catch (ArtikelException ex) {
                        Logger.getLogger(LagerGuiMain.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    setVisible(false);
                }
            });

            abbrechenButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    //beenden nicht anlegen ins menu
                    setVisible(false);
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
                    try {
                        //hinzufuegen
                        meinLager.entferneArtikel(Integer.parseInt(artikelNummerFeld.getText()));
                    } catch (ArtikelException ex) {
                        Logger.getLogger(LagerGuiMain.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    setVisible(false);
                }
            });

            abbrechenButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    //beenden nicht anlegen ins menu
                    setVisible(false);
                }
            });
        }
    }

    //static weil aus Main aufrufen
    private static class LagerGuiLagerAnlegen extends JDialog {

        public LagerGuiLagerAnlegen() {

            setTitle("Lager anlegen");
            setSize(300, 200);
            //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            setLayout(new GridLayout(0, 2));
            
            setModal(true);
            
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
                        System.out.println("Fehler beim Lageranlegen: " + ex);
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

    private class LagerGuiArtikelZuBuchen extends JFrame {

        public LagerGuiArtikelZuBuchen() {

            setTitle("Artikel zubuchen");
            setSize(300, 200);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            setLayout(new GridLayout(0, 2));

            //Artikelnummer
            JLabel artikelNummerLabel = new JLabel("Artikelnummer");
            JTextField artikelNummerFeld = new JTextField();

            //Anzahl
            JLabel anzahlLabel = new JLabel("Anzahl");
            JTextField anzahlFeld = new JTextField();

            //Buttons für das Flowlayout
            JButton okButton = new JButton("Ok");
            JButton abbrechenButton = new JButton("Abbrechen");

            //Komponenten in den JFrame einfügen        
            add(artikelNummerLabel);
            add(artikelNummerFeld);
            add(anzahlLabel);
            add(anzahlFeld);
            add(okButton);
            add(abbrechenButton);

            okButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        //hinzufuegen
                        meinLager.bucheZugang(Integer.parseInt(artikelNummerFeld.getText()), Integer.parseInt(anzahlFeld.getText()));
                    } catch (ArtikelException ex) {
                        Logger.getLogger(LagerGuiMain.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    setVisible(false);
                }
            });

            abbrechenButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    //beenden nicht anlegen ins menu
                    setVisible(false);
                }
            });
        }
    }

    private class LagerGuiArtikelAbBuchen extends JFrame {

        public LagerGuiArtikelAbBuchen() {

            setTitle("Artikel abbuchen");
            setSize(300, 200);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            setLayout(new GridLayout(0, 2));

            //Artikelnummer
            JLabel artikelNummerLabel = new JLabel("Artikelnummer");
            JTextField artikelNummerFeld = new JTextField();
            
            //Anzahl
            JLabel anzahlLabel = new JLabel("Anzahl");
            JTextField anzahlFeld = new JTextField();


            //Buttons für das Flowlayout
            JButton okButton = new JButton("Ok");
            JButton abbrechenButton = new JButton("Abbrechen");

            //Komponenten in den JFrame einfügen        
            add(artikelNummerLabel);
            add(artikelNummerFeld);
            add(anzahlLabel);
            add(anzahlFeld);
            add(okButton);
            add(abbrechenButton);

            okButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        //hinzufuegen
                       meinLager.bucheAbgang(Integer.parseInt(artikelNummerFeld.getText()), Integer.parseInt(anzahlFeld.getText()));
                    } catch (ArtikelException ex) {
                        Logger.getLogger(LagerGuiMain.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    setVisible(false);
                }
            });

            abbrechenButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    //beenden nicht anlegen ins menu
                    setVisible(false);
                }
            });
        }
    }

    
    public static void main(String[] args) {
        LagerGuiLagerAnlegen lagerAnlegen = new LagerGuiLagerAnlegen();
        lagerAnlegen.setVisible(true);

        JFrame mainFrame = new LagerGuiMain();
        System.out.println("penis");
        mainFrame.setVisible(true);
    }
    
}// Ende LagerGuiMain
