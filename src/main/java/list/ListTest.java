package list;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ListTest {


    @Test
    public void test1() {
        List list = new ArrayList();
        list.add("qqyumidi");
        list.add("corn");
        list.add(100);

        for (int i = 0; i < list.size(); i++) {
            String name = (String) list.get(i); // 1
            System.out.println("name:" + name);
        }
    }

    @Test
    public void test2() {
        Box<Number> name = new Box<Number>(99);
        Box<Integer> age = new Box<Integer>(712);

        getData(name);

        //The method getData(Box<Number>) in the type GenericTest is
        //not applicable for the arguments (Box<Integer>)
        getData(age);   // 1

    }

    public void getData(Box<? extends Number> data) {
        System.out.println("data :" + data.getData());
    }

    public<T> T ss(Class<T> c){
        T t=null;
        try {
             t=c.newInstance();

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return t;
    }


}
