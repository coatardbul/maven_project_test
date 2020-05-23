package baseJava.IO;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.junit.Test;

import java.io.*;
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
        //baseJava.String[] fileType = {".xml", ".properties", ".java", ".js"};
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

    @Test
    public void kldsjf() throws IOException {
        String resourceFilePath = "C:\\Users\\coatardbul\\Desktop\\sss.txt";
        FileReader fr = new FileReader(resourceFilePath);

        String resultFilePath="C:\\Users\\coatardbul\\Desktop\\ttt.txt";
        FileWriter fw=new FileWriter(resultFilePath);


        String setString="C:\\Users\\coatardbul\\Desktop\\www.txt";
        FileWriter fw1=new FileWriter(setString);

        String setString2="C:\\Users\\coatardbul\\Desktop\\vvv.txt";
        FileWriter fw2=new FileWriter(setString2);


        BufferedReader br = new BufferedReader(fr);
        StringBuffer sb=new StringBuffer();
        String str = "";

        BodyUnit bodyUnit=new BodyUnit();
        BodyName bodyName=new BodyName();
        BodySetUtil bodySetUtil=new BodySetUtil();
        while ((str = br.readLine()) != null) {
            System.out.println(str);
            if("CNY".equals(str.trim())){
                continue;
            }
            if("baseJava/string".equals(str.trim())){
                continue;
            }
            if("Number".equals(str.trim())){
                continue;
            }
            if("abc".equals(str.trim())){
                continue;
            }
            if(str.trim().matches("[0-9]+")){
                continue;
            }
            if(str.trim().matches("^[A-Za-z_]+$")){
                //将数据写入到文件中
                fw.write(bodyUnit.toString());
                fw.flush();

                fw1.write(bodySetUtil.toString());
                fw1.flush();

                fw2.write(bodyName.toString());
                fw2.flush();


                bodyUnit=new BodyUnit();
                bodyUnit.setName(str.trim());

                bodyName=new BodyName();
                bodyName.setName(str.trim());

                bodySetUtil=new BodySetUtil();
                bodySetUtil.setName(str.trim());
                continue;
            }
            if("✔️".equals(str.trim())){
                bodyUnit.setProperty("yes");

                bodySetUtil.setProperty("yes");
                continue;
            }
            bodyUnit.setDeclare(str.trim());
            bodySetUtil.setDeclare(str.trim());
        }
        fw.write(bodyUnit.toString());
        fw.flush();

        fw1.write(bodySetUtil.toString());
        fw1.flush();

        fw2.write(bodyName.toString());
        fw2.flush();

        fr.close();
        br.close();
        fw.close();
        fw1.close();
        fw2.close();
    }

    @Test
    public  void  djflk(){
        ResponseHeadDro  responseHeadDro=new ResponseHeadDro();
        responseHeadDro.setResCd("1111");
        responseHeadDro.setResMsg("222");
        responseHeadDro.setResDt("333");
        responseHeadDro.setResTm("444");
        responseHeadDro.setResSeqNum("55");
        responseHeadDro.setReqSeqNum("666");
        ResponseDto<BodyUnit> responseDto=new ResponseDto();
        responseDto.setHead(responseHeadDro);
        BodyUnit b=new BodyUnit();
        b.setName("dsfsdf");
        b.setProperty("sdfsd");
        b.setDeclare("fasdfsd");
        responseDto.setBody(b);

        String str=JSON.toJSONString(responseDto);
        ResponseDto<BodyUnit> responseDto1=JSON.parseObject(str,  new TypeReference<ResponseDto<BodyUnit>>(){});
       // JSONObject  jsonObject=responseDto1.getBody();
        BodyUnit bodyUnit=responseDto1.getBody();
        responseDto1.toString();
    }

}
