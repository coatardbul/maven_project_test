/**
 * Copyright  2019-2029 联通集团财务有限公司版权所有。
 */
package IO.excelParse;

import entity.ImportCrmProduct;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.input.BOMInputStream;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author: suxiaolei
 * @date: 2019/7/24
 */
public class dfas {


    @Test
    public void ddddd() throws IOException, InvalidFormatException {
        File file = new File("C:\\Users\\coatardbul\\Desktop\\qqq.csv");
        // File file=new File("C:\\Users\\coatardbul\\Desktop\\xx.xlsx");
        FileInputStream inputStream = new FileInputStream(file);
        InputStreamReader reader = new InputStreamReader(inputStream);
        BufferedReader br = new BufferedReader(reader);
        final CSVFormat format = CSVFormat.DEFAULT.withHeader();
        CSVParser csvRecords = new CSVParser(reader, format);
        // 获取从csv文件中解析的信息
        List<BedcBankbranch> list = new ArrayList<>();
        // 获取csv文件第一行的信息
        Map<String, Integer> headerMap = csvRecords.getHeaderMap();
        BedcBankbranch bedcBankbranch = new BedcBankbranch();
        for (final CSVRecord csvRecord : csvRecords) {
            bedcBankbranch = new BedcBankbranch();
            // TODO 根据具体的业务操作
            bedcBankbranch.setBranchcode(csvRecord.get("??"));
            list.add(bedcBankbranch);
        }

        br.close();
    }

    @Test
    public void testBOMInputStream_ParserWithInputStream() throws IOException {

        try (final BOMInputStream inputStream = createBOMInputStream();
             final CSVParser parser = CSVParser.parse(inputStream, StandardCharsets.UTF_8, CSVFormat.DEFAULT.withHeader("a", "b"));
        ) {
            for (final CSVRecord record : parser) {
                System.out.println(record);

            }
        }
    }

    private BOMInputStream createBOMInputStream() throws IOException {
        File file = new File("C:\\Users\\coatardbul\\Desktop\\bom.csv");
        // File file=new File("C:\\Users\\coatardbul\\Desktop\\xx.xlsx");
        FileInputStream inputStream = new FileInputStream(file);

        return new BOMInputStream(inputStream);
    }

    @Test
    public void sdfd() {
        File file = new File("C:\\Users\\coatardbul\\Desktop\\cc.csv");
        final Charset charset = Charset.forName("GBK");
        ;
        //final CSVFormat format = CSVFormat.DEFAULT.withHeader("A  ", "B", "C", "D");

        final CSVFormat format = CSVFormat.EXCEL.withHeader();
        try (final CSVParser parser = CSVParser.parse(file, charset, format)) {
            for (final CSVRecord csvRecord : parser) {
                Assert.assertNotNull(csvRecord);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void ddfds() {
        ImportCrmProduct importCrmProduct = new ImportCrmProduct();
        String str = null;
        importCrmProduct.setAppType(str);
        importCrmProduct.setPrintAppYes("dsfasfsda");
    }
}
