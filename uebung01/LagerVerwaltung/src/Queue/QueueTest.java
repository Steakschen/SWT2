/*
 * Softwaretechnik 2 
 * Uebung 6
 * @author Carsten Gross / Moritz Fey
 */
package Queue;

import static java.lang.Thread.sleep;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Moritz
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

    public String getMyName() {
        return name;
    }
    
    public boolean shallGet() {
        Random r = new Random(System.currentTimeMillis());
        int i = r.nextInt();
        // get soll etwa doppelt so oft wie append aufgerufen werden
        return (i % 3 != 0);
    }

    public void run() {
        System.out.println(">>>>>>>>> "+ name + 
                " gestartet mit delay = " + delay + " <<<<<<<<<<<<");
        System.out.flush();
        try {
            for (int i = 0; i < 10; ++i) {
                if (shallGet()) {
                    System.out.print(name + "\t: \t\tget(): ");
                    System.out.println(name + "\t: get():"
                            + q.get().getContent() + " " + "\tQueue: " + q);
                } else {
                    System.out.println(name + ": append("
                            + name + i + ") " + "\tQueue: " + q);
                    q.append(new Element(name + i));
                }
                
                System.out.println(name + ": " + "sleep " + delay +"\tQueue: " + q);
                System.out.flush();
                sleep(delay); // warten
                System.out.println(name + ": " + "sleep Ende" + "\tQueue: " + q);
                System.out.flush();
            }
        } catch (InterruptedException e) {
            return;
        }
    }

    public static void main(String[] args) {
        Queue q = new Queue();
        QueueTest [] qt = new QueueTest[args.length];
        int waitingThreads = 0;
        int finishedThreads = 0;
        String zustaende;
        boolean mainLoopIsRunning = true;
        for (int i = 0; i < args.length; i++) {
            qt[i] = new QueueTest(q, args[i], 100 + i * 50);
            qt[i].start();
        }
        while (mainLoopIsRunning) {
            try {
                sleep(2000);
                zustaende = "ZUSTAENDE: ";
                for (int i = 0; i < args.length; i++) {
                    zustaende = zustaende + "  " + qt[i].getMyName() + "=" + qt[i].getState();
                   if (qt[i].getState() == Thread.State.WAITING) {
                       waitingThreads++;
                   }
                   if (qt[i].getState() == Thread.State.TERMINATED) {
                       finishedThreads++;
                   }
                }
                System.out.println(zustaende);
                System.out.flush();
                
                if (finishedThreads == args.length) {
                    mainLoopIsRunning = false;
                }
                
                if (waitingThreads > 0) {
                    for (int i = 0; i <= waitingThreads; i++) {
                        q.append(new Element(":-)" + i));
                    }
                }
                waitingThreads = 0;
                finishedThreads = 0;
            } catch (InterruptedException ex) {
                Logger.getLogger(QueueTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
