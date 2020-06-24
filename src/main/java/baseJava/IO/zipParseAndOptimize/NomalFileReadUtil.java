package baseJava.IO.zipParseAndOptimize;

import java.io.*;

public class NomalFileReadUtil {
    public static void main(String[] args) throws IOException {
        InputStream in = null;
        try{
            in = new BufferedInputStream(new FileInputStream(new File("C:\\Users\\coatardbul\\Desktop\\文件路径.txt")));
            byte [] buf = new byte[1024];
            int bytesRead = in.read(buf);
            while(bytesRead != -1)
            {
                for(int i=0;i<bytesRead;i++)
                    System.out.print((char)buf[i]);
                bytesRead = in.read(buf);
            }
        }catch (IOException e)
        {
            e.printStackTrace();
        }finally{
            try{
                if(in != null){
                    in.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }

    }
}
