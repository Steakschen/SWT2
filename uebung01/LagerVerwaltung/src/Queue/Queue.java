/*
 * Softwaretechnik 2 
 * Uebung 6
 * @author Carsten Gross / Moritz Fey
 */

package Queue;

import java.text.SimpleDateFormat;
import java.util.Date;

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
                System.out.println(getTimeStamp() + "warten ...");
                System.out.flush();
                wait(); // Warten auf ein Element
            }
        } catch (InterruptedException x) {
            System.out.println(getTimeStamp() + "get() unterbrochen!");
            System.out.flush();
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
     * Liefert einen Timestamp für die Ausgabe zurück.
     * @return timestamp
     */
    public String getTimeStamp() {
        return new SimpleDateFormat("HH:mm:ss:SSS ").format(new Date());    
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
