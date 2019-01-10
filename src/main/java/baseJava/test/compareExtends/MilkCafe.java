package baseJava.test.compareExtends;



/**
 * @author zhengbinMac
 *
 */
public class MilkCafe extends Cafe{

    @Override
    public void getCafe() {
        super.getCafe();
        this.addMilk();
    }

    private void addMilk() {
        System.out.println("加奶！");
    }
}

