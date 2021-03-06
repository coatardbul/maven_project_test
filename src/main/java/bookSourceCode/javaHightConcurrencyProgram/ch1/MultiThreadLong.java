package bookSourceCode.javaHightConcurrencyProgram.ch1;

import lombok.SneakyThrows;

public class MultiThreadLong {
    public static long t = 0;

    public static class ChangeT implements Runnable {
        private long to;

        public ChangeT(long to) {
            this.to = to;
        }

        @SneakyThrows
        public void run() {
            while (true) {
                MultiThreadLong.t = to;
                Thread.sleep(20000);
                System.out.println(t);

               // Thread.yield();
            }
        }
    }

    public static class ReadT implements Runnable {
        public void run() {
            while (true) {
                long tmp = MultiThreadLong.t;
                if (tmp != 111L && tmp != -999L && tmp != 333L && tmp != -444L)
                    System.out.println(tmp);
               Thread.yield();
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new ChangeT(111L)).start();
        new Thread(new ChangeT(-999L)).start();
        new Thread(new ChangeT(333L)).start();
        new Thread(new ChangeT(-444L)).start();
        new Thread(new ReadT()).start();

        System.out.println(t);
    }
}
