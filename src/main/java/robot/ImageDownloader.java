package robot;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;

public class ImageDownloader {
    public static void main(String[] args) {
        try {
            String imageUrl = "https://jywg.18.cn/Login/YZM?randNum=0.16677577965132473"; // 替换为实际的图片URL
            URL url = new URL(imageUrl);

            // 打开连接
            InputStream in = url.openStream();
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            // 读取数据
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
            in.close();
            out.close();

            // 转换完成，out.toByteArray()包含图片的二进制数据
            byte[] imageBytes = out.toByteArray();

            // 对二进制数据的后续处理...
//            System.out.println("Image downloaded and converted to byte array.");


            String s = ChaoJiYing.PostPic("sxl14459048", "xp14459048", "955179", "4004", "0", imageBytes);
        System.out.println(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
