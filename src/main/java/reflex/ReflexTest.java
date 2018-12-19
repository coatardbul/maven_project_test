package reflex;

import entity.ImportCrmProduct;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ReflexTest {
    public static void main(String[] args) {
        ImportCrmProduct importCrmProduct = new ImportCrmProduct();
        importCrmProduct.setTradeTime("213123");
        //Object object=ReflexUtil.readValueByName("tradeTime",importCrmProduct);
        //System.out.println(object.toString());
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
        Map<String,Object> map=ReflexUtil.readObjectAllAttribute(importCrmProduct);
        System.out.println(map);

        ImportCrmProduct  i =new ImportCrmProduct();
        ReflexUtil.setMapToObject(i,map);
        System.out.println(i.getIp());
        int num = ReflexUtil.compareTwoObject(importCrmProduct, importCrmProductTemp);
        System.out.println(num);

    }

    public static ImportCrmProduct getObject() {
        ImportCrmProduct importCrmProduct = new ImportCrmProduct();
        importCrmProduct.setDateId((long) new Random().nextInt());
        importCrmProduct.setProdId("h1ello");
        importCrmProduct.setAppType("12");
        importCrmProduct.setResStatus("1");
        importCrmProduct.setTradeTime("2018-09-08 12:12");
        importCrmProduct.setProdName("qunidayede");
        return importCrmProduct;
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
    public  void  testSetMapToObject() throws IllegalAccessException, InstantiationException {
        ImportCrmProduct importCrmProduct = new ImportCrmProduct();
        Map<String,Object> map=new HashMap<>();
        map.put("prodId","hello");
//        importCrmProduct.setProdId("h1ello");
//        importCrmProduct.setAppType("12");
//        importCrmProduct.setResStatus("1");
//        importCrmProduct.setTradeTime("2018-09-08 12:12");
//        importCrmProduct.setProdName("qunidayede");
       // ReflexUtil.singleSetMapToObject(importCrmProduct,map);
        System.out.println(importCrmProduct.toString());

    }


}
