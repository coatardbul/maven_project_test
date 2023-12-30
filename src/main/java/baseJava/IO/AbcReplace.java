package baseJava.IO;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: AbcReplace
 * @Description: 农行账号替换
 * @Author: LYH
 * @Date: 2019/12/3 20:36
 **/
public class AbcReplace {
  static   Map<String,String > abcAcctNoMap = new HashMap<String,String>();
    static {
        abcAcctNoMap.put("240101049999994", "11240101049999994");
        abcAcctNoMap.put("600001040015861", "81600001040015861");
        abcAcctNoMap.put("240101049999994", "11240101049999994");
        abcAcctNoMap.put("369101040020860", "50369101040020860");
        abcAcctNoMap.put("849901040010989", "50849901040010989");
        abcAcctNoMap.put("199601040001097", "50199601040001097");
        abcAcctNoMap.put("208001040013052", "50208001040013052");
        abcAcctNoMap.put("585101040015512", "50585101040015512");
        abcAcctNoMap.put("930001040037890", "50930001040037890");
        abcAcctNoMap.put("663101040005534", "50663101040005534");
        abcAcctNoMap.put("757001040010251", "50757001040010251");
        abcAcctNoMap.put("360001040011662", "50360001040011662");
        abcAcctNoMap.put("603601040009421", "50603601040009421");
        abcAcctNoMap.put("401001040010780", "50401001040010780");
        abcAcctNoMap.put("885001040007815", "50885001040007815");
        abcAcctNoMap.put("600001040012462", "81600001040012462");
        abcAcctNoMap.put("600001040011134", "81600001040011134");
        abcAcctNoMap.put("240101040007102", "11240101040007102");
        abcAcctNoMap.put("600001040009930", "81600001040009930");
    }
    public static void main(String[] args) {


        String  filePath ="/Users/coatardbul/Desktop";
        String fileName ="方差标准差.xlsx";
        File excelFile = new File(filePath+"/"+fileName);
        InputStream is = null;
        try {
            is = new FileInputStream(excelFile);
            Workbook wb = null;
            // 根据文件后缀（xls/xlsx）进行判断
            if (excelFile.getName().endsWith("xls")) {
                wb = new HSSFWorkbook(is);
            } else if (excelFile.getName().endsWith("xlsx")) {
                wb = new XSSFWorkbook(is);
            }

            //对应第几张sheet，从0开始
            Sheet sheet = wb.getSheetAt(0);
            //获得行数
            int rowSize = sheet.getLastRowNum() + 1;
            for (int j = 0; j < rowSize; j++) {
                //遍历行
                Row row = sheet.getRow(j);
                if (row == null) {
                    //略过空行
                    continue;
                }
               String  agentDraweeAcctNo = row.getCell(8).getStringCellValue();
          int s=-1;

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(is!=null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
