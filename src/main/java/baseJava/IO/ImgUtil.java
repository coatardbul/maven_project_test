package baseJava.IO;


import lombok.extern.slf4j.Slf4j;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * @Author: yuansc
 * @Date: 2020/10/20 14:06
 * @Description: *
 */
public class ImgUtil {

    private static String localStr = "/app/ilsp/taview-api/app/imgs/";

    public static void imageResize(File originalFile, File resizedFile,
                                   int maxWidth, int maxHeight, float quality) {

        if (quality > 1) {
            throw new IllegalArgumentException(
                    "图片质量需设置在0.1-1范围");
        }
        try {
            ImageIcon ii = new ImageIcon(originalFile.getCanonicalPath());
            Image i = ii.getImage();
            Image resizedImage = null;

            int iWidth = i.getWidth(null);
            int iHeight = i.getHeight(null);

            int newWidth = maxWidth;
            if (iWidth < maxWidth) {
                newWidth = iWidth;
            }


            if (iWidth >= iHeight) {
                resizedImage = i.getScaledInstance(newWidth, (newWidth * iHeight)
                        / iWidth, Image.SCALE_SMOOTH);
            }


            int newHeight = maxHeight;
            if (iHeight < maxHeight) {
                newHeight = iHeight;
            }

            if (resizedImage == null && iHeight >= iWidth) {
                resizedImage = i.getScaledInstance((newHeight * iWidth) / iHeight,
                        newHeight, Image.SCALE_SMOOTH);
            }

            //此代码确保加载图像中的所有像素
            Image temp = new ImageIcon(resizedImage).getImage();

            //创建缓冲图像
            BufferedImage bufferedImage = new BufferedImage(temp.getWidth(null),
                    temp.getHeight(null), BufferedImage.TYPE_INT_RGB);
            //将图像复制到缓冲图像
            Graphics g = bufferedImage.createGraphics();
            //清除背景并绘制图像。
            g.setColor(Color.white);
            g.fillRect(0, 0, temp.getWidth(null), temp.getHeight(null));
            g.drawImage(temp, 0, 0, null);
            g.dispose();

            float softenFactor = 0.05f;
            float[] softenArray = {0, softenFactor, 0, softenFactor,
                    1 - (softenFactor * 4), softenFactor, 0, softenFactor, 0};
            Kernel kernel = new Kernel(3, 3, softenArray);
            ConvolveOp cOp = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);
            bufferedImage = cOp.filter(bufferedImage, null);
            //将jpeg写入文件
            FileOutputStream out = new FileOutputStream(resizedFile);
            //将图像编码为jpeg数据流
            ImageIO.write(bufferedImage, "jpeg", out);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    // 将图片压缩到5k及以下
    public static void imgToFiveK(String fileName, String targetFileName) {
        for (int i = 0; i < 10; i++) {
            BufferedImage sourceImg = null;
            // 当前图片大小，单位为K
            File customaryFile = new File(localStr + fileName);
            double storage = 0;
            BufferedImage bi = null;
            ColorModel color = null;
            try {
                ImageInputStream iis = ImageIO.createImageInputStream(customaryFile);
                storage = iis.length() / 1024;
                if (storage < 5) {
                    break;
                }
                bi = ImageIO.read(customaryFile);
                color = bi.getColorModel();

            } catch (IOException e) {
                e.printStackTrace();
            }
            // width和height需要压缩比例
            double need = Math.sqrt(storage / 5 / color.getPixelSize() * 8);
            // 需要压缩成的width和height
            int widthNew = new Double(bi.getWidth() / need).intValue();
            int heightNew = new Double(bi.getHeight() / need).intValue();
            File compressAfter = new File(localStr + targetFileName);
            imageResize(customaryFile, compressAfter, widthNew, heightNew, bi.getAccelerationPriority());
        }
    }


    // 下载图片到本地
    public static void download(String urlString, String fileName) throws Exception {
        // 构造URL
        URL url = new URL(urlString);

        // 打开连接
        URLConnection con = url.openConnection();
        con.setConnectTimeout(1000);
        HttpURLConnection httpconn = (HttpURLConnection) url.openConnection();
        httpconn.setConnectTimeout(1000);
        // 输入流
        InputStream is = con.getInputStream();
        // 1K的数据缓冲
        byte[] bs = new byte[1024];
        // 读取到的数据长度
        int len;
        // 输出的文件流
        File file = new File(localStr + fileName);
        FileOutputStream os = new FileOutputStream(file, true);
        // 开始读取
        while ((len = is.read(bs)) != -1) {
            os.write(bs, 0, len);
        }
        // 完毕，关闭所有链接
        os.close();
        is.close();
    }

    /**
     * 将网络图片转换base64编码
     *
     * @param netImagePath 网络图片路径
     * @return 编码后的图片
     */
    public static String NetImageToBase64(String netImagePath) throws IOException {
        ByteArrayOutputStream data = new ByteArrayOutputStream();
        String strNetImageToBase64 = "";

        // 创建URL
        URL url = new URL(netImagePath);
        final byte[] by = new byte[1024];
        // 创建链接
        final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setConnectTimeout(5000);
        InputStream is = conn.getInputStream();
        // 将内容读取内存中
        int len = -1;
        while ((len = is.read(by)) != -1) {
            data.write(by, 0, len);
        }
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        strNetImageToBase64 = encoder.encode(data.toByteArray());
//        log.info("网络图片转换Base64:" + strNetImageToBase64);
        // 关闭流
        is.close();
        return strNetImageToBase64;
    }

    /**
     * 压缩图片
     *
     * @param customaryFile 图片文件
     * @param size          压缩大小 单位为KB
     * @return 返回压缩后的文件
     */
    public static File compressImg(File customaryFile, double size) {
        for (int i = 0; i < 10; i++) {
            double storage = 0;
            BufferedImage bi = null;
            ColorModel color = null;
            try {
                ImageInputStream iis = ImageIO.createImageInputStream(customaryFile);
                storage = iis.length() / 1024;
                if (storage < size) {
                    break;
                }
                bi = ImageIO.read(customaryFile);
                color = bi.getColorModel();

            } catch (IOException e) {
                e.printStackTrace();
            }
            // width和height需要压缩比例
            double need = 1.0;
            // 需要压缩成的width和height
            int widthNew = new Double(bi.getWidth() / need).intValue();
            int heightNew = new Double(bi.getHeight() / need).intValue();
            File compressAfter = new File(customaryFile.getAbsolutePath());
            imageResize(customaryFile, compressAfter, widthNew, heightNew, bi.getAccelerationPriority());
        }
        return new File(customaryFile.getAbsolutePath());
    }

}
