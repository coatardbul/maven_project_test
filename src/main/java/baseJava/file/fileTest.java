package baseJava.file;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

import java.io.*;

public class fileTest {


    public static void main(String[] args) throws Exception{

        String path = "/Users/coatardbul/Downloads/他行电子流水190813/光大—公司/光大银行201710-12.xls";
        InputStream is=          new FileInputStream(path);
        Workbook wb  = new HSSFWorkbook(is);
        Sheet sheet = wb.getSheetAt(0);
        for (int n = 16;  100+1 > n; n++) {
            Row row = sheet.getRow(n);
            Cell dc = row.getCell(1);

            System.out.println(is);

        }
        System.out.println(is);


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
