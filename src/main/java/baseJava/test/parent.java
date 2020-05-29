package baseJava.test;

public class parent {
    static int a = 3;

    static {
        System.out.println("parent static block");
    }

    static void doSomething() {
        System.out.println("do something");
    }
}

class child extends parent {
    static int b = 3;
    static {
        System.out.println("child static block");
    }
}

class test {
    //    public static void main(String args){
//        System.out.println(child.a);
//        child.doSomething();
//    }
    public static void main(String[] args) {
               System.out.println(child.a);
        child.doSomething();
        System.out.println(child.b);
    }
}