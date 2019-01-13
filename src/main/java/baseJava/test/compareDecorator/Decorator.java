package baseJava.test.compareDecorator;



/**
 * @author zhengbinMac
 * 装饰角色
 */
public class Decorator implements Cafe{

    public Cafe cafe;

    public Decorator(Cafe cafe) {
        this.cafe = cafe;
    }

    @Override
    public void getCafe() {
        cafe.getCafe();
    }

}

