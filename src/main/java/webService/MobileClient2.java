package webService;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class MobileClient2 {

    public static void main(String[] args) throws IOException {
        //第一步：创建服务地址，不是WSDL地址
        URL url = new URL("http://10.50.149.230:8085/services/XyInstsInfoWebServer?wsdl");
        //第二步：打开一个通向服务地址的连接
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        //第三步：设置参数
        //3.1发送方式设置：POST必须大写
        connection.setRequestMethod("POST");
        //3.2设置数据格式：content-type
        connection.setRequestProperty("content-type", "text/xml;charset=UTF-8");
        connection.setRequestProperty(" User-Agent", "Apache-HttpClient/4.1.1 (java 1.5)");
        connection.setRequestProperty("Soapaction", "");
        //3.3设置输入输出，因为默认新创建的connection没有读写权限，
        connection.setDoInput(true);
        connection.setDoOutput(true);

        //第四步：组织SOAP数据，发送请求
        String soapXML = getXML();
        OutputStream os = connection.getOutputStream();
        os.write(soapXML.getBytes());
        //第五步：接收服务端响应，打印
        int responseCode = connection.getResponseCode();
        if(200 == responseCode){//表示服务端响应成功
            InputStream is = connection.getInputStream();
            //将字节流转换为字符流
            InputStreamReader isr = new InputStreamReader(is,"utf-8");
            //使用缓存区
            BufferedReader br = new BufferedReader(isr);

            StringBuilder sb = new StringBuilder();
            String temp = null;
            while(null != (temp = br.readLine())){
                sb.append(temp);
            }
            System.out.println("ssss"+sb.toString());

            is.close();
            isr.close();
            br.close();
        }

        os.close();
    }

    //组织数据，将数据拼接一下
    public static String getXML(){
        String soapXML = "<soapenv:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:impl=\"http://impl.service.xySync.app.platform.xQuant.com\">" +
                "   <soapenv:Header/>" +
                "   <soapenv:Body>" +
                "      <impl:analysisXyInstsInfo soapenv:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\">" +
                "         <retJson xsi:type=\"xsd:string\">" +
                "         {\"payInstsInfo\":[{" +
                "\"keyCode\": \" XP2018103009261503\"," +
                "\"resultVal\": \"01\"," +
                "\"APPROVAL_LOG\":[{" +
                "\"APPROVAL_USER\":\"史震\"," +
                "\"TASK_NAME\":\"提交通用交易指令信息\"," +
                "\"APPROVAL_TIME\":\"2018-09-18 17:46:31\"," +
                "\"APPROVAL_NOTE\":\"通用交易指令ID95079操作：提交通用交易指令信息\"," +
                "\"APPROVAL_OPERATION\":\"同意\"" +
                "}]" +
                "},{" +
                "\"keyCode\": \"XP2018102919112996\"," +
                "\"resultVal\": \"01\"," +
                "\"APPROVAL_LOG\": [{" +
                "\"APPROVAL_USER\": \"史震\"," +
                "\"TASK_NAME\": \"提交通用交易指令信息\"," +
                "\"APPROVAL_TIME\": \"2018-09-18 17:46:31\"," +
                "\"APPROVAL_NOTE\": \"通用交易指令ID95079操作：提交通用交易指令信息\"," +
                "\"APPROVAL_OPERATION\":\"同意\"" +
                "},{" +
                "\"APPROVAL_USER\": \"史震\"," +
                "\"TASK_NAME\": \"交收确认\"," +
                "\"APPROVAL_TIME\": \"2018-09-18 18:01:18\"," +
                "\"APPROVAL_NOTE\": \"结算指令ID[61982]交收确认,备注:产品申购\"," +
                "\"APPROVAL_OPERATION\":\"\"" +
                "}]" +
                "}]}" +
                "" +
                "         </retJson>" +
                "      </impl:analysisXyInstsInfo>" +
                "   </soapenv:Body>" +
                "</soapenv:Envelope>";
        return soapXML;
    }
}
