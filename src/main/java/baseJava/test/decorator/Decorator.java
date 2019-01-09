package baseJava.test.decorator;

//装饰类
public  abstract  class Decorator implements Component {

    public Component component;

    public Decorator(Component component) {

        this.component = component;
    }

    public void biu() {

        this.component.biu();
    }
}