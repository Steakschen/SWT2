/*
 * Softwaretechnik 2 
 * Uebung 1
 * @author Carsten Gross / Moritz Fey
 */
package Queue;

import static java.lang.Thread.sleep;
import java.util.Random;

/**
 *
 * @author Moritz
 */
public class QueueTest {

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
        try {
            for (int i = 0; i < 20; ++i) {
                if (shallGet()) {
                    System.out.print(name + "\t: \t\tget(): ");
                    System.out.println(name + "\t: get():"
                            + q.get().getContent() + " ");
                } else {
                    System.out.println(name + ": append("
                            + name + i + ") ");
                    q.append(new Element(name + i));
                }
                sleep(delay); // warten
            }
        } catch (InterruptedException e) {
            return;
        }
    }

    public static void main(String[] args) {
        Queue q = new Queue();
        QueueTest tom = new QueueTest(q, "Tom", 100);
        QueueTest jerry = new QueueTest(q, "Jerry", 50);
        tom.start();
        jerry.start();
    }
}
