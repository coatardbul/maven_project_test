package list;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class ArrayListTest {
    public static void main(String[] args) {

        List<String> link = new ArrayList<>();
        link.add("111");
        link.add("222");
        link.add("333");
        link.add("444");
        link.add("555");
        link.add("666");
        link.add("777");
        link.add("888");

        List<String> linkadd = new ArrayList<>();
        linkadd.add("111");
        linkadd.add("222");
        linkadd.add("333");
        linkadd.add("444");
        linkadd.add("555");


        link.addAll(linkadd);
        for(int i=0;i<link.size();i++){
            System.out.println(link.get(i));
        }



    }
}
