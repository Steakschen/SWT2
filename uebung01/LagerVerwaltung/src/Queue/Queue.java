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
public class Queue {
    // erstes und letztes Element der Schlange

    Element head, tail;

    public synchronized void append(Element e) {
        if (tail == null) // Schlange leer ?
        {
            head = e;
        } else {
            tail.next = e;
        }
        e.next = null;
        tail = e;
        notify(); // Wieder was in der Schlange !
    }

    public synchronized Element get() {
        try {
            while (head == null) {
                System.out.println("warten ...");
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
}
