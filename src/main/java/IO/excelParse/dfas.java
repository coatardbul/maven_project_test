/**
 * Copyright  2019-2029 联通集团财务有限公司版权所有。
 */
package IO.excelParse;

import entity.ImportCrmProduct;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: suxiaolei
 * @date: 2019/7/24
 */
public class dfas {


    @Test
    public void ddddd() throws IOException, InvalidFormatException {
        File file = new File("C:\\Users\\coatardbul\\Desktop\\cc.csv");
        // File file=new File("C:\\Users\\coatardbul\\Desktop\\xx.xlsx");
        FileInputStream inputStream = new FileInputStream(file);
        InputStreamReader reader = new InputStreamReader(inputStream, "GBK");
        BufferedReader br = new BufferedReader(reader);

        List<String> list = new ArrayList<>();
        String line = "";
        String everyLine = "";
        while ((line = br.readLine()) != null) // 读取到的内容给line变量
        {
            everyLine = line;
            System.out.println(everyLine);
            String[] tjns = line.split("TJN");
            System.out.println(Arrays.toString(tjns));
            list.add(everyLine);
        }
        br.close();
    }


    @Test
    public void sdfd(){
        File file = new File("C:\\Users\\coatardbul\\Desktop\\cc.csv");
        final Charset charset = Charset.forName("GBK");;
        //final CSVFormat format = CSVFormat.DEFAULT.withHeader("A  ", "B", "C", "D");

        final CSVFormat format = CSVFormat.EXCEL.withHeader();
        try(final CSVParser parser = CSVParser.parse(file, charset, format)) {
            for (final CSVRecord csvRecord : parser) {
                Assert.assertNotNull(csvRecord);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void ddfds(){
        ImportCrmProduct importCrmProduct=new ImportCrmProduct();
        String str=null;
        importCrmProduct.setAppType(str);
        importCrmProduct.setPrintAppYes("dsfasfsda");
    }
}
