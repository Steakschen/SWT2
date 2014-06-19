/*
 * Softwaretechnik 2 
 * Uebung 3
 * @author Carsten Gross / Moritz Fey
 */
package lagerverwaltung;

/**
 * Klasse fuer den Knoten der Liste.
 */
public class Knoten {

    protected Artikel data;
    protected Knoten next;

    public Knoten(Artikel _data, Knoten _next) {
        this.data = _data;
        this.next = _next;
    }
}
