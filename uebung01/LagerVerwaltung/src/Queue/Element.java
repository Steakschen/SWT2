/*
 * Softwaretechnik 2 
 * Uebung 1
 * @author Carsten Gross / Moritz Fey
 */

package Queue;

/**
 *
 * @author Moritz
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

