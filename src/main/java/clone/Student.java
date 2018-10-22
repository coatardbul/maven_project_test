package clone;

public class Student implements Cloneable {
    private int number;
    private Address addr;

    public Address getAddr() {
        return addr;
    }

    public void setAddr(Address addr) {
        this.addr = addr;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }


    @Override
    public Object clone() {
        Student stu = null;
        try {
            stu = (Student) super.clone();   //浅复制
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        stu.addr = (Address) addr.clone();   //深度复制
        return stu;
    }
}
