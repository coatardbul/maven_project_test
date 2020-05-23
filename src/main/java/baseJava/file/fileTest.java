package baseJava.file;

import org.junit.Test;

import java.io.*;

public class fileTest {


    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {

        String path = "C:\\Users\\xiaolei.su\\Desktop";//所创建文件的路径

        File f = new File(path);

        if (!f.exists()) {

            f.mkdirs();//创建目录
        }

        String fileName = "hello.pdf";//文件名及类型

        File file = new File(path, fileName);

        if (!file.exists()) {

            try {
                file.createNewFile();
            } catch (IOException e) {

                e.printStackTrace();
            }

        }

    }

    @Test
    public void get() {
        String path = "D:\\workspace\\idea\\maven_project_test\\src\\main\\java\\leetCode";//所创建文件的路径

        for (int i = 1; i < 50; i++) {
            File f = new File(path + "\\num" + i);
            if (!f.exists()) {

                f.mkdirs();//创建目录
            }
        }


    }

}
