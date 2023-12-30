package robot;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.File;

/**
 * @Author zhoujing
 * @Date 2023/11/17 15:53
 * @Description resumesdk简历解析工具包
 **/
@Slf4j
public class ParserUtil {

    public static void main(String[] args) throws Exception {
        parser("/Users/coatardbul/Desktop/学位证.jpg","学位证.jpg");
    }
    static String url = "http://resumesdk.market.alicloudapi.com/ResumeParser";
//    String fname = "D:\\pdf\\imgToPdf\\钻探技术岗_王能_国聘.pdf";  //替换为您的文件名
    static String appcode = "e117789ed55342428c6ee49e847f7fac";
    public static String parser(String path,String fname) throws Exception {
        // 设置头字段
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Authorization", "APPCODE " + appcode);
        httpPost.addHeader("Content-Type", "application/json; charset=UTF-8");
        httpPost.addHeader("Content-Type", "application/json");

        // 读取简历内容
        byte[] bytes = org.apache.commons.io.FileUtils.readFileToByteArray(new File(path));
        String data = new String(Base64.encodeBase64(bytes), Consts.UTF_8);

        // 设置内容信息
        JSONObject json = new JSONObject();
        json.put("file_name", fname);	// 文件名
        json.put("file_cont", data);	// 经base64编码过的文件内容
        json.put("need_avatar", 0);		// 是否需要解析头像 0不需要 1需要
        json.put("need_social_exp",0);  // 是否需要解析社会实践经验 0不需要 1需要
        json.put("ocr_type", 1);		// 1为高级ocr
        StringEntity params = new StringEntity(json.toString(), Consts.UTF_8);
        httpPost.setEntity(params);

        // 发送请求
        HttpClient httpclient = new DefaultHttpClient();
        HttpResponse response = httpclient.execute(httpPost);

        // 处理返回结果
        String resCont = EntityUtils.toString(response.getEntity(), Consts.UTF_8);

        JSONObject res = new JSONObject(resCont);
        System.out.println(res.toString(4));

        return null;
    }
}
