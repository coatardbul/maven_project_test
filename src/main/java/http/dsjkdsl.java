package http;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class dsjkdsl {
    @Test
    public void tes() {
        System.out.println(new Random(64).nextInt());

    }

    /**
     * 1为阳
     */
    @Test
    public void test() {
        for(int i=0;i<6;i++){
            System.out.println(new Random(64).nextInt());
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
