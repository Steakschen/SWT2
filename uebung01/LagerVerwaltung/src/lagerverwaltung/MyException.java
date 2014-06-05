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
public class MyException extends Exception {
    private String message;
    
    public MyException(String message_) {
        this.message = message_;
    }
    
    public MyException () { }
    
    public String toString() {
        return message;
    }
}
