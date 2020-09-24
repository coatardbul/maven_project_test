package baseJava.IO.excelParse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ApiParse {

    private static final String inputFilePath = "D:\\download\\OneDrive\\文档\\希为项目\\风控系统";
    private static final String inputFileName = "接口文档1.2.xlsx";

    private static final String oututFilePathName = "C:\\Users\\coatardbul\\Desktop\\vvv.txt";

    public static void main(String[] args) throws IOException {
        FileWriter fw = new FileWriter(oututFilePathName);
        File excelFile = new File(inputFilePath + "/" + inputFileName);
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
            Boolean pageFlag = false;
            Boolean objectFlag = false;
            StringBuffer jsonSb = new StringBuffer();
            for (int j = 1; j < rowSize; j++) {//遍历行
                Row row = sheet.getRow(j);
                if (row == null || row.getCell(0) == null) {
                    continue;
                } else {
                    String rowBegin = row.getCell(0).getStringCellValue();
                    if (rowBegin.equals("参数")) {
                        objectFlag = true;
                        writeSeparatorInfo("^", row, fw);
                    } else if (rowBegin.equals("请求参数") || rowBegin.equals("返回参数")) {
                        objectFlag = false;
                        pageFlag = false;
                        fw.write("\n");
                        fw.write("#### " + rowBegin + "\n");
                    } else if (rowBegin.equals("请求示例") || rowBegin.equals("返回示例")) {
                        objectFlag = false;
                        pageFlag = false;
                        fw.write("\n");
                        fw.write("#### " + rowBegin + "\n");
                        fw.write(" ```\n");
                        fw.write(jsonSb.toString());
                        fw.write(" ```\n");
                        jsonSb = new StringBuffer();
                    } else if (rowBegin.matches("[—-]?[a-zA-Z]+")) {
                        appendJsonString(pageFlag, objectFlag, row, jsonSb);
                        writeSeparatorInfo("|", row, fw);
                    } else if (checkcountname(rowBegin)) {
                        if (rowBegin.contains("分页")) {
                            pageFlag = true;
                        }
                        fw.write("### " + rowBegin + "\n");
                    } else if (rowBegin.contains("/api")) {
                        fw.write("#### 接口调用请求说明 " + "\n");
                        fw.write("  * 请求路径: " + rowBegin + "\n");
                        fw.write("  * 请求方法: post" + "\n");
                        fw.write("\n");
                    }
                }
                fw.flush();
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private static void appendJsonString(Boolean pageFlag, Boolean objectFlag, Row row, StringBuffer jsonSb) {
        //分页+当前
        if (objectFlag) {
            for (int i = 0; i < 3; i++) {
                jsonSb.append("\t");
            }
            jsonSb.append("\"" + row.getCell(0).getStringCellValue() + "\": ");
            if (row.getCell(1).getStringCellValue().contains("String")) {
                if (row.getCell(3) == null) {
                    jsonSb.append("\"" + row.getCell(2) + "\"");
                } else {
                    jsonSb.append("\"" + row.getCell(3) + "\"");
                }
            } else if (row.getCell(1).getStringCellValue().contains("list")) {

            } else {
                if(row.getCell(3) == null){
                    jsonSb.append(row.getCell(2));
                }else {
                    jsonSb.append(row.getCell(3));
                }

            }
            jsonSb.append(",\n");
        }
    }

    private static void writeSeparatorInfo(String s, Row row, FileWriter fw) throws IOException {
        StringBuffer sb = new StringBuffer(s);
        for (int i = 0; i < 4; i++) {
            if (i == 3 && row.getCell(3) == null) {
                continue;
            } else if (row.getCell(i) == null) {
                sb.append(s);
            } else {
                sb.append(row.getCell(i).getStringCellValue());
                sb.append(s);
            }
        }
        fw.write(sb.toString() + "\n");
    }


    public static boolean checkcountname(String countname) {
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(countname);
        if (m.find()) {
            return true;
        }
        return false;
    }
}
