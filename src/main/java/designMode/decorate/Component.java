package designMode.decorate;

//基础接口
public interface Component {

    public void biu();
}
//具体实现类
 class ConcretComponent implements Component {

    @Override
    public void biu() {

        System.out.println("biubiubiu");
    }
}
//装饰类
 class Decorator implements Component {

    public Component component;

    public Decorator(Component component) {

        this.component = component;
    }

    @Override
    public void biu() {

        this.component.biu();
    }
}
//具体装饰类
 class ConcreteDecorator extends Decorator {

    public ConcreteDecorator(Component component) {

        super(component);
    }

    @Override
    public void biu() {

        System.out.println("ready?go!");
        this.component.biu();
    }
}