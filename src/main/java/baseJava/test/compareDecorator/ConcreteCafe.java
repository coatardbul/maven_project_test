package baseJava.test.compareDecorator;


/**
 * @author zhengbinMac
 * 具体构件角色
 */
public class ConcreteCafe implements Cafe{

    @Override
    public void getCafe() {
        System.out.println("上一杯原味咖啡！");
    }

}

