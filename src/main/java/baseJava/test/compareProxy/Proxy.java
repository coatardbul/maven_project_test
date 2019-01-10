package baseJava.test.compareProxy;


/**
 * @author zhengbinMac
 *
 */
public class Proxy implements Cafe{

    private ConcreteCafe concreteCafe;

    public Proxy() {
        this.concreteCafe = new ConcreteCafe();
    }

    @Override
    public void getCafe() {
        concreteCafe.getCafe();
        addMilk();
        addSugar();
    }

    private void addMilk() {
        System.out.println("加奶！");
    }

    private void addSugar() {
        System.out.println("加糖！");
    }

}

