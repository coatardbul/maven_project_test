package mul_Thread.threadPool;



import java.util.concurrent.*;


public class ThreadPoolTest {

    public static Boolean flag = true;

    public static void main(String[] args) throws InterruptedException {

        RejectedExecutionHandler handler =new RejectPolicyDemo();
        ThreadPoolExecutor threadPoolExecutor =
                new DefineThreadPool(Runtime.getRuntime().availableProcessors(), 500, 30,
                        TimeUnit.MINUTES, new ArrayBlockingQueue<Runnable>(500),handler);

        Thread.sleep(30000);
        for (int i = 0; i < 100; i++) {
            if (flag) {
                Future<?> submit = threadPoolExecutor.submit(new AThread());
                try {
                    submit.get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }else {
                threadPoolExecutor.submit(new AThread());
            }
        }

        BlockingQueue<Runnable> queue = threadPoolExecutor.getQueue();
        while (true) {

            int queueSize = threadPoolExecutor.getQueue().size();
            System.out.println("当前排队线程数：" + queueSize);

            int activeCount = threadPoolExecutor.getActiveCount();
            System.out.println("当前活动线程数：" + activeCount);

            long completedTaskCount = threadPoolExecutor.getCompletedTaskCount();
            System.out.println("执行完成线程数：" + completedTaskCount);

            long taskCount = threadPoolExecutor.getTaskCount();
            System.out.println("总线程数：" + taskCount);

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //System.out.println(queue.toString());


    }
}
