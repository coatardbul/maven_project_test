package IO;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileRead {


    /**
     * 读取svn提交信息，将文件中提交的记录提取出来
     */
    @Test
    public void fileReadLineForSVNFullDate() throws IOException {
        List<String> list=new ArrayList<>();
        String filePath = "C:\\Users\\xiaolei.su\\Desktop\\dsdd.txt";
        //String[] fileType = {".xml", ".properties", ".java", ".js"};
        String[] fileType = { ".java"};
        FileReader fr = new FileReader(filePath);
        BufferedReader br = new BufferedReader(fr);
        String str = "";
        while ((str = br.readLine()) != null) {
            for (int i = 0; i < fileType.length; i++) {
                if(str.contains(fileType[i])){
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
        }
        System.out.println(list.toString());
    }
}
