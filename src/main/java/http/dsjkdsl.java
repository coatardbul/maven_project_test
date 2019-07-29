package http;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class dsjkdsl {
    @Test
    public void tes() {
        int i = 9;
        System.out.println(i % 2);
        System.out.println(i / 2);

    }

    @Test
    public void test() {
        List<String> list = new ArrayList<>();

        // 主体评级
        list.add("issuerGradegradeTypeLow");
        list.add("issuerGradegradeLow");
        list.add("issuerGradegradeTypeHigh");
        list.add("issuerGradegradeHigh");
        //债项评级
        list.add("bondGradegradeTypeLow");
        list.add("bondGradegradeLow");
        list.add("bondGradegradeTypeHigh");
        list.add("bondGradegradeHigh");

        for (int i = 0; i < list.size(); i++) {

        }
    }

    private String ddddd(List<String> listDict, HashMap<String, Object> param) {

        String str="";
        int num =listDict.size();
        for (int i = 0; i < listDict.size(); i++) {
            if(i%2!=0){

                if((param.get(listDict.get(i-1))==null&& param.get(listDict.get(i))!=null)
                        ||(param.get(listDict.get(i-1))!=null&& param.get(listDict.get(i))==null)
                        ){
                    if(i/4==0){
                        str="主体评级规则不符合要求";
                    }else {
                        str="债券评级规则不符合要求";
                    }

                }
            }
        }
        return str;
    }


}
