package baseJava.test.compareExtends;


/**
 * @author zhengbinMac
 *
 */
public class Test {

    public static void main(String[] args) {
        Cafe milkCafe = new MilkCafe();
        milkCafe.getCafe();

        Cafe sugarCafe = new SugarCafe();
        sugarCafe.getCafe();

        Cafe sugarMilkCafe = new SugarMilkCafe();
        sugarMilkCafe.getCafe();
    }

}

