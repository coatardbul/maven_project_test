package baseJava.test.proxy;

/**
 * 代理类  添加用户方法前后记录日志
 */
public class OracleHandlerProxy implements IDBHandler {

    private OracleHandler oracleHandler;

    public OracleHandlerProxy(OracleHandler oh){
        this.oracleHandler = oh;
    }

    @Override
    public void addUser() {
        System.out.println("log first");
        oracleHandler.addUser();
        System.out.println("log last");
    }
}
