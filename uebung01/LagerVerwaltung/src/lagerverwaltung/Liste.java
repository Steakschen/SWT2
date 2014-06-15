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
    
    private int count;
    private Knoten head;
    
    public Liste() {
        this.count = 0;
        this.head  = null;
    }
    
    public void add(Artikel artikel) throws MyException {
        Knoten itemToAdd = new Knoten(artikel, null);
        if (count == 0) {
            head = itemToAdd;
            count += 1;
        } 
        
        else {
            Knoten tmp = head;
            if (this.contains(artikel)) {
                throw new MyException("Artikel bereits in Liste");
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
    
    public boolean contains(Artikel _artikel) {
        Knoten tmp = head;
        boolean containsItem = false;
        for (int i = 0; i < count; i++) {
            if (tmp.data.getArtikelNr() == _artikel.getArtikelNr()) {
                containsItem = true;
            }
            tmp = tmp.next;
        }
        return containsItem;
    }
    
    public Artikel getHead() {
        return head.data;
    }
    
    public void delete(int _artikelnummer) {
        Knoten tmp = head;
        while (tmp != null) {
            if (tmp.next.data.getArtikelNr() == _artikelnummer) {
                tmp.next = tmp.next.next;
                count -= 1;
            }
        }
    }
    
    public static class Knoten {
        private Artikel data;
        private Knoten next;
        
        public Knoten (Artikel _data, Knoten _next) {
            this.data = _data;
            this.next = _next;
        }
    }
}
