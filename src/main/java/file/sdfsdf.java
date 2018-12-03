package file;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class sdfsdf {
    public static void main(String[] args) {
        try {
            List<String> list=new ArrayList<>();
            FileReader fr = new FileReader("C:\\Users\\xiaolei.su\\Desktop\\dsdd.txt");
            BufferedReader bf = new BufferedReader(fr);
            String str;
            // 按行读取字符串
            while ((str = bf.readLine()) != null) {
                if(str.contains(".java")|| str.contains(".js")|| str.contains(".xml")){
                    if(str.contains("/")){

                        String ss=str.substring(str.lastIndexOf("/"));
                        if(!list.contains(ss)){
                            list.add(ss);
                        }

                    }else {
                        if(!list.contains(str)){
                            list.add(str);
                        }
                    }

                }

            }
            System.out.print(list.toString());
            bf.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
