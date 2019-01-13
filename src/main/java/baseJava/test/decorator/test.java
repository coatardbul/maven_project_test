package baseJava.test.decorator;
//装饰器模式的测试
public class test {
    public static void main(String[] args) {
        //https://www.cnblogs.com/jzb-blog/p/6717349.html
        Decorator decorator=new ConcreteDecorator(new ConcretComponent());
        decorator.biu();
    }
}
