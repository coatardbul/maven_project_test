package JDBC;

import JDBC.JDBCUtil;
import entity.ImportCrmProduct;
import org.junit.Test;

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

    public  static String   SB="hello";
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


    @Test
    public void getCrmSystemProduceList() {
        JDBCUtil jdbcUtil = getCrmConnect();
        try {
            jdbcUtil.connection.setAutoCommit(false);
            //************业务开始***************
            String querySql="select app.DATE_ID,'' as update_time,app.prod_id,app.app_type,app.cust_bal,app.res_status,app.PRINTAPP_YES  as print_app_yes,'' as print_confim_yes,to_char(app.reg_app_time,'yyyy-mm-dd hh24:mi:ss') as reg_app_time,'2018-10-22' as trade_time   " +
                    "FROM CRM_F_SE_CUST_APP app  inner JOIN CRM_F_PD_RECORD pd    " +
                    " ON app.prod_id = pd.prod_id  inner JOIN CRM_F_CI_CUST cust   " +
                    "  ON app.cus_id = cust.cust_id   left JOIN CRM_F_CFG_BENEFIT bef    " +
                    " on pd.benefit_id = bef.benefitcode   left JOIN crm_f_ack_trans OTS    " +
                    " on app.appsheetserialno = OTS.Appsheetserialno " +
                    " WHERE bef.fundtype = '5200'    ";
            List<ImportCrmProduct> list=jdbcUtil.excuteQuery(querySql,new ArrayList<Object>(),ImportCrmProduct.class);
            //*************业务结束**************
            System.out.println(list.toString());
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
       // return null;
    }

    public JDBCUtil getCrmConnect() {
        JDBCUtil jdbcUtil = new JDBCUtil();
        String driver = "oracle.jdbc.driver.OracleDriver";
        String url = "jdbc:oracle:thin:@//10.50.21.89:1521/acrmtest";
        String userName = "crmsc";
        String userPassword = "crmsc";
        jdbcUtil.getConnection(driver, url, userName, userPassword);
        return jdbcUtil;
    }



    public <T> void ownSystemProcess(List<T> insertList, List<T> updateList) {
        JDBCUtil jdbcUtil = getCrmConnect();
        try {
            jdbcUtil.connection.setAutoCommit(false);
            //************业务开始***************

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
        sb.append(getSqlIn(BusinessUtil.DATEID," and "," in ",mapParam.get(BusinessUtil.DATEID)));
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
    public static  StringBuffer getSqlIn(String sqlParam, String andOr, String inOrNot, List<Object> ownSystemProdIdList) {
        if (ownSystemProdIdList.size() == 0 || ownSystemProdIdList == null) {
            return new StringBuffer();
        }
        StringBuffer sb = new StringBuffer(sqlParam);
        sb.append(inOrNot);
        sb.append("(");
        if (ownSystemProdIdList.size() < INT_SQL_LENGTH) {
            for (int i = 1; i < ownSystemProdIdList.size(); i++) {
                sb.append("?,");
            }
            sb.append("?)");
            return sb;
        } else {
            BigDecimal bd = new BigDecimal(ownSystemProdIdList.size());
            int num = bd.divide(new BigDecimal(INT_SQL_LENGTH), BigDecimal.ROUND_UP).intValue();
            int remainderNum = ownSystemProdIdList.size() % INT_SQL_LENGTH;
            if (remainderNum == 0) {
                for (int i = 1; i < num; i++) {
                    for (int j = 1; j < INT_SQL_LENGTH; j++) {
                        sb.append("?,");
                    }
                    sb.append("?) ");
                }
            } else {
                for (int i = 1; i < num; i++) {
                    for (int j = 1; j < INT_SQL_LENGTH; j++) {
                        sb.append("?,");
                    }
                    sb.append("?) ");
                    sb.append(andOr);
                    sb.append(sqlParam);
                    sb.append(inOrNot);
                    sb.append("(");
                }
                for (int i = 1; i < remainderNum; i++) {
                    sb.append("?,");
                }
                sb.append("?)");
            }
        }
        return sb;
    }
}
