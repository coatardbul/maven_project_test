package JDBCTest;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BusinessUtil {
    public static int INT_SQL_LENGTH = 1000;
    public static String  DATEID = "dateId";
    public static String  ORACLE_DATEID = "DATE_ID";
    public static String  TRADE_TIME = "tradeTime";
    public static String  ORACLE_TRADE_TIME = "TRADE_TIME";
    public static String  PRODID = "prodId";
    public static String  ORACLE_PRODID  = "PROD_ID";

    /**
     * crmList 从crm取出的数据集合
     * 查询当前trade_time>=当前时间
     * ownList 本地数据的集合
     * 查询当前trade_time>=当前时间
     *
     * @return insertList
     * updateList
     */
    public  void collateListDate(List<ImportCrmProduct> crmList, List<ImportCrmProduct> ownList) {
        //TODO
        //取数逻辑,1取成功的交易和当前系统trade_time>=当前时间&&成功的预约id
        //in 拼接(不能超过1000条件)

//        select * from crm系统
//                where trade_time>=当前时间
//                and 预约id  in  (当前系统trade_time>=当前时间&&成功的预约id )
//                  and  (去除成功的数据)
//        union all
//        select * from crm系统
//        where trade_time>=当前时间
//        and (成功的状态)
//          and not in （当前系统trade_time>=当前时间&&成功的预约id ）
//          and  产品id in(启用的产品)

        try {
            //复制crmlist
            List<ImportCrmProduct> crmListTemp = ListCopyUtil.deepCopy(crmList);
            //插入的数据
            crmList.removeAll(ownList);
            //TODO  插入sql  list
            //更新的数据
            crmListTemp.removeAll(crmList);
            //TODO  更新sql  list

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public <T> List<T> getCrmSystemProduceList() {
        JDBCUtil jdbcUtil = getCrmConnect();
        try {
            jdbcUtil.connection.setAutoCommit(false);
            //************业务开始***************
            Map<String,List<Object>> map= getOwnParamsToCRM();
            String querySql = getCRMProductSql(map);
            //TODO
            List<ImportCrmProduct> list = jdbcUtil.excuteQuery(querySql, getListByMap(map), ImportCrmProduct.class);
            //*************业务结束**************
            jdbcUtil.connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                jdbcUtil.connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            jdbcUtil.closeAll();
        }
        return null;
    }

    public JDBCUtil getCrmConnect() {
        JDBCUtil jdbcUtil = new JDBCUtil();
        String driver = "oracle.jdbc.driver.OracleDriver";
        String url = "jdbc:oracle:thin:@//localhost:1521/ssw";
        String userName = "XIR_TRD_J";
        String userPassword = "xpar";
        jdbcUtil.getConnection(driver, url, userName, userPassword);
        return jdbcUtil;
    }

    public <T> void ownSystemProcess(List<T> insertList, List<T> updateList) {
        JDBCUtil jdbcUtil = getCrmConnect();
        try {
            jdbcUtil.connection.setAutoCommit(false);
            //************业务开始***************
            String insertSql = "";
            List<String> reduceParam = new ArrayList<>();
            reduceParam.add("");
            jdbcUtil.executeInsertList(insertSql, insertList, reduceParam);

            String updateSql = "";
            String[] param = new String[3];
            jdbcUtil.executeUpdateList(updateSql, updateList, param);
            //*************业务结束**************
            jdbcUtil.connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                jdbcUtil.connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            jdbcUtil.closeAll();
        }
    }
    public Map<String,List<Object>> getOwnParamsToCRM(){
        Map<String,List<Object>> m=new HashMap<>();
        //获取预约id
        List<Object> dateIdList=null;
        //TODO
        m.put(BusinessUtil.DATEID,dateIdList);
        //获取产品id
        List<Object> prodIdList=null;
        //TODO
        m.put(BusinessUtil.PRODID,prodIdList);
        return m;
    }
    public String getCRMProductSql(Map<String,List<Object>>  mapParam) {
        StringBuffer sb = new StringBuffer();
        //TODO
        sb.append(getSqlIn(BusinessUtil.DATEID,mapParam.get(BusinessUtil.DATEID)));
        //TODO
        return sb.toString();
    }

    /**
     * 根据map生成传入sql的list参数
     * @param mapParam
     * @return
     */
    public List<Object> getListByMap(Map<String,List<Object>>  mapParam){
        List<Object> list=new ArrayList<>();
        //TODO
        list.addAll(mapParam.get(BusinessUtil.PRODID));
        return list;
    }
    /**
     * @param sqlParam
     * @param ownSystemProdIdList
     * @return sqlParam in (?,?)
     */
    public StringBuffer getSqlIn(String sqlParam, List<Object> ownSystemProdIdList) {
        if (ownSystemProdIdList.size() == 0) {
            return new StringBuffer();
        }
        StringBuffer sb = new StringBuffer(sqlParam);
        sb.append("in (");
        if (ownSystemProdIdList.size() < BusinessUtil.INT_SQL_LENGTH) {
            for (int i = 1; i < ownSystemProdIdList.size(); i++) {
                sb.append("?,");
            }
            sb.append("?)");
            return sb;
        } else {
            BigDecimal bd = new BigDecimal(ownSystemProdIdList.size());
            int num = bd.divide(new BigDecimal(BusinessUtil.INT_SQL_LENGTH), BigDecimal.ROUND_UP).intValue();
            int remainderNum = ownSystemProdIdList.size() % BusinessUtil.INT_SQL_LENGTH;
            if (remainderNum == 0) {
                for (int i = 1; i < num; i++) {
                    for (int j = 1; j < BusinessUtil.INT_SQL_LENGTH; j++) {
                        sb.append("?,");
                    }
                    sb.append("?) ");
                }
            } else {
                for (int i = 1; i < num; i++) {
                    for (int j = 1; j < BusinessUtil.INT_SQL_LENGTH; j++) {
                        sb.append("?,");
                    }
                    sb.append("?)  or");
                    sb.append(sqlParam);
                    sb.append("in (");
                }
                for (int i=1;i<remainderNum;i++) {
                    sb.append("?,");
                }
                sb.append("?)");
            }
        }
        return sb;
    }
}
