package http;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.interfaces.RSAPrivateKey;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class HttpClientUtil {
    /**
     * 访问服务
     *
     * @param wsdl      wsdl地址
     * @param ns        命名空间
     * @param reqParam  参数
     * @return
     * @throws Exception
     */
    public synchronized static String accessService(String wsdl,
                                                    String reqParam) throws Exception {
        String soapResponseData = "";
        PostMethod postMethod = new PostMethod(wsdl);
        // 然后把Soap请求数据添加到PostMethod中
        byte[] b = null;
        InputStream is = null;
        try {
//            b = reqParam.getBytes();
//            is = new ByteArrayInputStream(b, 0, b.length);
//            RequestEntity re = new InputStreamRequestEntity(is, b.length, "application/x-www-form-urlencoded; charset=UTF-8");
//            postMethod.setRequestEntity(re);
//
//            List<NameValuePair> baseJava.list = new ArrayList<NameValuePair>();
//            baseJava.list.add(new BasicNameValuePair("1","2"));
//            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(baseJava.list,"UTF-8");
//            RequestEntity re1 = new StringRequestEntity("123456");
//            //postMethod.setRequestEntity(re1);
//
//            /*org.apache.commons.httpclient.NameValuePair[] aa = new org.apache.commons.httpclient.NameValuePair[3];
//            aa[0] = new org.apache.commons.httpclient.NameValuePair("1","3");
//            postMethod.setRequestBody(aa);*/
//            postMethod.setParameter("aa", "aaa");
//            HttpMethodParams params  = new HttpMethodParams();
//            params.setParameter("aa", "bb");
//            postMethod.setParams(params);
//
//            HttpClient httpClient = new HttpClient();
//            int status = httpClient.executeMethod(postMethod);
//            baseJava.String aa1 = postMethod.getResponseBodyAsString();
//            System.out.println("aa----"+aa1);
//            if (status == 200) {
//                soapResponseData =postMethod.getResponseBodyAsString();
//                System.out.println("------"+soapResponseData);
//            }else{
//                throw new Exception("基金代销接口响应状态错误，状态为："+status+",请检查");
//            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new  Exception(e.getMessage());
        } finally {
            if (is != null) {
                is.close();
            }
        }
        return soapResponseData;
    }

    public static void main(String[] args) {
        try {
            String wsdl = "http://10.50.151.64:8080/fundProxyWeb/findFundProxyData";
            String jYwsdl = "http://2z26891m45.iok.la:34715/getForApprovalDetail.action";
            String url3 = "http://localhost:8085/fundProxy/findFundProxyData.action";
            String param = "{\"userId\":\"ZXXT_TEST01\"}";
            /*//私钥
            baseJava.String privateKey   ="MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAIrS6qrgWxPTNRSjgzRgsHmr9dQQViKfodZdweeXB"
            		+ "5OAPt97lhLGQUzoqDTdIcmJUop4m5Rz/e26fZ71HMP3XSwkQ7dYIAMu+k3exRkGjz+HW/nAp5DWkTh4BVwODrZTcUBtqGOEV6nr"
            		+ "RqKa+CyIL3SYiBgrlK0ETD9k/qaF+8f/AgMBAAECgYB2O3wxXDM1h0RF95zfFxo2BgawPdmeU2Z6NA/ZNf/119u0aoU+KCulXOEl"
            		+ "ZNBJ3PK3jFC09rTlq1ch8pA1No/ark255cx6Htg0jQ/PvBbyJ8i07JY6S4BMTNfbgOCwVyoylNXx/b7T0k6/TDhYei7IfqGfr"
            		+ "/B8/U6Igow4D5KfwQJBAMvld0tFhcU0PS1p72BThIxjnVUIQQsUjV2hv3xAKOOsr11chPhD5v2HXcPC+lYzM3qHr1BauNnEgD"
            		+ "SunnpmM+0CQQCuTIbsR0gxzshGfTDcGmwPzE8dFkWyV1EcWfXDsFANM5aulUudk1URU5t08sMUj9E37uDnxjRjIT1LS9Yp18YbA"
            		+ "kBFd1BFXNJOdZXCOy1K7Clkiyu3JLDETaQ8Gjda5aEIsHSuoi7LRpcUGWJgp7USWS9Yt5GLFt2Tk9b6G+PyX4+hAkB51YBSVJlA"
            		+ "R5xyuTuX/tgeSt/aIyFgwewudyt1OB8cuzzOmAkdAklqLRjzLGX0T3sdL9sB5+fiiu9/XGk9IliVAkEAmwONtEUzDXLJVcB0O"
            		+ "IBCaposMh/eU9G0FpOKmUPK//iopzCYlKxfqPQr5v8WZUiHn834mc9jIVHW7hiFZdGuJg==";
            RSAPrivateKey  rSAPrivateKey =  RSAEncrypt.loadPrivateKeyByStr(privateKey);
            byte[] bytes = RSAEncrypt.encryptByPrivate(rSAPrivateKey, param.getBytes( StandardCharsets.UTF_8 ));
            */
            String response = accessService(jYwsdl,param  );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
