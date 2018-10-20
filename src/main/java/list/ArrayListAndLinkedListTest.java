package list;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class ArrayListAndLinkedListTest {
    @Test
    public void test(){
        List<String> link=new LinkedList<>();
        link.add("123");
        link.add("222");
        link.add("333");
        link.add("222");
        link.add("333");
        link.add("444");
        link.add("555");

        //((LinkedList<String>) link).peek();
        String s=((LinkedList<String>) link).poll();
        String r=((LinkedList<String>) link).poll();
        String b=((LinkedList<String>) link).poll();
        System.out.println(s+r+b);
        System.out.println(link);
//       int num=0;
//       while (num<10000){
//            link.add(String.valueOf(new Random().nextInt()));
//           num++;
//       }


    }
}
