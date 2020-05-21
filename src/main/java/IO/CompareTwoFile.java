package IO;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CompareTwoFile {

    public static void main(String[] args) throws IOException {
//        FileInputStream f=new FileInputStream(new File("C:\\Users\\coatardbul\\Desktop\\新建文本文档.txt"));
//
//        FileOutputStream ff=new FileOutputStream(new File("C:\\Users\\coatardbul\\Desktop\\新建文本文档.txt"));
//        FileReader f1=new FileReader(new File("C:\\Users\\coatardbul\\Desktop\\新建文本文档.txt"));


        List<String> arrayList = new ArrayList<>();
        FileReader fr = new FileReader(new File("C:\\Users\\coatardbul\\Desktop\\ss.txt"));
        BufferedReader bf = new BufferedReader(fr);
        String str;
        // 按行读取字符串
        while ((str = bf.readLine()) != null) {
            arrayList.add(str);
        }
        bf.close();
        fr.close();
        System.out.println(arrayList);
        System.out.println(arrayList.size());

        List<String> arrayList1 = new ArrayList<>();
        FileReader fr1 = new FileReader(new File("C:\\Users\\coatardbul\\Desktop\\tt.txt"));
        BufferedReader bf1 = new BufferedReader(fr1);
        String str1;
        // 按行读取字符串
        while ((str1 = bf1.readLine()) != null) {
            if(arrayList1.contains(str1)){
                System.out.println("$$$$$$$$$"+str1);
            }
            arrayList1.add(str1);
        }
        bf1.close();
        fr1.close();
        System.out.println(arrayList1);
        System.out.println(arrayList1.size());

        for(String ss:arrayList){
            if(!arrayList1.contains(ss)){
                System.out.println("#######"+ss);
            }
        }

        arrayList.removeAll(arrayList1);

        System.out.println(arrayList);



    }
}
