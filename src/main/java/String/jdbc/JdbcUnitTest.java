
package String.jdbc;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author: suxiaolei
 * @date: 2019/7/15
 */
public class JdbcUnitTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入：");
        String scannerStr = scanner.next();
        List<String> strList = new ArrayList<>();
        do {
            String string = scanner.nextLine();
            if (string.equals("")) {
                break;
            }
            strList.add(string);
        } while (true);
        for (String strAr : strList) {
            if (!strAr.contains("Parameters:") && !strAr.contains("Preparing:")) {
                System.out.println("输入有误");
                return;
            }
        }
        String[] strArray = scannerStr.split(" Parameters:");
        //参数
        String str = strList.get(1);
        //sql结果
        String result = strList.get(0);
        if (str.contains("Parameters:")) {
            int len = str.indexOf(":");
            str = str.substring(len + 1, str.length());
        }
        String[] split = str.split(",");
        List<JdbcUnit> list = new ArrayList<>(split.length);
        JdbcUnit jdbcUnit = new JdbcUnit();
        for (String s : split) {
            if (s.contains("String")|| s.contains("Timestamp")) {
                jdbcUnit = new JdbcUnit();
                jdbcUnit.setValue("'" + s.substring(0, s.indexOf("(")).trim() + "'");
                jdbcUnit.setAttribute("String");
                list.add(jdbcUnit);
                continue;
            }
            if (s.contains("Long")||s.contains("BigDecimal")||s.contains("Integer")) {
                jdbcUnit = new JdbcUnit();
                jdbcUnit.setValue(s.substring(0, s.indexOf("(")).trim());
                jdbcUnit.setAttribute("Long");
                list.add(jdbcUnit);
                continue;
            }
            if ("null".equals(s.trim())) {
                jdbcUnit = new JdbcUnit();
                jdbcUnit.setValue("''");
                list.add(jdbcUnit);
                continue;
            }
            jdbcUnit = new JdbcUnit();
            jdbcUnit.setAttribute("未知");
            list.add(jdbcUnit);
        }
        for (JdbcUnit j : list) {
            result = result.replaceFirst("[?]", j.getValue());
            //  System.out.println(j.toString());
        }
        System.out.println(result.substring(result.indexOf(":") + 1, result.length()));
        //  System.out.println(split.toString());


    }
}
