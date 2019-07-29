package dataConnection;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ColletionTest {

    @Test
    public  void removeTest(){

        List<String >list=new ArrayList<String>();
        list.add("111");
        list.add("222");
        list.add("333");
        list.add("444");
        list.add("555");
        list.add("666");

        List<String >listCopy=new ArrayList<String>();
        listCopy=list;

        List<String>list1=new ArrayList<String>();
        //list1.add("111");
        list1.add("222");
        list1.add("333");
        list1.add("444");
        list1.add("555");
        list1.add("666");
        list1.add("777");
        list.removeAll(list1);
        System.out.println(list.toString());
        System.out.println(listCopy.toString());

    }
}
