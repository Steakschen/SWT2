/*
 * Softwaretechnik 2 
 * Uebung 1
 * @author Carsten Gross / Moritz Fey
 */

package lagerverwaltung;

import java.io.File;

/**
 * Klasse die DateiExceptions zur Verfügung stellt.
 * 
 * @author Moritz
 */
public class DateiException extends Exception {
    private static final String DATEI_VORHANDEN = "\tDie Datei mit dem folgenden Namen ist bereits vorhanden: ";
    private static final String DATEI_NICHT_VORHANDEN = "\tDie Datei mit dem folgenden Name ist nicht vorhanden: ";
    
    /**
     * Konstruktor der Klasse DateiException.
     * @param fehlerMeldung Die Fehlermeldung
     */
    public DateiException(String fehlerMeldung) {
        super(fehlerMeldung);
    }
    
    /**
     * Prüft, ob bereits eine Datei mit dem angegebenen Namen vorhanden ist. 
     * Wirft im Fehlerfall direkt eine Exception.
     * @param datei Name der Datei
     * @throws DateiException 
     */
    public static void dateiVorhanden (File datei) throws DateiException {
        if (datei.exists()) {
            throw new DateiException(DATEI_VORHANDEN + datei.getName());
        }
    }
    
    /**
     * Prüft, ob bereits eine Datei mit dem angegebenen Namen noch nicht vorhanden ist. 
     * Wirft im Fehlerfall direkt eine Exception.
     * @param datei
     * @throws DateiException 
     */
    public static void dateiNichtVorhanden (File datei) throws DateiException {
        if (!datei.exists()) {
            throw new DateiException(DATEI_NICHT_VORHANDEN + datei.getName());
        }
    }
}
