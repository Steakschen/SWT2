package klausur.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Sortierer extends JFrame {

    private String text = "Rumpelstilzchen";
    private String errorText = "";
    JLabel jLabel;
    JTextArea jLabelError;
    int i = 0;

    char zuMerken = 0;
    int position = 0;
    char zuTauschen = 0;
    boolean tauschen = false;

    public Sortierer() {
        setTitle("Sortieren");
        setSize(400, 100);
        setLayout(new GridLayout(3, 1));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jLabel = new JLabel(text);
        jLabelError = new JTextArea(errorText);

        JPanel buttonFeld = new JPanel(new GridLayout(1, 3));
        JButton links = new JButton("Links");
        JButton merken = new JButton("Merken/Tauschen");
        JButton rechts = new JButton("Rechts");
        buttonFeld.add(links);
        buttonFeld.add(merken);
        buttonFeld.add(rechts);

        add(jLabel);
        add(buttonFeld);
        add(jLabelError);

        links.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                schiebeLinks();
            }
        });
        merken.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                toDo();
            }
        });
        rechts.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                schiebeRechts();
            }
        });
    }

    private void schiebeLinks() {
        if (i > 0) {
            i = i - 1;
            jLabel.setText(toUpperCase(i));
        } else if (i == 0) {
            jLabelError.setText("!<");
        }
    }

    private void schiebeRechts() {
        if (i < (text.length() - 1)) {
            i = i + 1;
            jLabel.setText(toUpperCase(i));
        } else if (i == (text.length() - 1)) {
            jLabelError.setText(">!");
        }
    }

    private void toDo() {
        StringBuffer textBuffer = new StringBuffer(jLabel.getText());
        // jLabelError.setText(textBuffer.toString());
        if (tauschen) {
            zuTauschen = textBuffer.charAt(i);
            textBuffer.setCharAt(i, zuMerken);
            textBuffer.setCharAt(position, zuTauschen);
            jLabelError.append("[ ]");
            text = textBuffer.toString();
            jLabel.setText(text);
            tauschen = false;
        } else {
            zuMerken = textBuffer.charAt(i);
            position = i;
            tauschen = true;
            jLabelError.append("[" + zuMerken + "]");
        }
    }

    private String toUpperCase(int index) {
        StringBuffer textBuffer = new StringBuffer(text);
        char lowerCharacter = Character.toLowerCase(textBuffer.charAt(0));
        textBuffer.setCharAt(0, lowerCharacter);
        char upperCharacter = Character.toUpperCase(textBuffer.charAt(index));
        textBuffer.setCharAt(index, upperCharacter);
        return textBuffer.toString();
    }

    public static void main(String[] args) {
        JFrame frame = new Sortierer();
        frame.setVisible(true);
    }
}
