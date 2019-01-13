package baseJava.test.compareExtends;


/**
 * @author zhengbinMac
 *
 */
public class SugarCafe extends Cafe{

    @Override
    public void getCafe() {
        super.getCafe();
        this.addSugar();
    }

    private void addSugar() {
        System.out.println("加糖！");
    }
}

