package baseJava.IO.zipParseAndOptimize;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.zip.ZipFile;

public class ZipParseUtil {

    private ZipFile getZipFileByByteArraysLocal() throws IOException {
        //所创建文件的路径，将传入的流写入到文件中
        // String path = "D:" + File.separator + "var";
        String path = "C:\\Users\\coatardbul\\Desktop";
        //文件名及类型
        String fileName = "cnap.zip";
        File file = new File(path, fileName);
        ZipFile zipFile = new ZipFile(file, Charset.forName("GBK"));

        return zipFile;
    }

        public static void main(String[] args) throws IOException {
                String path = "C:\\Users\\coatardbul\\Desktop";
                //文件名及类型
                String fileName = "cnap.zip";
                File file = new File(path, fileName);
                FileInputStream fi=new FileInputStream(file);

                String path1 = "C:\\Users\\coatardbul\\Desktop\\cnap.zip";
                FileOutputStream fo=new FileOutputStream(new File(path1));
                //获得传输通道channel
                FileChannel inChannel=fi.getChannel();
                FileChannel outChannel=fo.getChannel();
                //获得容器buffer
                ByteBuffer buffer= ByteBuffer.allocate(1024);
                while(true){
                        //判断是否读完文件
                        int eof =inChannel.read(buffer);
                        if(eof==-1){
                                break;
                        }
                        //重设一下buffer的position=0，limit=position
                        buffer.flip();
                        //开始写
                        outChannel.write(buffer);
                        //写完要重置buffer，重设position=0,limit=capacity
                        buffer.clear();
                }
                inChannel.close();
                outChannel.close();
                fi.close();
                fo.close();
        }
}
