package list;

import entity.ImportCrmProduct;
import entity.UserIp;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.*;

public class ListTest {


    @Test
    public void test1() {
        List list = new ArrayList();
        list.add("111");
        list.add("222");
        list.add("333");
        List list1 = new ArrayList();
        list1.add("444");

list.retainAll(list1);
System.out.println(list.size());
        for (int i = 0; i < list.size(); i++) {
            String name = (String) list.get(i); // 1
            System.out.println("name:" + name);
        }
    }

    @Test
    public void test3() {
        List<String> list = new ArrayList();
        list.add("1111");
        list.add("2222");
        list.add("3333");
        list.add("4444");
        list.add("5555");
        list.add("6666");

        System.out.println(list.subList(0, 3));
    }

    @Test
    public void test4() {
        List<String> list = new ArrayList();
        list.add("1111");
        List list1 = list;
        List<Integer> listTemp = new ArrayList();
        listTemp.add(111);
        List list2 = listTemp;
        Map<String, List<Object>> m = new HashMap<>();
        m.put("11", list1);
        m.put("22", list2);
        System.out.println(m.toString());

    }

    @Test
    public void test5() {
        List<ImportCrmProduct> list1 = new ArrayList<>();
        ImportCrmProduct i = new ImportCrmProduct();
        i.setAppType("123");
        list1.add(i);
        List<UserIp> list2 = new ArrayList<>();
        UserIp u = new UserIp();
        u.setCreateUser("123");
        list2.add(u);
        Map<String, List> m = new HashMap<>();
        m.put("im", list1);
        m.put("user", list2);
        if (m.get("im").get(0) instanceof ImportCrmProduct) {
            System.out.println("111");
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

    @Test
    public void test6() {
        List<List<Integer>> batchList = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add( 1111);
        }
        for (int i = 0; i < 100; i++) {
            list.add( 222);
        }
        for (int i = 0; i < 23; i++) {
            list.add( 333);
        }

        //System.out.println(list.toString());
        batchList=getBatch(list);
        System.out.println(batchList.size());
        System.out.println(batchList.toString());
    }

    public <T> List<List<T>> getBatch(List<T> list) {
        List<List<T>> resultList = new ArrayList<>();
        int num = new BigDecimal(list.size()).divide(new BigDecimal(100)).intValue();
        int remainderNum = list.size() % 100;
        if (remainderNum == 0) {
            for (int i = 0; i < num; i++) {
                resultList.add(list.subList(i, (i + 1) * 100));
            }
            return resultList;
        } else {
            if (num == 0) {
                resultList.add(list);
                return resultList;
            } else {
                for (int i = 0; i < num ; i++) {
                    resultList.add(list.subList(i, (i + 1) * 100));
                }
                resultList.add(list.subList(num  * 100, list.size()));
                return resultList;
            }
        }
    }

    public void getData(Box<? extends Number> data) {
        System.out.println("data :" + data.getData());
    }
    @Test
    public void hashCodeTests(){
        UserIp u1=new UserIp();
        u1.setIp("123");
      u1.setCreateUser("sdfs");
        UserIp u2=new UserIp();
        u2.setIp("123");
        System.out.println(u1.hashCode()+"###"+u2.hashCode());
    }

    public <T> T ss(Class<T> c) {
        T t = null;
        try {
            t = c.newInstance();

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return t;
    }


}
