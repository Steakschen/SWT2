/*
 * Softwaretechnik 2 
 * Uebung 6
 * @author Carsten Gross / Moritz Fey
 */

package Queue;

/**
 * Klasse Element stellt ein 
 * @author Carsten / Moritz
 */
public class Element {

    Element next = null;
    String content;

    public Element(String s) {
        content = s;
    }

    public String getContent() {
        return content;
    }
}

