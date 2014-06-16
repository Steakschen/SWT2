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
    
    /**
     * Funktion zum sortierten einfuegen in die Liste.
     * @param artikel Der einzufuegende Artikel
     * @throws MyException 
     */
    public void add(Artikel artikel) throws MyException {
        Knoten itemToAdd = new Knoten(artikel, null);
        if (count == 0) {
            head   = itemToAdd;
            count += 1;
        } 
        
        else {
            Knoten tmp = head;
            if (this.contains(artikel)) {
                throw new MyException(ARTIKEL_VORHANDEN);
            }
            while (tmp != null) {
                if (tmp.data.getArtikelNr() < itemToAdd.data.getArtikelNr()) {
                    Knoten tmpNext = tmp.next;
                    tmp.next = itemToAdd;
                    itemToAdd.next = tmpNext;
                }
                tmp = tmp.next;
            }
        }
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
    
    /**
     * Klasse fuer den Knoten der Liste.
     */
    public static class Knoten {
        private Artikel data;
        private Knoten next;
        
        public Knoten (Artikel _data, Knoten _next) {
            this.data = _data;
            this.next = _next;
        }
    }
}
