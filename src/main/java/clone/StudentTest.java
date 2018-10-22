package clone;

import org.junit.Test;

public class StudentTest  {
    public static void main(String args[]) {

    }

    /**
     * 浅克隆
     */
    @Test
    public  void  test1(){
        Student stu1 = new Student();
        stu1.setNumber(12345);
        Student stu2 = (Student)stu1.clone();

        System.out.println("学生1:" + stu1.getNumber());
        System.out.println("学生2:" + stu2.getNumber());

        stu2.setNumber(54321);

        System.out.println("学生1:" + stu1.getNumber());
        System.out.println("学生2:" + stu2.getNumber());

        System.out.println(stu1 == stu2);
    }

    /**
     *
     */
    @Test
    public void test2(){
        Address addr = new Address();
        addr.setAdd("杭州市");
        Student stu1 = new Student();
        stu1.setNumber(123);
        stu1.setAddr(addr);

        Student stu2 = (Student)stu1.clone();

        System.out.println("学生1:" + stu1.getNumber() + ",地址:" + stu1.getAddr().getAdd());
        System.out.println("学生2:" + stu2.getNumber() + ",地址:" + stu2.getAddr().getAdd());

        addr.setAdd("西湖区");

        System.out.println("学生1:" + stu1.getNumber() + ",地址:" + stu1.getAddr().getAdd());
        System.out.println("学生2:" + stu2.getNumber() + ",地址:" + stu2.getAddr().getAdd());

    }
}