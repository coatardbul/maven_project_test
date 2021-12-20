package baseJava.util;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * <p>
 * Note:
 * <p>
 * Date: 2021/11/10
 *
 * @author Su Xiaolei
 */
public class test {

    public static void main(String[] args) throws InterruptedException, IOException, URISyntaxException {

        String path="https://api.weixin.qq.com/wxa/generatescheme?access_token=51_NnIXmel8mujHvnot6Ch-VS0JC2_MCVQGmPob3Vu4qcMWYyuaDwMcZyALmEJS7fwH_X5MHriJe6o53BsVelE8KkYtOB6ua8bsT7T8Ud1mOno02TdpY4RJiHCR5frNYpJ_6tvkUc7UFOvd05_FKVYdAEAPAD";
        String json="\n" +
                "{\n" +
                "    \"jump_wxa\":\n" +
                "    {\n" +
                "        \"path\": \"/pages/home/index\",\n" +
                "        \"query\": \"userd=123\"\n" +
                "    }\n" +
                "}";

        InputStream inputStream = null;
        OutputStream outStream = null;
        HttpURLConnection conn = null;
        try {
            byte[] entity = json.getBytes();
            conn = (HttpURLConnection) new URL(path).openConnection();
            conn.setConnectTimeout(1000);// 设置超时
            conn.setRequestMethod("POST");
            // 允许对外输出数据
            conn.setDoOutput(true);
            // 设定传送的内容类型是可序列化的java对象
            // (如果不设此项,在传送序列化对象时,当WEB服务默认的不是这种类型时可能抛java.io.EOFException)
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Content-Length", String.valueOf(entity.length));
            conn.setRequestProperty("AppKey", "3");
            conn.setRequestProperty("Connection", "Keep-Alive");
            outStream = conn.getOutputStream();
            outStream.write(entity);
            if (conn.getResponseCode() == 200) {
                inputStream = conn.getInputStream();
                byte[] dateStream = readStream(inputStream);
                System.out.println(new String(dateStream));
                new String(dateStream);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (outStream != null) {
                outStream.close();
            }
            if (conn != null) {
                conn.disconnect();
            }
        }



    }


    public static byte[] readStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
        byte[] buffer = new byte[2048];
        int len = -1;
        while ((len = inStream.read(buffer)) != -1) {
            outSteam.write(buffer, 0, len);
        }
        outSteam.close();
        inStream.close();
        return outSteam.toByteArray();
    }

    public static void downUrlTxt(String fileName, String fileUrl, String downPath) throws IOException {
        String sss = "http://aladdin.zj.chinamobile.com/aiStatic/taview/2021/11/27/df6cd2e5a0e54cb2b1f4a642cb9f779e.png";

        File file=null;
        try {
             file = new File("12312321");//创建新文件

//            if (file != null && !file.exists()) {
//                file.createNewFile();
//
//            }

            OutputStream oputstream = new FileOutputStream(file);

            URL url = new URL(sss);

            HttpURLConnection uc = (HttpURLConnection) url.openConnection();

            uc.setDoInput(true);//设置是否要从 URL 连接读取数据,默认为true

            uc.connect();


            InputStream iputstream = uc.getInputStream();

            System.out.println("file size is:" + uc.getContentLength());//打印文件长度

            byte[] buffer = new byte[4 * 1024];

            int byteRead = -1;

            while ((byteRead = (iputstream.read(buffer))) != -1) {
                oputstream.write(buffer, 0, byteRead);

            }

            oputstream.flush();

            iputstream.close();

            oputstream.close();

        } catch (Exception e) {

        }

        String encodeImage = Base64.getEncoder().encodeToString(Files.readAllBytes(file.toPath()));
        System.out.println(encodeImage);


    }

    public static String getFilterUrl(String str) {
        if (!StringUtils.isNotBlank(str)) {
            return str;
        }
        int i = str.indexOf("https:");
        if (i == -1) {
            return str;
        }
        str.substring(i, str.length());
        int j = str.substring(i, str.length()).indexOf(" ");
        if (j == -1) {
            return str.substring(i, str.length()).trim();
        }
        return str.substring(i, i + j).trim();

    }

}
