package JDBC;

import reflex.ReflexUtil;

import java.sql.*;
import java.util.*;

public class JDBCUtil {
    /**
     * 数据库驱动类名称
     */
    private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";

    /**
     * 连接字符串
     */
    private static final String URL = "jdbc:oracle:thin:@//127.0.0.1:1521/ssw";

    /**
     * 用户名
     */
    private static final String USERNAME = "XIR_TRD_J";

    /**
     * 密码
     */
    private static final String USERPASSWORD = "xpar";
    /**
     * 创建数据库连接对象
     */
    public Connection connection = null;

    /**
     * 创建PreparedStatement对象
     */
    private PreparedStatement preparedStatement = null;

    /**
     * 创建CallableStatement对象
     */
    private CallableStatement callableStatement = null;

    /**
     * 创建结果集对象
     */
    private ResultSet resultSet = null;

    /**
     * 建立数据库连接
     *
     * @return 数据库连接
     */
    public Connection getConnection(String driverName, String url, String userName, String userPassword) {
        try {
            Class.forName(driverName);
            connection = DriverManager.getConnection(url, userName,
                    userPassword);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * 获取结果集，并将结果放在List中
     *
     * @param sql SQL语句
     * @return List
     * 结果集
     */
    public <T> List<T> excuteQuery(String sql, List<Object> params, Class<T> object) {
        List<T> list = new ArrayList<T>();
        ResultSetMetaData resultSetMetaData = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            // 参数赋值
            if (params.size() != 0) {
                for (int i = 0; i < params.size(); i++) {
                    preparedStatement.setObject(i + 1, params.get(i));
                }
            }
            resultSet = preparedStatement.executeQuery();
            resultSetMetaData = resultSet.getMetaData();
            while (resultSet.next()) {
                T t = object.newInstance();
                for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
                    ReflexUtil.setAttributeValue(resultSetMetaData.getColumnLabel(i), resultSet.getObject(i), t);
                }
                list.add(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return list;
    }

    public <T> void executeUpdateList(String sql, List<T> list, String[] param) {
        try {
            for (int i = 0; i < list.size(); i++) {
                List<Object> paramlist = new ArrayList<Object>();
                T targetObject = list.get(i);
                for (int j = 0; j < param.length; j++) {
                    Object object = ReflexUtil.readValueByName(param[j], targetObject);
                    paramlist.add(object);
                }
                executeUpdate(sql, paramlist);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getSqlString(String sql, List params) {
        if (sql == null || sql.length()==0) {
            return sql;
        }
        for(int i=0;i<params.size();i++){
           if(params.get(i) instanceof  String){
               params.set(i,"'"+params.get(i)+"'");
           }
        }
        Object[] objects = params.toArray();
        String statement =String.format(sql.replaceAll("\\?", "%s"), objects);
        return statement;
    }

    /**
     * @param sql
     * @param list 传入sql的参数
     * @return
     */
    public int executeUpdate(String sql, List<?> list) throws SQLException {
        // 受影响的行数
        int affectedLine = 0;
        // 调用SQL
        preparedStatement = connection.prepareStatement(sql);
        // 参数赋值
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                preparedStatement.setObject(i + 1, list.get(i));
            }
        }
        // 执行
        affectedLine = preparedStatement.executeUpdate();
        return affectedLine;
    }

    /**
     * @param sql
     * @param list
     * @param reduceParam 实体类比数据库多出的字段
     * @param <T>
     */
    public <T> void executeInsertList(String sql, List<T> list, List<String> reduceParam) {
        try {
            for (T t : list) {
                executeInsert(sql, t, reduceParam);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param sql
     * @param t
     * @param <T>
     * @throws SQLException
     */
    public <T> void executeInsert(String sql, T t, List<String> cutParam) throws SQLException {
        preparedStatement = connection.prepareStatement(sql);
        ReflexUtil.setObjectToPreparedStatement(preparedStatement, t, cutParam);
        preparedStatement.executeUpdate();
    }

    /**
     * 关闭所有资源
     */
    public void closeAll() {
        // 关闭结果集对象
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // 关闭PreparedStatement对象
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // 关闭CallableStatement 对象
        if (callableStatement != null) {
            try {
                callableStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        // 关闭Connection 对象
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
