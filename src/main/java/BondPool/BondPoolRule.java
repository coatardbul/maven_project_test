package BondPool;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BondPoolRule {
    private static final String MAX_VALUE = "MAX_VALUE";
    private static final String MIN_VALUE = "MIN_VALUE";
    //类型(行权债1，到期日2)
    private static final String B_STRIKE_DATE = "1";
    private static final String B_MTR_DATE = "2";

    @Test
    public void Test() {
        //多条件的测试
        Map<String, Object> map = new HashMap<>();
//        mulString.add("P_CLASS");
//        mulString.add("TRADE_EXE_MARKET");
//        mulString.add("TRADE_MARKET");
        map.put("P_CLASS", "保险公司资本补充债,超短期融资券,次级定期债务,次级债,地方政府债,短期融资券,二级资本债,非公开定向债务融资工具,非公开公司债,公司债,国债,混合资本债,集合票据,集合债券,金融债,可分离债,可分离转债,可交换公司债,可交换私募债,可转债,企业债,区域集优票据,区域集优中期票据,私募债,同业存单,项目收益票据,项目收益债券,小微企业扶持债,央行票据,政策性金融债,政府支持债,证券公司公司债,中期票据,资产支持票据,资产支持证券");
        map.put("TRADE_MARKET", "X_CNBD,XSHG,XSHE,NONE,SGEX,X_CNFFEX");
        map.put("TRADE_EXE_MARKET", "X_CNBD_QSS,X_CNBD_ZZD,XSHG,XSHE,X_VIRTUAL,NONE,SGEX,X_CNFFEX,OTHER");

        Map<String, Object> map1 = new HashMap<>();
        map1.put("P_CLASS", "保险公司资本补充债,超短期融资券,次级定期债务");
        map1.put("TRADE_MARKET", "X_CNBD,XSHG");
        map1.put("TRADE_EXE_MARKET", "X_CNBD_QSS,X_CNBD_ZZD");

        Map<String, Object> map2 = new HashMap<>();
        map2.put("P_CLASS", "保险公司资本补充债");
        map2.put("TRADE_EXE_MARKET", "");
        List<Map<String, Object>> resultList = new ArrayList<>();
        resultList.add(map);
        resultList.add(map1);
        resultList.add(map2);
        Map<String, Object> resultMap = mergerRules(resultList);
        System.out.println(resultMap);


    }

    @Test
    public void test2() {
        //字典list
        Map<String, String> dictMap = new HashMap<>();
        dictMap.put("ISSUE_GRADE_DOWNTYPE", "ISSUE_GRADE_DOWNVAL");
        dictMap.put("BOND_GRADE_DOWNTYPE", "BOND_GRADE_DOWNVAL");
        dictMap.put("ISSUE_GRADE_UPTYPE", "ISSUE_GRADE_UPVAL");
        dictMap.put("BOND_GRADE_UPTYPE", "BOND_GRADE_UPVAL");

        //< =   <=的左边 取最大值的判断
        List<String> minString = new ArrayList<>();
        minString.add("ISSUE_GRADE_DOWNTYPE");
        minString.add("BOND_GRADE_DOWNTYPE");
        Map<String, Object> map = new HashMap<>();
        map.put("ISSUE_GRADE_DOWNTYPE", "=");
        map.put("ISSUE_GRADE_DOWNVAL", new BigDecimal(1));
        Map<String, Object> map1 = new HashMap<>();
        map1.put("ISSUE_GRADE_DOWNTYPE", "=");
        map1.put("ISSUE_GRADE_DOWNVAL", new BigDecimal(23));
        Map<String, Object> map2 = new HashMap<>();
        map2.put("ISSUE_GRADE_DOWNTYPE", "<");
        map2.put("ISSUE_GRADE_DOWNVAL", new BigDecimal(12));
        List<Map<String, Object>> resultList = new ArrayList<>();
        resultList.add(map);
        resultList.add(map1);
        resultList.add(map2);
        Map<String, Object> resultMap = mergerRules(resultList);
        System.out.println(resultMap);
    }

    @Test
    public void test3() {
        //字典list
        Map<String, String> dictMap = new HashMap<>();
        dictMap.put("ISSUE_GRADE_DOWNTYPE", "ISSUE_GRADE_DOWNVAL");
        dictMap.put("BOND_GRADE_DOWNTYPE", "BOND_GRADE_DOWNVAL");
        dictMap.put("ISSUE_GRADE_UPTYPE", "ISSUE_GRADE_UPVAL");
        dictMap.put("BOND_GRADE_UPTYPE", "BOND_GRADE_UPVAL");

        //< =   <=的右边 取最小值的判断
        List<String> minString = new ArrayList<>();
        minString.add("ISSUE_GRADE_UPTYPE");
        minString.add("BOND_GRADE_UPTYPE");
        Map<String, Object> map = new HashMap<>();
        map.put("ISSUE_GRADE_UPTYPE", "<");
        map.put("ISSUE_GRADE_UPVAL", new BigDecimal(111));
        Map<String, Object> map1 = new HashMap<>();
        map1.put("ISSUE_GRADE_UPTYPE", "<");
        map1.put("ISSUE_GRADE_UPVAL", new BigDecimal(23));
        Map<String, Object> map2 = new HashMap<>();
        map2.put("ISSUE_GRADE_UPTYPE", "<");
        map2.put("ISSUE_GRADE_UPVAL", new BigDecimal(12));
        List<Map<String, Object>> resultList = new ArrayList<>();
        resultList.add(map);
        resultList.add(map1);
        resultList.add(map2);
        Map<String, Object> resultMap = mergerRules(resultList);
        System.out.println(resultMap);
    }

    @Test
    public void test4() {
        //行权日的判断
        Map<String, Object> map = new HashMap<>();
        map.put("SURPLUS_PERIOD_TYPE", "1");
        map.put("SURPLUS_PERIOD_UP", new BigDecimal(11));
        map.put("SURPLUS_PERIOD_DOWN", new BigDecimal(22));
        Map<String, Object> map1 = new HashMap<>();
        map1.put("SURPLUS_PERIOD_TYPE", "1");
        map1.put("SURPLUS_PERIOD_UP", new BigDecimal(15));
        map1.put("SURPLUS_PERIOD_DOWN", new BigDecimal(17));
        Map<String, Object> map2 = new HashMap<>();
//        map2.put("SURPLUS_PERIOD_TYPE", "2");
//        map2.put("SURPLUS_PERIOD_UP", new BigDecimal(11));
//        map2.put("SURPLUS_PERIOD_DOWN", new BigDecimal(55));
        Map<String, Object> map3 = new HashMap<>();
        map3.put("SURPLUS_PERIOD_TYPE", "1");
        map3.put("SURPLUS_PERIOD_UP", new BigDecimal(32));
        map3.put("SURPLUS_PERIOD_DOWN", new BigDecimal(456));
        Map<String, Object> map4 = new HashMap<>();
        map4.put("SURPLUS_PERIOD_TYPE", "2");
        map4.put("SURPLUS_PERIOD_UP", new BigDecimal(12));
        map4.put("SURPLUS_PERIOD_DOWN", new BigDecimal(234));
        List<Map<String, Object>> resultList = new ArrayList<>();



        resultList.add(map);
        resultList.add(map1);
        resultList.add(map2);
        for(Map<String, Object> s:resultList){
            s.put("123123","111111111111");


        }
        for(Map<String, Object> s:resultList){
            System.out.println(s);
        }
//        resultList.add(map3);
//        resultList.add(map4);
//        Map<String, Object> resultMap = mergerRules(resultList);
//        System.out.println(resultMap);
    }

    public Map<String, Object> mergerRules(List<Map<String, Object>> resultList) {
        if (resultList.size() == 0) {
            return null;
        }
        Map<String, Object> mapResult1 = new HashMap<>();
        int num = 0;
        for (int i = 0; i < resultList.size(); i++) {
            mapResult1.putAll(resultList.get(i));
        }
        Integer state = null;
        for (String key : mapResult1.keySet()) {
            state = mulStringMergo(mapResult1, key, resultList);
            if (state == null) {
                break;
            }
        }
        if (state == null) {
            mapResult1.clear();
        }
        return mapResult1;
    }

    /**
     * @param mapResult
     * @param str
     * @param resultList
     * @return 空则合并的规则为空，1则有合并的规则
     */
    public Integer mulStringMergo(Map<String, Object> mapResult, String str, List<Map<String, Object>> resultList) {
        if (mapResult == null) {
            return null;
        }
        if (resultList.size() == 0) {
            return null;
        }
        Map<String, String> periodMapMin = new HashMap<>();
        periodMapMin.put("SURPLUS_PERIOD_TYPE", "SURPLUS_PERIOD_UP");
        Map<String, String> periodMapMax = new HashMap<>();
        periodMapMax.put("SURPLUS_PERIOD_TYPE", "SURPLUS_PERIOD_DOWN");
        //行权日，剩余期限的判断
        if ("SURPLUS_PERIOD_TYPE".equals(str)) {
            List<Map<String, Object>> bStrikeDateList = new ArrayList<>();
            List<Map<String, Object>> bMtrDateList = new ArrayList<>();
            for (Map<String, Object> map : resultList) {
                if (map.get(str) != null && !"".equals(map.get(str))) {
                    //行权日
                    if (map.get(str).equals(BondPoolRule.B_STRIKE_DATE)) {
                        bStrikeDateList.add(map);
                    }
                    // 到期日
                    if (map.get(str).equals(BondPoolRule.B_MTR_DATE)) {
                        bMtrDateList.add(map);
                    }
                }
            }
            //行权日
            if(bStrikeDateList.size()>0){
                mapResult.put(str + "_TEMP", bStrikeDateList.get(0).get(str));
                mapResult.put(periodMapMin.get(str) + "_TEMP", extremeValueBigDecimal(BondPoolRule.MAX_VALUE,periodMapMin.get(str),bStrikeDateList));
                mapResult.put(periodMapMax.get(str) + "_TEMP", extremeValueBigDecimal(BondPoolRule.MIN_VALUE,periodMapMax.get(str),bStrikeDateList));
            }
            //到期日
            if(bMtrDateList.size()>0){
                mapResult.put(str , bMtrDateList.get(0).get(str));
                mapResult.put(periodMapMin.get(str) , extremeValueBigDecimal(BondPoolRule.MAX_VALUE,periodMapMin.get(str),bMtrDateList));
                mapResult.put(periodMapMax.get(str) , extremeValueBigDecimal(BondPoolRule.MIN_VALUE,periodMapMax.get(str),bMtrDateList));
            }else {
                mapResult.put(str , null);
                mapResult.put(periodMapMin.get(str) , null);
                mapResult.put(periodMapMax.get(str) , null);

            }
        }

        //多选择框的key
        List<String> mulString = new ArrayList<>();
        mulString.add("P_CLASS");
        mulString.add("TRADE_EXE_MARKET");
        mulString.add("TRADE_MARKET");
        //发行人
        // TODO
        if (mulString.contains(str)) {
            List<String> listGoal = new ArrayList<>();
            for (Map<String, Object> map : resultList) {
                if (map.get(str) != null && !"".equals(map.get(str))) {
                    if (listGoal.size() == 0) {
                        listGoal = stringToList((String) map.get(str));
                    } else {
                        listGoal.retainAll(stringToList((String) map.get(str)));
                    }
                }
            }
            mapResult.put(str, listToString(listGoal));
        }
        //字典list
        Map<String, String> dictMap = new HashMap<>();
        dictMap.put("ISSUE_GRADE_DOWNTYPE", "ISSUE_GRADE_DOWNVAL");
        dictMap.put("BOND_GRADE_DOWNTYPE", "BOND_GRADE_DOWNVAL");
        dictMap.put("ISSUE_GRADE_UPTYPE", "ISSUE_GRADE_UPVAL");
        dictMap.put("BOND_GRADE_UPTYPE", "BOND_GRADE_UPVAL");

        //< =   <=的左边 取最大值的判断
        List<String> minString = new ArrayList<>();
        minString.add("ISSUE_GRADE_DOWNTYPE");
        minString.add("BOND_GRADE_DOWNTYPE");
        if (minString.contains(str)) {
            Map<String, Integer> mapTemp = getListAttribute(str, resultList);
            if (!mapTemp.containsKey("=")) {
                setExtremeValue(dictMap, mapResult, str, BondPoolRule.MAX_VALUE, resultList);
            } else if (mapTemp.get("=") > 1) {
                return null;
            } else if (mapTemp.get("=") == 1) {
                for (Map<String, Object> map : resultList) {
                    if (map.get(str) != null && !"".equals(((String) map.get(str)).trim())) {
                        if (((String) map.get(str)).trim().equals("=")) {
                            mapResult.put(str, ((String) map.get(str)).trim());
                            mapResult.put(dictMap.get(str), map.get(dictMap.get(str)));
                        }
                    }
                }
                for (Map<String, Object> map : resultList) {
                    if (map.get(str) != null && !"".equals(((String) map.get(str)).trim())) {
                        if (!map.get(str).equals("=")) {
                            if (((BigDecimal) (map.get(dictMap.get(str)))).compareTo((BigDecimal) mapResult.get(dictMap.get(str))) > 0) {
                                return null;
                            }
                        }
                    }
                }

            }
        }
        //< =  <=右边的 取最小值的判断
        List<String> maxString = new ArrayList<>();
        maxString.add("ISSUE_GRADE_UPTYPE");
        maxString.add("BOND_GRADE_UPTYPE");
        if (maxString.contains(str)) {
            Map<String, Integer> minTemp = getListAttribute(str, resultList);
            if (!minTemp.containsKey("=")) {
                setExtremeValue(dictMap, mapResult, str, BondPoolRule.MIN_VALUE, resultList);
            } else if (minTemp.get("=") > 1) {
                return null;
            } else if (minTemp.get("=") == 1) {
                //将=后面的值放到对应的规则上
                for (Map<String, Object> map : resultList) {
                    if (map.get(str) != null && !"".equals(map.get(str))) {
                        if (((String) map.get(str)).trim().equals("=")) {
                            mapResult.put(str, ((String) map.get(str)).trim());
                            mapResult.put(dictMap.get(str), map.get(dictMap.get(str)));
                        }
                    }
                }
                //将=后面的值与其他类型的值作对比
                //如果=后面的值比其他值大，则规则无效
                //如果=后面的值比其他值小，则取=的规则
                for (Map<String, Object> map : resultList) {
                    if (map.get(str) != null && !"".equals(map.get(str))) {
                        if (!((String) map.get(str)).trim().equals("=")) {
                            if (((BigDecimal) (map.get(dictMap.get(str)))).compareTo((BigDecimal) mapResult.get(dictMap.get(str))) < 0) {
                                return null;
                            }
                        }
                    }
                }
            }
        }
        //列左边的值，交集取最大值
        List<String> minValue = new ArrayList<>();
        minValue.add("B_ISSUE_PERIOD_UP");
        minValue.add("MODIFIED_D_UP");
        minValue.add("CONVEXITY_UP");
        if (minValue.contains(str)) {
            BigDecimal maxV = extremeValueBigDecimal(BondPoolRule.MAX_VALUE, str, resultList);
            mapResult.put(str, maxV);
        }
        //列右边的值，交集取最小值
        List<String> maxValue = new ArrayList<>();
        maxValue.add("B_ISSUE_PERIOD_DOWN");
        maxValue.add("MODIFIED_D_DOWN");
        maxValue.add("CONVEXITY_DOWN");
        if (maxValue.contains(str)) {
            BigDecimal minV = extremeValueBigDecimal(BondPoolRule.MIN_VALUE, str, resultList);
            mapResult.put(str, minV);
        }
        return 1;

    }

    /**
     * 获取对应符号的次数
     *
     * @param str        不为空
     * @param resultList 肯定含有str值
     * @return
     */

    public Map<String, Integer> getListAttribute(String str, List<Map<String, Object>> resultList) {
        Map<String, Integer> resultMap = new HashMap<>();
        for (Map<String, Object> map : resultList) {
            if (map.get(str) != null && !"".equals(((String) map.get(str)).trim())) {
                if (!resultMap.containsKey(((String) map.get(str)).trim())) {
                    resultMap.put(((String) map.get(str)).trim(), 1);
                } else {
                    resultMap.put(((String) map.get(str)).trim(), resultMap.get(((String) map.get(str)).trim()) + 1);
                }
            }
        }
        return resultMap;
    }

    public List stringToList(String str) {
        List<String> list = new ArrayList<>();
        if (str.contains(",")) {
            String[] arrStr = str.split(",");
            for (int i = 0; i < arrStr.length; i++) {
                list.add(arrStr[i]);
            }
        } else {
            list.add(str);
        }
        return list;
    }

    public String listToString(List<String> list) {
        if (list.size() != 0) {
            StringBuffer sb = new StringBuffer(list.get(0));

            for (int i = 1; i < list.size(); i++) {
                sb.append(",").append(list.get(i));
            }
            return sb.toString();
        } else {
            return "";
        }

    }

    /**
     * resultList没有=号，取最大值或最小值
     *
     * @param dictMap
     * @param mapResult  合并的规则结果
     * @param str        map中的key
     * @param type       取最大值还是最小值
     * @param resultList
     */
    public void setExtremeValue(Map<String, String> dictMap, Map<String, Object> mapResult,
                                String str, String type, List<Map<String, Object>> resultList) {
        for (Map<String, Object> map : resultList) {
            if (map.get(str) != null && !"".equals(map.get(str))) {
                mapResult.put(str, ((String) map.get(str)).trim());
                mapResult.put(dictMap.get(str), map.get(dictMap.get(str)));
                if (type.equals(BondPoolRule.MAX_VALUE) && ((BigDecimal) map.get(dictMap.get(str))).compareTo((BigDecimal) mapResult.get(dictMap.get(str))) > 0) {
                    mapResult.put(str, ((String) map.get(str)).trim());
                    mapResult.put(dictMap.get(str), map.get(dictMap.get(str)));
                } else if (type.equals(BondPoolRule.MIN_VALUE) && ((BigDecimal) map.get(dictMap.get(str))).compareTo((BigDecimal) mapResult.get(dictMap.get(str))) < 0) {
                    mapResult.put(str, ((String) map.get(str)).trim());
                    mapResult.put(dictMap.get(str), map.get(dictMap.get(str)));
                }
            }
        }
    }

    /**
     * @param type       取最大值还是最小值
     * @param str        map中的key
     * @param resultList
     * @return
     */
    public BigDecimal extremeValueBigDecimal(String type, String str, List<Map<String, Object>> resultList) {
        BigDecimal extremeValue = null;
        for (Map<String, Object> map : resultList) {
            if (map.get(str) != null && !"".equals(map.get(str))) {
                if (extremeValue == null) {
                    extremeValue = (BigDecimal) map.get(str);
                } else if (type.equals(BondPoolRule.MAX_VALUE) && extremeValue.compareTo((BigDecimal) map.get(str)) < 0) {
                    extremeValue = (BigDecimal) map.get(str);
                } else if (type.equals(BondPoolRule.MIN_VALUE) && extremeValue.compareTo((BigDecimal) map.get(str)) > 0) {
                    extremeValue = (BigDecimal) map.get(str);
                }
            }
        }
        return extremeValue;
    }

    /**
     * @param str        不为空
     * @param resultList 肯定含有str值
     * @return
     */
    public BigDecimal maxBigDecimal(String str, List<Map<String, Object>> resultList) {
        BigDecimal maxV = null;
        for (Map<String, Object> map : resultList) {
            if (map.get(str) != null && !"".equals(map.get(str))) {
                if (maxV == null) {
                    maxV = (BigDecimal) map.get(str);
                } else if (maxV.compareTo((BigDecimal) map.get(str)) < 0) {
                    maxV = (BigDecimal) map.get(str);
                }
            }
        }
        return maxV;
    }

    /**
     * @param str        不为空
     * @param resultList 肯定含有str值
     * @return
     */
    public BigDecimal minBigDecimal(String str, List<Map<String, Object>> resultList) {
        BigDecimal minV = null;
        for (Map<String, Object> map : resultList) {
            if (map.get(str) != null && !"".equals(map.get(str))) {
                if (minV == null) {
                    minV = (BigDecimal) map.get(str);
                } else if (minV.compareTo((BigDecimal) map.get(str)) > 0) {
                    minV = (BigDecimal) map.get(str);
                }
            }
        }
        return minV;
    }
}
