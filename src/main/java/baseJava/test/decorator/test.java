package baseJava.test.decorator;

public class test {
    public static void main(String[] args) {
        Decorator decorator=new ConcreteDecorator(new ConcretComponent());
        decorator.biu();
    }
}
