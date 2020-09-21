package network;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;

import java.io.InputStream;


public class HttpClientUtil {
    /**
     * 访问服务
     *
     * @param wsdl     wsdl地址
     * @param ns       命名空间
     * @param reqParam 参数
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
            throw new Exception(e.getMessage());
        } finally {
            if (is != null) {
                is.close();
            }
        }
        return soapResponseData;
    }

    public static void main(String[] args) {
        try {
            HttpClient httpClient = new HttpClient();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
