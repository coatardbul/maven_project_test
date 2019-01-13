package baseJava.test.compareExtends;


/**
 * @author zhengbinMac
 *
 */
public class SugarMilkCafe extends Cafe{
    @Override
    public void getCafe() {
        super.getCafe();
        this.addSugarAndMilk();
    }

    private void addSugarAndMilk() {
        System.out.println("加糖!");
        System.out.println("加奶!");
    }
}

