package jvm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HeepOOM {
    private int num=1;
    static class OOMObject{

    }
    public void stackLeak(){
        num++;
        stackLeak();

    }
    public static void main(String[] args) {
       // -Xms20m -Xmx20m
//        List<OOMObject> baseJava.list=new ArrayList<>();
//        while (true){
//            baseJava.list.add(new OOMObject());
//        }

        HeepOOM heepOOM=new HeepOOM();
        heepOOM.stackLeak();
    }

}
