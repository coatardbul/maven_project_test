package baseJava.reflex;

import common.entity.ImportCrmProduct;
import org.apache.poi.ss.formula.functions.T;
import org.junit.Test;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ReflexTest {
    public static final String DATE_TIME = "yyyy-MM-dd HH:mm:ssSSSSSS";

    public static void main(String[] args) {
        String ss=null;


        LocalDateTime now = LocalDateTime.now();
        long l = System.nanoTime();
        System.out.println(l);
        System.out.println(now);
        System.out.println(now.getMinute());
        System.out.println(now.getSecond());
        System.out.println(now.getNano());
        System.out.println(now.format(DateTimeFormatter.ofPattern(DATE_TIME)));
    }

    @Test
    public void testMain() {
//        int i=compareTest();
//        System.out.println(i);
        ImportCrmProduct importCrmProduct = getObject();
        //ReflexUtil.readAttributeValuePrint(importCrmProduct,importCrmProduct.getClass());
    }

    @Test
    public void testCompareTest() throws IllegalAccessException {
        ImportCrmProduct importCrmProduct = new ImportCrmProduct();
        importCrmProduct.setProdName("sb");
        importCrmProduct.setDateId(1123l);
        importCrmProduct.setProdId("h1ello");
        importCrmProduct.setAppType("12");
        importCrmProduct.setResStatus("1");
        importCrmProduct.setTradeTime("2018-09-08 12:12");

        ImportCrmProduct importCrmProductTemp = new ImportCrmProduct();
        importCrmProductTemp.setProdName("sb");
        importCrmProductTemp.setDateId(1123l);
        importCrmProductTemp.setProdId("h1ello");
        importCrmProductTemp.setAppType("12");
        importCrmProductTemp.setResStatus("1");
        importCrmProductTemp.setTradeTime("2018-09-08 12:12");
        importCrmProduct.setUserId(13123l);
        importCrmProduct.setIp("127.0.0.1");
        Map<String, Object> map = ReflexUtil.readObjectAllAttribute(importCrmProduct);
        System.out.println(map);
        ReflexUtil.singleReadAttributeValuePrint(importCrmProduct, importCrmProduct.getClass());
//        ImportCrmProduct  i =new ImportCrmProduct();
//        ReflexUtil.setMapToObject(i,map);
//        System.out.println(i.getIp());
//        int num = ReflexUtil.compareTwoObject(importCrmProduct, importCrmProductTemp);
//        System.out.println(num);

    }

    public static ImportCrmProduct getObject() {
        ImportCrmProduct importCrmProduct = new ImportCrmProduct();
        importCrmProduct.setDateId((long) new Random().nextInt());
        importCrmProduct.setProdId("h1ello");
        importCrmProduct.setAppType("12");
        importCrmProduct.setResStatus("1");
        importCrmProduct.setTradeTime("2018-09-08 12:12");
        importCrmProduct.setProdName("qunidayede");


        //    xx(importCrmProduct);


        return importCrmProduct;
    }

    public static void xx(T t) {
        //得到所有属性
        Field[] fields = t.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {//遍历
            //得到属性
            Field field = fields[i];
            //打开私有访问
            field.setAccessible(true);
            //获取属性
            String name = field.getName();
            //获取属性值
            // java.lang.Object value = field.get(t);

        }
    }

    @Test
    public void testReadValueByName() {
        ImportCrmProduct i = getObject();
        Object o = ReflexUtil.readValueByName("tradeTime", i);
        System.out.println(o.toString());
    }

    @Test
    public void testReadAttributeCount() {
        ImportCrmProduct i = getObject();
        Object o = ReflexUtil.readAttributeCount(i);
        System.out.println(o.toString());
        int num = 0;
        num += ReflexUtil.singleReadAttributeCount(new ImportCrmProduct().getClass());
        num += ReflexUtil.singleReadAttributeCount(new ImportCrmProduct().getClass());
        System.out.println(num);
    }

    @Test
    public void testSetAttrubteValue() {
        ImportCrmProduct i = getObject();
        i.setProdName("ssss");
        System.out.println(i.toString());
        // ReflexUtil.singleSetAttributeValue("prodName","bbbbb",i,i.getClass());
        ReflexUtil.setAttributeValue("prodName", "bbbbb", i);
        ReflexUtil.setAttributeValue("regAppTime", "123go", i);
        System.out.println(i.toString());
    }

    @Test
    public void testReadAttrubtePrint() {
        ImportCrmProduct i = getObject();
        ReflexUtil.setAttributeValue("regAppTime", "123go", i);
        ReflexUtil.readAttributeValuePrint(i);
    }

    @Test
    public void testSetMapToObject() throws IllegalAccessException, InstantiationException {
        ImportCrmProduct importCrmProduct = new ImportCrmProduct();
        Map<String, Object> map = new HashMap<>();
        map.put("prodId", "hello");
//        importCrmProduct.setProdId("h1ello");
//        importCrmProduct.setAppType("12");
//        importCrmProduct.setResStatus("1");
//        importCrmProduct.setTradeTime("2018-09-08 12:12");
//        importCrmProduct.setProdName("qunidayede");
        // ReflexUtil.singleSetMapToObject(importCrmProduct,map);
        System.out.println(importCrmProduct.toString());

    }

    @Test
    public void test2() throws ClassNotFoundException {
        ImportCrmProduct importCrmProduct = new ImportCrmProduct();
        importCrmProduct.setIp("dsfsdfsd");
        Map<String, Object> map = new HashMap<>();
        map.put("ordid", 1231212);
        map.put("id", 12313L);
        map.put("str", "hello");
        System.out.println(map.get("id").getClass());
        System.out.println(Class.forName("java.lang.Long").getClass());


    }

    @Test
    public void demo1() throws Exception {
        //获取字节码对象
//        Class<ReflexTest> clazz = (Class<ReflexTest>) Class.forName("common.entity.ImportCrmProduct");
//
//        //获取一个对象
//        Constructor con =  clazz.getConstructor();
//        ImportCrmProduct m = (ImportCrmProduct) con.newInstance();
//        baseJava.String[] s = new baseJava.String[]{"aa","bb"};
//        //获取Method对象
//        Method method = clazz.getMethod("test", baseJava.String[].class);
//        //调用invoke方法来调用
//        method.invoke(m, s);
    }


}
