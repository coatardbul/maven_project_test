package webService;


import java.nio.charset.Charset;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;


public  class HttpClientCallSoapUtil {
    static int socketTimeout = 30000;// 请求超时时间
    static int connectTimeout = 30000;// 传输超时时间


    /**
     * 使用SOAP1.1发送消息
     *
     * @param postUrl
     * @param soapXml
     * @param soapAction
     * @return
     */
    public static String doPostSoap1_1(String postUrl, String soapXml,
                                       String soapAction) {
        String retStr = "";
        // 创建HttpClientBuilder
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        // HttpClient
        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
        HttpPost httpPost = new HttpPost(postUrl);
        //  设置请求和传输超时时间
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(socketTimeout)
                .setConnectTimeout(connectTimeout).build();
        httpPost.setConfig(requestConfig);
        try {
            httpPost.setHeader("Content-Type", "text/xml;charset=UTF-8");
            httpPost.setHeader("SOAPAction", soapAction);
            StringEntity data = new StringEntity(soapXml,
                    Charset.forName("UTF-8"));
            httpPost.setEntity(data);
            CloseableHttpResponse response = closeableHttpClient
                    .execute(httpPost);
            HttpEntity httpEntity = response.getEntity();
            if (httpEntity != null) {
                // 打印响应内容
                retStr = EntityUtils.toString(httpEntity, "UTF-8");
                System.out.print("response:" + retStr);
            }
            // 释放资源
            closeableHttpClient.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retStr;
    }

    /**
     * 使用SOAP1.2发送消息
     *
     * @param postUrl
     * @param soapXml
     * @param soapAction
     * @return
     */
    public static String doPostSoap1_2(String postUrl, String soapXml,
                                       String soapAction) {
        String retStr = "";
        // 创建HttpClientBuilder
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        // HttpClient
        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
        HttpPost httpPost = new HttpPost(postUrl);
        // 设置请求和传输超时时间
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(socketTimeout)
                .setConnectTimeout(connectTimeout).build();
        httpPost.setConfig(requestConfig);
        try {
            httpPost.setHeader("Content-Type",
                    "application/soap+xml;charset=UTF-8");
            httpPost.setHeader("SOAPAction", soapAction);
            StringEntity data = new StringEntity(soapXml,
                    Charset.forName("UTF-8"));
            httpPost.setEntity(data);
            CloseableHttpResponse response = closeableHttpClient
                    .execute(httpPost);
            HttpEntity httpEntity = response.getEntity();
            if (httpEntity != null) {
                // 打印响应内容
                retStr = EntityUtils.toString(httpEntity, "UTF-8");
                System.out.print("response:" + retStr);
            }
            // 释放资源
            closeableHttpClient.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retStr;
    }

    public static void main(String[] args) {
        String orderSoapXml = "<soapenv:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:impl=\"http://impl.service.xySync.app.platform.xQuant.com\"> " +
                "   <soapenv:Header/> " +
                "   <soapenv:Body> " +
                "      <impl:analysisXyInstsInfo soapenv:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\"> " +
                "         <retJson xsi:type=\"xsd:string\"> " +
                "         {\"payInstsInfo\":[{ " +
                "  \"keyCode\": \" XP2018103009261503\", " +
                "  \"resultVal\": \"01\", " +
                "  \"APPROVAL_LOG\":[{ " +
                "   \"APPROVAL_USER\":\"史震\", " +
                "   \"TASK_NAME\":\"提交通用交易指令信息\", " +
                "   \"APPROVAL_TIME\":\"2018-09-18 17:46:31\", " +
                "   \"APPROVAL_NOTE\":\"通用交易指令ID95079操作：提交通用交易指令信息\", " +
                "   \"APPROVAL_OPERATION\":\"同意\" " +
                "   }]  " +
                "},{ " +
                "  \"keyCode\": \"XP2018102919112996\", " +
                "  \"resultVal\": \"01\", " +
                "  \"APPROVAL_LOG\": [{ " +
                "   \"APPROVAL_USER\": \"史震\", " +
                "   \"TASK_NAME\": \"提交通用交易指令信息\", " +
                "   \"APPROVAL_TIME\": \"2018-09-18 17:46:31\", " +
                "   \"APPROVAL_NOTE\": \"通用交易指令ID95079操作：提交通用交易指令信息\", " +
                "   \"APPROVAL_OPERATION\":\"同意\" " +
                "  },{ " +
                "   \"APPROVAL_USER\": \"史震\", " +
                "   \"TASK_NAME\": \"交收确认\", " +
                "   \"APPROVAL_TIME\": \"2018-09-18 18:01:18\", " +
                "   \"APPROVAL_NOTE\": \"结算指令ID[61982]交收确认,备注:产品申购\", " +
                "   \"APPROVAL_OPERATION\":\"\" " +
                "  }] " +
                "}]} " +
                " " +
                "         </retJson> " +
                "      </impl:analysisXyInstsInfo> " +
                "   </soapenv:Body> " +
                "</soapenv:Envelope>";
        String querySoapXml = "<?xml version = \"1.0\" ?>"
                + "<soapenv:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:web=\"http://webservices.b.com\">"
                + "   <soapenv:Header/>"
                + "   <soapenv:Body>"
                + "      <web:query soapenv:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\">"
                + "         <in0 xsi:type=\"web:QueryRequest\">"
                + "            <endTime xsi:type=\"xsd:dateTime\">?</endTime>"
                + "            <mobile xsi:type=\"soapenc:string\" xmlns:soapenc=\"http://schemas.xmlsoap.org/soap/encoding/\">?</mobile>"
                + "            <startTime xsi:type=\"xsd:dateTime\">?</startTime>"
                + "         </in0>" + "      </web:query>"
                + "   </soapenv:Body>" + "</soapenv:Envelope>";
        String postUrl = "http://10.50.149.230:8085/services/XyInstsInfoWebServer?wsdl";
        //采用SOAP1.1调用服务端，这种方式能调用服务端为soap1.1和soap1.2的服务
        doPostSoap1_1(postUrl, orderSoapXml, "");
      //  doPostSoap1_1(postUrl, querySoapXml, "");

        //采用SOAP1.2调用服务端，这种方式只能调用服务端为soap1.2的服务
        //doPostSoap1_2(postUrl, orderSoapXml, "order");
        //doPostSoap1_2(postUrl, querySoapXml, "query");
    }

}