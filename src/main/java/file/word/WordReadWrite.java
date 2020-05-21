package file.word;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.springframework.beans.BeanUtils;

import java.io.FileInputStream;
import java.util.*;

/**
 *
 */
public class WordReadWrite {
    public static void main(String[] args) throws Exception {
        String filePath = "D:\\svn_project\\liantong\\sunline-svn\\02 工程\\02 设计\\各子模块数据库\\银企系统\\数据库设计\\银企系统数据库表设计-V1.0.10.docx";
        FileInputStream in = new FileInputStream(filePath);//载入文档 //如果是office2007  docx格式
        if (filePath.toLowerCase().endsWith("docx")) {
            //word 2007 图片不会被读取， 表格中的数据会被放在字符串的最后
            XWPFDocument xwpf = new XWPFDocument(in);//得到word文档的信息
//             List<XWPFParagraph> listParagraphs = xwpf.getParagraphs();//得到段落信息
            Iterator<XWPFTable> it = xwpf.getTablesIterator();//得到word中的表格
            int ii = 0;
            List<TableList> tableList = getTableList(it);
            it.next();
            modifyTableInfo(it,null);
        }


    }

    /**
     *
     * @param it
     */
    private static List<TableList>  getTableList (Iterator<XWPFTable> it){
        List<TableList> tableLists=new ArrayList<>();
        if (it.hasNext()) {
            XWPFTable table = it.next();
            List<XWPFTableRow> rows = table.getRows();
            //读取每一行数据
            for (int i = 1; i < rows.size(); i++) {
                XWPFTableRow row = rows.get(i);
                //读取每一列数据
                List<XWPFTableCell> cells = row.getTableCells();
                if(cells.size()<5){
                    continue;
                }
                TableList tableList=new TableList();
                tableList.setTableName(cells.get(2).getText());
                tableList.setTableCommont(cells.get(3).getText());
                tableLists.add(tableList);
            }
        }
        return tableLists;
    }

    /**
     *
     * @param it  文档
     * @param tableMap  带表名的Map标准数据信息
     */
    private static void  modifyTableInfo (Iterator<XWPFTable> it,Map<String,List<TableInfo>> tableMap){
        while (it.hasNext()) {
            XWPFTable table = it.next();
            List<XWPFTableRow> rows = table.getRows();
            //读取每一行数据  表名 BEDC_ACCOUNT_QUERY_INFO
            String tableName=rows.get(0).getTableCells().get(1).getText().trim();

            //读取第三行数据
            //获取标准表信息
            List<TableInfo> tl = tableMap.get(tableName);
            for (int i = 3; i < rows.size(); i++) {
                XWPFTableRow row = rows.get(i);
                //读取每一列数据
                List<XWPFTableCell> cells = row.getTableCells();
                for(TableInfo tt:tl){
                    if(tt.getColumn().equalsIgnoreCase(cells.get(0).getText().trim())){
                        //修改每一行的数据  字段名称0	中文说明1	字段类型2	长度3	约束4	备注5
                        cells.get(2).setText(tt.getColumnType());
                        cells.get(3).setText(tt.getColumnLength());
                        cells.get(4).setText("Y".equals(tt.getColumnType())?"NOT  NULL":"");
                    }else {
                        continue;
                    }
                }
//                for (int j = 0; j < cells.size(); j++) {
//                    XWPFTableCell cell = cells.get(j);
//
//                    //输出当前的单元格的数据
//                    System.out.println(cell.getText());
//                }
            }
        }
    }

    /**
     * 将查询出来的标准信息转换成带表名的Map信息
     * @param tableStandardInfos
     * @return
     */
    private static Map<String,List<TableInfo>> convertListToMap(List<TableStandardInfo> tableStandardInfos){
        Map<String,List<TableInfo>> stringListMap=new HashMap<>();
        for(TableStandardInfo t:tableStandardInfos){
            if(!stringListMap.containsKey(t.getTableName().trim())){
                List<TableInfo> lists=new ArrayList<>();
                TableInfo tableList=new TableInfo();
                BeanUtils.copyProperties(t,tableList);
                lists.add(tableList);
                stringListMap.put(t.getTableName(),lists);
            }else {
                TableInfo tableInfo=new TableInfo();
                BeanUtils.copyProperties(t,tableInfo);
                stringListMap.get(t.getTableName()).add(tableInfo);
            }
        }
        return stringListMap;
    }
}
