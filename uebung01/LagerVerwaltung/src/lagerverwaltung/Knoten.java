/*
 * Softwaretechnik 2 
 * Uebung 3
 * @author Carsten Gross / Moritz Fey
 */
package lagerverwaltung;

import java.io.Serializable;

/**
 * Klasse fuer den Knoten der Liste.
 */
public class Knoten implements Serializable {

    protected Artikel data;
    protected Knoten next;

    /**
     * Legt ein Element / einen Knoten an
     * @param _data     beinhaltet die Daten des Elements
     * @param _next     zeigt auf den nächsten Knoten
     */
    public Knoten(Artikel _data, Knoten _next) {
        this.data = _data;
        this.next = _next;
    }
}
