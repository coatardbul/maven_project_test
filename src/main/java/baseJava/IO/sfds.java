package baseJava.IO;

import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class sfds {
    public static void main(String[] args) throws Exception {
        File file2 = new File("/Users/coatardbul/Desktop/22-221.jpeg");

        File file1 = new File("/Users/coatardbul/Desktop/22-221-1.jpeg");
        for (int i = 0; i < 12345; i++) {
            FileUtils.copyFile(file2,file1);
            File file = new File("/Users/coatardbul/Desktop/abc2.jpg");
//            if(i%2==1){
                System.out.println(DateFormatUtils.format(new Date(),"yyyy-MM-dd HH:mm:ss.SSS"));
                long start=System.currentTimeMillis();
                System.out.println("原来文件大小"+file1.length()/1024);
                BufferedImage originalImage = ImageIO.read(file1);
                Thumbnails.of(originalImage).scale(0.75).toFile(file);
                System.out.println("新文件大小"+file.length()/1024);
                System.out.println("google时间"+(System.currentTimeMillis()-start));
                Thread.sleep(6000);

//            }else {
//                System.out.println(DateFormatUtils.format(new Date(),"yyyy-MM-dd HH:mm:ss.SSS"));
//                System.out.println("原来文件大小"+file1.length()/1024);
//                long start=System.currentTimeMillis();
//                file = ImgUtil.compressImg(file1, 1024 * 2);
//                System.out.println("新文件大小"+file.length()/1024);
//                System.out.println("taview时间"+(System.currentTimeMillis()-start));
//                Thread.sleep(6000);
//            }


        }


    }

    private void persistImage(BufferedImage image, int width, int height, File output) throws IOException {
        System.out.println(output);
        output.getParentFile().mkdirs();
        ImageIO.write(Thumbnails.of(image).size(width, height).asBufferedImage(), FilenameUtils.getExtension(output.getAbsolutePath()), output);
    }

    public static String covStringToStringTwo(String sDate) {
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = dateFormat1.parse(sDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String newDate = dateFormat.format(date);
        return newDate;
    }
}
