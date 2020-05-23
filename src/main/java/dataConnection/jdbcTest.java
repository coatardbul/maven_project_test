package dataConnection;

import common.entity.ImportCrmProduct;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class jdbcTest {
    public static void main(String[] args) {


    }


    public JDBCUtil getConnect() {
        JDBCUtil jdbcUtil = new JDBCUtil();
        String driver = "oracle.jdbc.driver.OracleDriver";
        String url = "jdbc:oracle:thin:@//10.50.21.89:1521/acrmtest";
        String userName = "crmsc";
        String userPassword = "crmsc";
        jdbcUtil.getConnection(driver, url, userName, userPassword);
        return jdbcUtil;
    }

    @Test
    public void testMain() {
        JDBCUtil jdbcUtil = getConnect();
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
            ;
        } finally {
            jdbcUtil.closeAll();
        }
    }

    public List<ImportCrmProduct> queryTest(JDBCUtil jdbcUtil) {
        String sql = "select date_id     dateId, update_time     updateTime, prod_id     prodId,  app_type     appType, cust_bal     custBal, res_status     resStatus, print_app_yes     printAppYes , print_confim_yes     printConfimYes , reg_app_time     regAppTime , trade_time     tradeTime from TTRD_IMPORT_CRM_PRODUCT";
        List<ImportCrmProduct> list = jdbcUtil.excuteQuery(sql, null, ImportCrmProduct.class);
        System.out.println("******" + list.size() + "*************");
        System.out.println(list.toString());
        return list;
    }

    public void updateTestList(JDBCUtil jdbcUtil, List<ImportCrmProduct> list) throws SQLException {
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setProdId("25588");
            list.get(i).setCustBal(22.90);
        }
        String updateSql = "update TTRD_IMPORT_CRM_PRODUCT set PROD_ID =?,CUST_BAL=?";
        String[] s = {"prodid", "custBal"};
        jdbcUtil.executeUpdateList(updateSql, list, s);

    }

    public void updateTest(JDBCUtil jdbcUtil) {
        try {
            String updateSql = "update TTRD_IMPORT_CRM_PRODUCT set PROD_ID =?,CUST_BAL=?";
            List<Object> paramlist = new ArrayList<Object>();
            paramlist.add("wwww");
            paramlist.add(123.90);
            jdbcUtil.executeUpdate(updateSql, paramlist);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertTest(JDBCUtil jdbcUtil) {
        String insertSql = "insert into TTRD_IMPORT_CRM_PRODUCT (DATE_ID, UPDATE_TIME, PROD_ID, APP_TYPE, CUST_BAL, RES_STATUS, PRINT_APP_YES, PRINT_CONFIM_YES, REG_APP_TIME, TRADE_TIME) values (?,?,?,?,?,?,?,?,?,?) ";

        List<ImportCrmProduct> ImportCrmProductsList = new ArrayList<ImportCrmProduct>();
        for (int i = 0; i < 10; i++) {
            ImportCrmProduct ImportCrmProduct = new ImportCrmProduct();
            ImportCrmProduct.setDateId((long) new Random().nextInt());
            ImportCrmProduct.setProdId("hello");
            ImportCrmProduct.setAppType("12");
            ImportCrmProduct.setResStatus("1");
            ImportCrmProduct.setTradeTime("2018-09-08 12:12");
            ImportCrmProductsList.add(ImportCrmProduct);
        }
        List<String> cutParam = new ArrayList<>();
        cutParam.add("prodName");
        jdbcUtil.executeInsertList(insertSql, ImportCrmProductsList, cutParam);
        //jdbcUtil.executeInsert(insertSql,ImportCrmProduct);

    }

    @Test
    public void getSqlString() {
        JDBCUtil jdbcUtil = new JDBCUtil();
        String sql = "select * from ttrd ?,?  ";
        List list = new ArrayList();
        list.add("123");
        list.add(2123);
        String str =   jdbcUtil.getSqlString(sql, list);
        System.out.println(str);
    }
}
