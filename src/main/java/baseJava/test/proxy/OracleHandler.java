package baseJava.test.proxy;

/**
 * ORACLE中的添加用户方法
 */
public class OracleHandler implements IDBHandler {
    @Override
    public void addUser() {
        System.out.println("add user to db");
    }
}
