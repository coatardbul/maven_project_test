package baseJava.list;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class LinkedTest {

    public static void main(String[] args) {
        long beginTime=System.currentTimeMillis();
        List<String> link = new LinkedList<>();
        int num = 0;
        while (num < 10000000) {
            link.add(String.valueOf(new Random().nextInt()));
            num++;
        }
        long endTime=System.currentTimeMillis();
        System.out.println(endTime-beginTime);

    }

}
