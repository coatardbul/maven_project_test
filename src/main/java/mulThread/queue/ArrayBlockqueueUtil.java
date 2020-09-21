package mulThread.queue;

import org.junit.Test;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class ArrayBlockqueueUtil {
    public static void main(String[] args) throws InterruptedException {
        Queue queue = QueueUtil.getArrayBlockQueueByLength(30);

        ArrayBlockingQueue a=new ArrayBlockingQueue(10);
        a.put("1111111111");
        a.put("1111111111");
        a.put("1111111111");

        a.put("1111111111");




    }

    @Test
    public void testPoll() {
        Queue queue = QueueUtil.getArrayBlockQueueByLength(30);

        for (int i = 0; i < 32; i++) {
            Object poll = queue.poll();
            System.out.println(poll);
        }

    }

    @Test
    public void testRemove(){
        Queue queue = QueueUtil.getArrayBlockQueueByLength(30);

        for (int i = 0; i < 32; i++) {
            Object remove = queue.remove();
            System.out.println(remove);

        }
    }

    @Test
    public void testOffer() {
        Queue queue = new ArrayBlockingQueue(30);
        boolean b1 = queue.offer("111");
        boolean b2 = queue.offer("222");
        boolean b3 = queue.offer("333");
        boolean b4 = queue.offer("444");
        boolean b5 = queue.offer("555");
        boolean b6 = queue.offer("666");
        boolean b7 = queue.offer("777");
        boolean b8 = queue.offer("888");
        boolean b9 = queue.offer("999");
        boolean b10 = queue.offer("1110");
        boolean b11 = queue.offer("1221");
        boolean b12 = queue.offer("1332");
        boolean b13 = queue.offer("1443");
        boolean b14 = queue.offer("1554");
        boolean b15 = queue.offer("1665");
        boolean b16 = queue.offer("1776");
        boolean b17 = queue.offer("1887");
        boolean b18 = queue.offer("1998");
        boolean b19 = queue.offer("2109");
        boolean b20 = queue.offer("2220");
        boolean b21 = queue.offer("2331");
        boolean b22 = queue.offer("2442");
        boolean b23 = queue.offer("2553");
        boolean b24 = queue.offer("2664");
        boolean b25 = queue.offer("2775");
        boolean b26 = queue.offer("2886");
        boolean b27 = queue.offer("2997");
        boolean b28 = queue.offer("3108");
        boolean b29 = queue.offer("3219");
        boolean b30 = queue.offer("3330");
        boolean b31 = queue.offer("3441");
        boolean b32 = queue.offer("3552");
        boolean b33 = queue.offer("3663");
        System.out.println("b1:" + b1);
        System.out.println("b2:" + b2);
        System.out.println("b3:" + b3);
        System.out.println("b4:" + b4);
        System.out.println("b5:" + b5);
        System.out.println("b6:" + b6);
        System.out.println("b7:" + b7);
        System.out.println("b8:" + b8);
        System.out.println("b9:" + b9);
        System.out.println("b10:" + b10);
        System.out.println("b11:" + b11);
        System.out.println("b12:" + b12);
        System.out.println("b13:" + b13);
        System.out.println("b14:" + b14);
        System.out.println("b15:" + b15);
        System.out.println("b16:" + b16);
        System.out.println("b17:" + b17);
        System.out.println("b18:" + b18);
        System.out.println("b19:" + b19);
        System.out.println("b20:" + b20);
        System.out.println("b21:" + b21);
        System.out.println("b22:" + b22);
        System.out.println("b23:" + b23);
        System.out.println("b24:" + b24);
        System.out.println("b25:" + b25);
        System.out.println("b26:" + b26);
        System.out.println("b27:" + b27);
        System.out.println("b28:" + b28);
        System.out.println("b29:" + b29);
        System.out.println("b30:" + b30);
        System.out.println("b31:" + b31);
        System.out.println("b32:" + b32);
        System.out.println("b33:" + b33);


    }

    @Test
    public void testAddMethod() {
        Queue queue = new ArrayBlockingQueue(30);
        boolean b1 = queue.add("111");
        boolean b2 = queue.add("222");
        boolean b3 = queue.add("333");
        boolean b4 = queue.add("444");
        boolean b5 = queue.add("555");
        boolean b6 = queue.add("666");
        boolean b7 = queue.add("777");
        boolean b8 = queue.add("888");
        boolean b9 = queue.add("999");
        boolean b10 = queue.add("1110");
        boolean b11 = queue.add("1221");
        boolean b12 = queue.add("1332");
        boolean b13 = queue.add("1443");
        boolean b14 = queue.add("1554");
        boolean b15 = queue.add("1665");
        boolean b16 = queue.add("1776");
        boolean b17 = queue.add("1887");
        boolean b18 = queue.add("1998");
        boolean b19 = queue.add("2109");
        boolean b20 = queue.add("2220");
        boolean b21 = queue.add("2331");
        boolean b22 = queue.add("2442");
        boolean b23 = queue.add("2553");
        boolean b24 = queue.add("2664");
        boolean b25 = queue.add("2775");
        boolean b26 = queue.add("2886");
        boolean b27 = queue.add("2997");
        boolean b28 = queue.add("3108");
        boolean b29 = queue.add("3219");
        boolean b30 = queue.add("3330");
        boolean b31 = queue.add("3441");
        boolean b32 = queue.add("3552");
        boolean b33 = queue.add("3663");


        System.out.println("b1:" + b1);
        System.out.println("b2:" + b2);
        System.out.println("b3:" + b3);
        System.out.println("b4:" + b4);
        System.out.println("b5:" + b5);
        System.out.println("b6:" + b6);
        System.out.println("b7:" + b7);
        System.out.println("b8:" + b8);
        System.out.println("b9:" + b9);
        System.out.println("b10:" + b10);
        System.out.println("b11:" + b11);
        System.out.println("b12:" + b12);
        System.out.println("b13:" + b13);
        System.out.println("b14:" + b14);
        System.out.println("b15:" + b15);
        System.out.println("b16:" + b16);
        System.out.println("b17:" + b17);
        System.out.println("b18:" + b18);
        System.out.println("b19:" + b19);
        System.out.println("b20:" + b20);
        System.out.println("b21:" + b21);
        System.out.println("b22:" + b22);
        System.out.println("b23:" + b23);
        System.out.println("b24:" + b24);
        System.out.println("b25:" + b25);
        System.out.println("b26:" + b26);
        System.out.println("b27:" + b27);
        System.out.println("b28:" + b28);
        System.out.println("b29:" + b29);
        System.out.println("b30:" + b30);
        System.out.println("b31:" + b31);
        System.out.println("b32:" + b32);
        System.out.println("b33:" + b33);
    }
}
