/*
 * Softwaretechnik 2 
 * Uebung 1
 * @author Carsten Gross / Moritz Fey
 */

package lagerverwaltung;

/**
 *
 * @author Moritz
 */
public class Liste {
    
    private static final String ARTIKEL_VORHANDEN = "\nArtikel bereits vorhanden!\n"; 
    
    private int         count;
    private Knoten      head;
    
    /**
     * Konstruktor fuer die Klasse Liste.
     */
    public Liste() {
        this.count      = 0;
        this.head       = null;
    }
    
    public void addFirst (Artikel artikel) throws MyException {
        Knoten itemToAdd = new Knoten(artikel, null, null);
        Knoten tmp = head;
        head = itemToAdd;
        head.next = tmp;
        count += 1;
    }
    
    /**
     * Funktion zum sortierten einfuegen in die Liste.
     * @param index
     * @param artikel Der einzufuegende Artikel
     * @throws MyException 
     */
    public void add(int index, Artikel artikel) throws MyException {
        /*if (index > count) {
            throw new MyException("Index out of Bound");
        } */  
        
        if (index == 1) {
            addFirst(artikel);
        } 
        else {
           
            if (this.contains(artikel)) {
                throw new MyException(ARTIKEL_VORHANDEN);
            }
            
            int i = 1;
            Knoten neuerKnoten      = new Knoten(artikel, null, null);
            Knoten tmpKnoten        = head;
            Knoten naechsterKnoten  = null;
            
            while (i < index-1) {
                naechsterKnoten     = tmpKnoten.next;
                tmpKnoten           = naechsterKnoten;
                i++;
            }
            
            neuerKnoten.prev        = tmpKnoten;
            neuerKnoten.next        = tmpKnoten.next;
            tmpKnoten.next.prev     = neuerKnoten;
            tmpKnoten.next          = neuerKnoten;
            count++;
        }
    }
    
    public int findeArtikel(int artikelNummer) {
        int position        = -1;
        int zaehler         =  0;
        Knoten tmpKnoten    = head;
        
        while (tmpKnoten != null) {
            zaehler += 1;
            if (tmpKnoten.data.getArtikelNr() == artikelNummer) {
                position = zaehler;
            }
        }
        return position;
    }
    
    /**
     * Funktion die prueft, ob ein Artikel in der Liste ist.
     * @param artikel
     * @return true, falls Artikel im Lager, ansonsten false
     */
    public boolean contains(Artikel artikel) {
        Knoten tmp = head;
        boolean containsItem = false;
        for (int i = 0; i < count; i++) {
            if (tmp.data.getArtikelNr() == artikel.getArtikelNr()) {
                containsItem = true;
            }
            tmp = tmp.next;
        }
        return containsItem;
    }
    
    /**
     * Gibt das erste Element der Liste zurueck.
     * @return erstes Listenelement
     */
    public Artikel getHead() {
        return head.data;
    }
    
    /**
     * Artikel mit Artikelnummer finden.
     * @param artikelNummer Artikelnummer des gesuchten Artikels
     * @return Referenz auf den Artikel, oder im Fehlerfall null
     */
    public Artikel getArtikel(int artikelNummer) {
        Knoten tmp = head;
        while (tmp != null) {
            if (tmp.data.getArtikelNr() == artikelNummer) {
                return tmp.data;
            }
        }
        return null;
    }
    
    /**
     * Loeschen eines Artikels per Artikelnummer.
     * @param artikelNummer Artikelnummer des zu loeschenden Artikels
     */
    public void delete(int artikelNummer) {
        Knoten tmp = head;
        while (tmp != null) {
            if (tmp.next.data.getArtikelNr() == artikelNummer) {
                tmp.next = tmp.next.next;
                count -= 1;
            }
        }
    }
    
    public String toString() {
        Knoten tmp = head;
        String listenString = new String();
        while (tmp != null) {
            listenString += tmp.data.toString();
            tmp = tmp.next;
        }
        return listenString;
    }
    
    /**
     * Klasse fuer den Knoten der Liste.
     */
    public static class Knoten {
        private Artikel data;
        private Knoten prev;
        private Knoten next;
        
        public Knoten (Artikel _data, Knoten _prev, Knoten _next) {
            this.data = _data;
            this.prev = _prev;
            this.next = _next;
        }
    }
}
