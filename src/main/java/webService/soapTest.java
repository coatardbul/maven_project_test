package webService;

import sun.net.www.protocol.http.HttpURLConnection;

import java.io.*;
import java.net.URL;

/**
 * Created by Administrator on 2018/11/6.
 */
public class soapTest {
    public static void main(String[] args) throws Exception
    {
        String urlString = "http://10.50.149.230:8085/services/XyInstsInfoWebServer";
        String xmlFile = "C:\\Users\\xiaolei.su\\Desktop\\soap.xml";// 要发送的soap格式文件
        String soapActionString = "";//Soap 1.1中使用
        URL url = new URL(urlString);
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        File fileToSend = new File(xmlFile);
        byte[] buf = new byte[(int) fileToSend.length()];// 用于存放文件数据的数组
        new FileInputStream(xmlFile).read(buf);
//		httpConn.setRequestProperty("Content-Length",
//				baseJava.String.valueOf(buf.length));//这句话可以不用写，即使是随便写
        //根据我的测试，过去的请求头中的Content-Length长度也是正确的，也就是说它会自动进行计算
        httpConn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
        httpConn.setRequestProperty("Soapaction",soapActionString);//Soap
        httpConn.setRequestMethod("POST");
        httpConn.setDoOutput(true);
        httpConn.setDoInput(true);
        httpConn.setRequestProperty("User-Agent", "Apache-HttpClient/4.1.1 (java 1.5)");
        OutputStream out = httpConn.getOutputStream();
        out.write(buf);
        out.close();
        httpConn.connect();
        InputStreamReader is = new InputStreamReader(httpConn.getInputStream(),
                "utf-8");
        BufferedReader in = new BufferedReader(is);
        String inputLine;
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("result.xml")));// 将结果存放的位置
        while ((inputLine = in.readLine()) != null) {
            System.out.println(inputLine);
            bw.write(inputLine);
            bw.newLine();
        }
        bw.close();
        in.close();
        httpConn.disconnect();
    }

}

