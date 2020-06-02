package mul_Thread;


import java.util.concurrent.*;

public class ExecutorTest {
    public  static ExecutorService executorService= Executors.newCachedThreadPool();
    public static void main(String[] args) throws ExecutionException, InterruptedException {
       Future f= executorService.submit(new RunnableDemo());
       f.get();     
    }
}
