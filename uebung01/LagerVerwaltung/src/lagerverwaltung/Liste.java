/*
 * Softwaretechnik 2 
 * Uebung 3
 * @author Carsten Gross / Moritz Fey
 */
package lagerverwaltung;

/**
 * Listen Klasse
 *
 * @author Moritz / Carsten
 */
public class Liste {

    private static final String ARTIKEL_VORHANDEN   = "\tArtikel bereits vorhanden!\n";
    private static final String LISTE_LEER          = "\tListe ist leer!\n";
    private static final String LISTE_VOLL          = "\tListe ist voll!\n";
    private static final String AUSSERHALB_LISTE    = "\tIndex ausserhalb der Liste!\n";

    private int     size;
    private Knoten  head;


    /**
     * Konstruktor fuer die Klasse Liste.
     * 
     */
    public Liste() {
        this.size       = 0;
        this.head       = null;
    }

    /**
     * Fügt ein Element an der ersten Stelle ein
     *
     * @param artikel
     * @throws MyException
     */
    public void addFirst(Artikel artikel) throws MyException {
        Knoten itemToAdd    = new Knoten(artikel, null);
        Knoten tmp          = head;
        head                = itemToAdd;
        head.next           = tmp;
        size                += 1;
    }

    /**
     * Funktion zum sortierten einfuegen in die Liste.
     *
     * @param artikel Der einzufuegende Artikel
     * @throws MyException
     */
    public void add(Artikel artikel) throws MyException {
        if (head == null) {
            addFirst(artikel);
        } else {
            if (contains(artikel)) {
                throw new MyException(ARTIKEL_VORHANDEN);
            } else {
                Knoten aktuell, letzter;
                Knoten neu = new Knoten(artikel, null);
                if (head.data.getArtikelNr() > artikel.getArtikelNr()) {
                    addFirst(artikel);
                } else {
                    aktuell = head.next;
                    letzter = head;
                    while ((aktuell != null) && (aktuell.data.getArtikelNr() < artikel.getArtikelNr())) {
                        aktuell = aktuell.next;
                        letzter = letzter.next;
                    }
                    letzter.next = neu;
                    if (aktuell != null) {
                        neu.next = aktuell;
                    }
                    size++;
                }
            }
        }
    }

    /**
     * Funktion die prueft, ob ein Artikel in der Liste ist.
     *
     * @param artikel
     * @return true, falls Artikel im Lager, ansonsten false
     */
    public boolean contains(Artikel artikel) {
        Knoten tmp = head;
        boolean containsItem = false;
        for (int i = 0; i < size; i++) {
            if (tmp.data.getArtikelNr() == artikel.getArtikelNr()) {
                containsItem = true;
            }
            tmp = tmp.next;
        }
        return containsItem;
    }

    /**
     * Gibt das erste Element/den ersten Artikel der Liste zurueck.
     *
     * @return erstes Listenelement
     */
    public Artikel getHead() {
        return head.data;
    }

    /**
     * Artikel mit Artikelnummer finden.
     *
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
     *
     * @param artikelNummer Artikelnummer des zu loeschenden Artikels
     * @throws lagerverwaltung.MyException
     */
    public void delete(int artikelNummer) throws MyException {
        Knoten anker = head;
        if (anker != null) {
            if (anker.data.getArtikelNr() == artikelNummer) {
                popFront();
                size--; //carsten
            } else {
                Knoten p = anker;
                while (p.next != null && p.next.data.getArtikelNr() != artikelNummer) {
                    p = p.next;
                }
                if (p.next != null) {
                    p.next = p.next.next;
                }
            }
        } else {
            throw new MyException(LISTE_LEER);
        }
    }

    /**
     * Gibt den Artikel an der Position in der Liste zurück
     *
     * @param position
     * @return
     * @throws MyException
     */
    public Artikel getArtikelAtPos(int position) throws MyException {
        if (position < 0 || position >= size) {
            throw new MyException(AUSSERHALB_LISTE);
        }

        Knoten anker = head;
        int it = 0;

        while (anker != null && it <= position) {
            if (anker != null && it == position) {
                return anker.data;
            }
            anker = anker.next;
            it++;
        }
        return null;
    }

    /**
     * Gibt die Anzahl der Elemente der Liste zurueck.
     *
     * @return Anzahl der Elemente in der Liste
     */
    public int getSize() {
        return size;
    }

    /**
     * Prueft, ob die Liste leer ist.
     *
     * @return true, wenn Liste leer, ansonsten false
     */
    public boolean isEmpty() {
        return size == 0;
    }
    
    /**
     * Löscht das erste Element der Liste.
     */
    public void popFront() {
        Knoten oldHead, newHead;
        oldHead     = head;
        newHead     = oldHead.next;
        head        = newHead;
    }

    /**
     * toString()-Methoder der Listenklasse.
     *
     * @return
     */
    @Override
    public String toString() {
        Knoten tmp          = head;
        String listenString = new String();
        while (tmp != null) {
            listenString    += tmp.data.toString();
            tmp             = tmp.next;
        }
        return listenString;
    }
}
