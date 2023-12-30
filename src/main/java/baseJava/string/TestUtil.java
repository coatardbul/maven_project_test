package baseJava.string;

import com.alibaba.fastjson.JSON;
import common.entity.UserIp;
import org.apache.logging.log4j.core.util.JsonUtils;
import org.bytedeco.javacpp.presets.opencv_core;
import org.json.JSONArray;
import org.junit.Test;

import java.util.*;

public class TestUtil {

    @Test
    public void test1() {
        ArrayList obj = new ArrayList<>();
        if(obj instanceof List){
            System.out.println(obj);
        }




    }

    @Test
    public void change() {

        String YEAR_MONTH_DAY = "[0-9]{4}[\u4E00-\u9FA5]{1}[0-9]{2}[\u4E00-\u9FA5]{1}[0-9]{2}[\u4E00-\u9FA5]{1}";

        String sb = "2022月12月21日";
        String str = "[0-9]{4}[年]{1}[0-9]{2}[月]{1}[0-9]{2}[日]{1}";
        System.out.println(sb.matches(str));
    }

    @Test
    public void test2() {
        String str1 = "1";
        String str2 = "1";
        String str = str1 + " " + str2;
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
    public void test4() {

        TestUtil tu = new TestUtil();
        UserIp ip = new UserIp();
        ip.setIp("ddd");
        tu.changeUserIp(ip);
        System.out.println(ip.toString());
    }

    @Test
    public void Test5() {
        UserIp ip = new UserIp();
        ip.setIp("ddd");
        changeUserIp(ip);
        System.out.println(ip.toString());

    }

    public void changeUserIp(UserIp ip) {
        ip.setCreateUser("dsfdsf");
    }

    @Test
    public void testList() {
        List<String> strList1 = new ArrayList<>();
        strList1.add("strList1 add string1");
        strList1.add("strList1 add string2");

        addElements1(strList1);

        for (String str : strList1) {
            System.out.println(str);
        }
    }

    public void addElements1(List<String> strList) {
        strList.add("strList add string1");
        strList.add("strList add string2");
    }

}
