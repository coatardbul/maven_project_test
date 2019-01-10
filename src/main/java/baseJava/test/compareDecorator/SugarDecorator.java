package baseJava.test.compareDecorator;


/**
 * @author zhengbinMac
 * 具体装饰角色
 */
public class SugarDecorator extends Decorator{

    public SugarDecorator(Cafe cafe) {
        super(cafe);
    }

    @Override
    public void getCafe() {
        super.getCafe();
        this.addSugar();
    }

    private void addSugar() {
        System.out.println("加糖！");
    }

}

