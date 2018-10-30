package String;

import org.junit.Test;

public class TestUtil {

    @Test
    public void test1(){
        String str="sdfsdf";
        String[] strList=str.split(",");
        System.out.println(strList.length);
        System.out.println(strList[0]);
    }
}
