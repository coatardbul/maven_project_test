package String;

import entity.UserIp;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.*;

public class TestUtil {

    @Test
    public void test1() {
        Map<String, Integer> map = new HashMap<>();
        map.put("sb", 123);
        map.put("hello", 123);
        change(map);
        System.out.println(map);
    }

    public void change(Map<String, Integer> map) {
        //map = new HashMap<>();
        map.put("22",11);
        map.remove("hello");
        map.clear();
        //map.clear();
    }

    @Test
    public void test2() {
      String str1="1";
        String str2="1";
        String str=str1+" "+str2;
        System.out.println(str);
    }

    @Test
    public void test3() {
        List<String> list = new ArrayList<>();
        list.add("fsadf");
        list.add("sdfsd");
        changeList(list);
        System.out.println(list);

    }

    public void changeList(List<String> list) {
        list.add("hello");
    }

    @Test
    public void test4(){

        TestUtil tu=new TestUtil();
        UserIp ip=new UserIp();
        ip.setIp("ddd");
        tu.changeUserIp(ip);
        System.out.println(ip.toString());
    }
    @Test
    public void Test5(){
        UserIp ip=new UserIp();
        ip.setIp("ddd");
        changeUserIp(ip);
        System.out.println(ip.toString());

    }
    public void changeUserIp(UserIp ip){
        ip.setCreateUser("dsfdsf");
    }
    @Test
    public void testList(){
        List<String> strList1 = new ArrayList<>();
        strList1.add("strList1 add string1");
        strList1.add("strList1 add string2");

        addElements1(strList1);

        for(String str : strList1){
            System.out.println(str);
        }
    }

    public void addElements1(List<String> strList){
        strList.add("strList add string1");
        strList.add("strList add string2");
    }

}
