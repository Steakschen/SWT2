/*
 * Softwaretechnik 2 
 * Uebung 6
 * @author Carsten Gross / Moritz Fey
 */
package Queue;

import static java.lang.Thread.sleep;
import java.util.Random;

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
        for (int i = 0; i < args.length; i++) {
            QueueTest qt = new QueueTest(q, args[i], 100+i*50);
            qt.start();
        }
        //QueueTest tom = new QueueTest(q, "Tom", 100);
        //QueueTest jerry = new QueueTest(q, "Jerry", 50);
        //tom.start();
        //jerry.start();
    }
}
