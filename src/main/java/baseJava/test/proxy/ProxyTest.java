package baseJava.test.proxy;

public class ProxyTest {

    public static void main(String[] args) {
        IDBHandler h = new OracleHandlerProxy(new OracleHandler());
        h.addUser();
    }
}
