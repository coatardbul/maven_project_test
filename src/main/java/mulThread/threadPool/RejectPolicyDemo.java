package mulThread.threadPool;

import java.util.concurrent.ThreadPoolExecutor;

/**
 *
 *  new AbortPolicy(); 默认抛出异常
 *  DiscardPolicy  丢弃异常
 *
 *CallerRunsPolicy
 */
public class RejectPolicyDemo extends ThreadPoolExecutor.CallerRunsPolicy {
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
        System.out.println("reject"+r.toString());
        System.out.println("sdfsadf"+e.toString());

        super.rejectedExecution(r, e);
    }
}
