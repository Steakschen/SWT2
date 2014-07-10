/*
 * Softwaretechnik 2 
 * Uebung 6
 * @author Carsten Gross / Moritz Fey
 */

package Queue;

/**
 * Queue
 * @author Moritz / Carsten
 */
public class Queue {
    // erstes und letztes Element der Schlange
    Element head, tail;

    /**
     * Hängt ein Element in die Schlange ein
     * @param e 
     */
    public synchronized void append(Element e) {
        if (tail == null) // Schlange leer ?
        {
            head = e;
        } else {
            tail.next = e;  // einreihen
        }
        e.next = null;
        tail = e;   
        notify(); // Wieder was in der Schlange !
    }

    /**
     * Gibt das erste Element aus der Schlange zurück wenn es existiert
     * und löscht es heraus, wenn kein Element in der Schlange ist
     * gibt er warten... zurück
     * @return e    erstes Element der Schlange
     */
    public synchronized Element get() {
        try {
            while (head == null) {
                System.out.println("get ... warten");
                wait(); // Warten auf ein Element
            }
        } catch (InterruptedException x) {
            System.out.println("get() unterbrochen!");
            return null;
        }
        Element e = head; // erstes Element merken
        head = head.next; // Entferne es aus der Schlange
        if (head == null) // Schlange leer ?
        {
            tail = null;
        }
        return e;
    }

    /**
     * gibt die Schlange aus
     * Wenn kein Element in der Schlange ist (head=null) gibt er leer aus
     * ansonsten legt er ein Element anker an das bis es null (am Ende) ist 
     * durch die Queue iteriert und den Content der Liste an den String 
     * anhängt.
     * @return s    Ausgabe der Schlange
     */
    public String toString() {
        String s = new String();
        if (head == null) {
            s = "leer";
        } else {
            Element anker = head;
            while (anker != null) {
                s = s + anker.getContent() + " ";
                anker = anker.next;
            }
        }
        return s;
    }
}
