/*
 * Softwaretechnik 2 
 * Uebung 5
 * @author Carsten Gross / Moritz Fey
 */
package lagerverwaltung;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * LagerGuiMain bietet einen Benutzerdialog für die Lager/Artikel Fachklassen
 * 
 * @author Moritz / Carsten
 */
public class LagerGuiMain extends JFrame {

    public static Lager meinLager;
    public static String lagerName;

    public LagerGuiMain() {
        setTitle("Lagerverwaltung");
        setSize(790, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
            
        //Textarea für die Ausgabe des Lagers, als Scrollpane
        JTextArea textFeld = new JTextArea();
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
        add(buttonFeld, BorderLayout.SOUTH);

        /**
         * Artikel Anlegen Button Listener - öffnet das Artikelanlegen Menu
         */
        neuButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LagerGuiArtikelAnlegen artikelAnlegen = new LagerGuiArtikelAnlegen(meinLager);
                artikelAnlegen.setVisible(true);
            }
        });

        /**
         * Artikel Löschen Button Listener - öffnet den Artikellöschen Dialog
         */
        loeschenButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LagerGuiArtikelLoeschen artikelLoeschen = new LagerGuiArtikelLoeschen();
                artikelLoeschen.setVisible(true);
            }
        });

        /**
         * Öffnet den Zugang Buchen Dialog
         */
        zugangButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LagerGuiArtikelZuBuchen zuBuchen = new LagerGuiArtikelZuBuchen();
                zuBuchen.setVisible(true);
            }
        });

        /**
         * Öffnet den Abgang Buchen Dialog
         */
        abgangButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LagerGuiArtikelAbBuchen abBuchen = new LagerGuiArtikelAbBuchen();
                abBuchen.setVisible(true);
            }
        });

        /**
         * Gibt die toString des Lagers in der TextArea aus
         */
        ausgabeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // ausgeben Bestandsliste weil schöner auch wenns nicht so gut 
                // formatiert wie im Kommandozeilenmodus
                //textFeld.setText(meinLager.toString());
                try {
                    textFeld.setText(meinLager.ausgebenBestandsListe());
                } catch (ArtikelException ex) {
                    new FehlerMeldung(ex.toString());
                }
            }
        });
    } // Ende LagerGuiMain

    /**
     * Artikel anlegen Dialog
     */
    public class LagerGuiArtikelAnlegen extends JFrame {

        public LagerGuiArtikelAnlegen(Lager _meinLager) throws HeadlessException {
            meinLager = _meinLager;
            setTitle("Artikel anlegen");
            setSize(300, 200);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
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

            /**
             * Ok Button des ArtikelAnlegen Dialogs
             */
            okButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Artikel artikel = null;
                    try {
                        artikel = new Artikel(Integer.parseInt(artikelNummerFeld.getText()), 
                                artikelBezeichnungFeld.getText(), 
                                Integer.parseInt(artikelBestandFeld.getText()), 
                                Double.parseDouble(artikelPreisFeld.getText()));
                        meinLager.erstelleArtikel(artikel);
                    } catch (ArtikelException ex) {
                        new FehlerMeldung(ex.toString());                       
                    }finally {
                        setVisible(false);
                    }
                }
            });

            /**
             * Abbrechen Button des ArtikelAnlegen Dialogs
             */
            abbrechenButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    //beenden nicht anlegen, zurück ins menu
                    setVisible(false);
                }
            });
        }
    } // Ende LagerGuiArtikelAnlegen

    /**
     * Artikel löschen Dialog
     */
    private class LagerGuiArtikelLoeschen extends JFrame {

        public LagerGuiArtikelLoeschen() {
            setTitle("Artikel löschen");
            setSize(300, 200);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
            setLayout(new GridLayout(0, 2));

            JLabel artikelNummerLabel = new JLabel("Artikelnummer");
            JTextField artikelNummerFeld = new JTextField();

            JButton okButton = new JButton("Ok");
            JButton abbrechenButton = new JButton("Abbrechen");
      
            add(artikelNummerLabel);
            add(artikelNummerFeld);
            add(okButton);
            add(abbrechenButton);

            okButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        meinLager.entferneArtikel(Integer.parseInt(artikelNummerFeld.getText()));
                    } catch (ArtikelException ex) {
                        new FehlerMeldung(ex.toString());
                    } finally {
                        setVisible(false); 
                    }
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

    /**
     * Lager anlegen Dialog
     * bei nichtanlegen wird das Programm beendet
     */
    private static class LagerGuiLagerAnlegen extends JDialog {

        public LagerGuiLagerAnlegen() {
            setTitle("Lager anlegen");
            setSize(300, 200);
            setLocationRelativeTo(null);
            setLayout(new GridLayout(0, 2));          
            setModal(true);
            
            JLabel lagerNameLabel = new JLabel("Lagername");
            JTextField lagerNameFeld = new JTextField();

            JLabel lagerPlaetzeLabel = new JLabel("Lagerplaetze");
            JTextField lagerPlaetzeFeld = new JTextField();

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
                    String standort = lagerNameFeld.getText();
                    int plaetze = Integer.parseInt(lagerPlaetzeFeld.getText());
                    try {
                        meinLager = new Lager(standort, plaetze);
                    } catch (LagerException ex) {
                        System.out.println("Fehler beim Lageranlegen: " + ex);
                        //new FehlerMeldung(ex.toString()); geht nicht
                    }finally {
                       setVisible(false); 
                    }
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

    /**
     * Artikel Bestadn zubuchen Dialog
     */
    private class LagerGuiArtikelZuBuchen extends JFrame {

        public LagerGuiArtikelZuBuchen() {
            setTitle("Artikel zubuchen");
            setSize(300, 200);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
            setLayout(new GridLayout(0, 2));

            JLabel artikelNummerLabel = new JLabel("Artikelnummer");
            JTextField artikelNummerFeld = new JTextField();

            JLabel anzahlLabel = new JLabel("Anzahl");
            JTextField anzahlFeld = new JTextField();

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
                        //Logger.getLogger(LagerGuiMain.class.getName()).log(Level.SEVERE, null, ex);
                        new FehlerMeldung(ex.toString());
                    } finally {
                        setVisible(false);
                    }
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

    /**
     * Artikel Bestand abbuchen Dialog
     */
    private class LagerGuiArtikelAbBuchen extends JFrame {

        public LagerGuiArtikelAbBuchen() {
            setTitle("Artikel abbuchen");
            setSize(300, 200);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
            setLayout(new GridLayout(0, 2));

            JLabel artikelNummerLabel = new JLabel("Artikelnummer");
            JTextField artikelNummerFeld = new JTextField();
            
            JLabel anzahlLabel = new JLabel("Anzahl");
            JTextField anzahlFeld = new JTextField();

            JButton okButton = new JButton("Ok");
            JButton abbrechenButton = new JButton("Abbrechen");
    
            add(artikelNummerLabel);
            add(artikelNummerFeld);
            add(anzahlLabel);
            add(anzahlFeld);
            add(okButton);
            add(abbrechenButton);

            okButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                       meinLager.bucheAbgang(Integer.parseInt(artikelNummerFeld.getText()), Integer.parseInt(anzahlFeld.getText()));
                    } catch (ArtikelException ex) {
                        new FehlerMeldung(ex.toString());
                    }finally {
                        setVisible(false);
                    }
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

    /**
     * Fehlermeldungsklasse - inner class
     */
    private class FehlerMeldung {
        private String fehlerMeldung;
        
        public FehlerMeldung (String _fehlerMeldung) {
            this.fehlerMeldung = _fehlerMeldung;
            JOptionPane.showMessageDialog(rootPane, fehlerMeldung, "Fehler",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Main Methode des Programms
     * @param args 
     */
    public static void main(String[] args) {
        //look and feel
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch(Exception e){
            e.printStackTrace();
        }
        
        LagerGuiLagerAnlegen lagerAnlegen = new LagerGuiLagerAnlegen();
        lagerAnlegen.setVisible(true);

        JFrame mainFrame = new LagerGuiMain();
        mainFrame.setVisible(true);
    }
}// Ende LagerGuiMain
