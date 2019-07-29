package dataConnection;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class BusinessTest {


    @Test
    public void  testSqlIn(){
        List list=new ArrayList<>();
        for(int i=0;i<3050;i++){
            list.add("sb");
        }
        String str=BusinessUtil.getSqlIn(" dateid "," and "," in ",list).toString();
        System.out.print(str);
    }
}
