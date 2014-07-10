/*
 * Softwaretechnik 2 
 * Uebung 6
 * @author Carsten Gross / Moritz Fey
 */
package Queue;

import static java.lang.Thread.sleep;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Die Klasse QueueTest
 *
 * @author Moritz / Carsten
 */
public class QueueTest extends Thread {

    Queue q;
    String name;
    int delay; // Wartezeit

    QueueTest(Queue q, String n, int delayTime) {
        this.q = q;
        name = n;
        delay = delayTime;
    }

    /**
     * gibt den Namen der Schlange zurück
     *
     * @return
     */
    public String getMyName() {
        return name;
    }

    public boolean shallGet() {
        Random r = new Random(System.currentTimeMillis());
        int i = r.nextInt();
        // get soll etwa doppelt so oft wie append aufgerufen werden
        return (i % 3 != 0);
    }

    public String getTimeStamp() {
        Calendar cal = Calendar.getInstance();
        String zeit = cal.get(Calendar.HOUR_OF_DAY)
                + ":" + cal.get(Calendar.MINUTE)
                + ":" + cal.get(Calendar.SECOND)
                + "." + cal.get(Calendar.MILLISECOND)
                + " ";
        return zeit;
    }

    //TODO: Zeitstempel
    //TODO: Ausgabe an die von Pick anpassen
    public void run() {

        System.out.println(getTimeStamp() + ">>>>>>>>> " + name
                + " gestartet mit delay = " + delay + " <<<<<<<<<<<<");
        System.out.flush();
        try {
            for (int i = 0; i < 10; ++i) {
                if (shallGet()) {
                    //System.out.print(name + "\t: \t\tget(): ");
                    System.out.println(getTimeStamp() + name + ": get("
                            + q.get().getContent() + "): " + "\t\tQueue: " + q);
                } else {
                    System.out.println(getTimeStamp() + name + ": append("
                            + name + i + ") " + "\t\tQueue: " + q);
                    q.append(new Element(name + i));
                }

                System.out.println(getTimeStamp() + name + ": " + "sleep " + delay + "\t\t\tQueue: " + q);
                System.out.flush();
                sleep(delay); // warten
                System.out.println(getTimeStamp() + name + ": " + "sleep Ende" + "\t\t\tQueue: " + q);
                System.out.flush();
            }
        } catch (InterruptedException e) {
            return;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        /* Zeit im Format 17:30:24.584 anlegen */
        Calendar cal = Calendar.getInstance();
        String zeit = cal.get(Calendar.HOUR_OF_DAY)
                + ":" + cal.get(Calendar.MINUTE)
                + ":" + cal.get(Calendar.SECOND)
                + "." + cal.get(Calendar.MILLISECOND)
                + " ";

        System.out.println(zeit+ " START ");
        Queue q = new Queue();
        /* Array fuer die verschiedenen Threads, damit man sie spaeter wieder 
         abfragen kann, ist so lang wie die Argumentliste*/
        QueueTest[] qt = new QueueTest[args.length];
        /* Anzahl der wartenden Threads */
        int waitingThreads = 0;
        /* Anzahl der beendeten Threads */
        int finishedThreads = 0;
        /* String fuer die Ausgabe der Zustaende */
        String zustaende;
        /* Variable fuer die Steuerung der Main Schleife */
        boolean mainLoopIsRunning = true;
        /* Threads erstellen*/
        for (int i = 0; i < args.length; i++) {
            qt[i] = new QueueTest(q, args[i], 100 + i * 50);
        }
        /* Threads starten */
        for (int i = 0; i < args.length; i++) {
            qt[i].start();
        }
        /* Hauptschleife, die alle 2 Sekunden die Zustaende der Threads checkt und
         ausgibt.
         */
        while (mainLoopIsRunning) {
            try {
                /* Schlafe 2 Sekunden */
                sleep(2000);
                zustaende = "\nZUSTAENDE: ";
                /* Durchlaufe alle Threads im Array und baue String fuer Ausgabe */
                for (int i = 0; i < args.length; i++) {
                    zustaende = zustaende + "  " + qt[i].getMyName() + "=" + qt[i].getState();
                    /* Wenn der Thread im Zustand Waiting ist, dann zaehle 
                     waitingThreads hoch */
                    if (qt[i].getState() == Thread.State.WAITING) {
                        waitingThreads++;
                    }
                    /* Wenn der Thread im Zustand Terminated ist, dann zaehle 
                     finishedThreads hoch */
                    if (qt[i].getState() == Thread.State.TERMINATED) {
                        finishedThreads++;
                    }
                }
                /* Ausgabe der Zustaende */
                System.out.println(zeit + zustaende);
                System.out.flush();

                /* Beenden der Schleife, wenn alle Threads beendet sind */
                if (finishedThreads == args.length) {
                    mainLoopIsRunning = false;
                }

                /* Elemente in die Queue wenn alle Threads aus waiting stehn */
                if (waitingThreads > 0) {
                    for (int i = 0; i <= waitingThreads; i++) {
                        q.append(new Element(":-)" + i));
                    }
                }
                /* Zuruecksetzen der Werte fuer waitingThreads, finishedThreads */
                waitingThreads = 0;
                finishedThreads = 0;
            } catch (InterruptedException ex) {
                Logger.getLogger(QueueTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
