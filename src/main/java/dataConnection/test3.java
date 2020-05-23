package dataConnection;

import common.entity.ImportCrmProduct;
import baseJava.reflex.ReflexUtil;

public class test3 {
    public static void main(String[] args) {
        ImportCrmProduct importCrmProduct=new ImportCrmProduct();
        importCrmProduct.setAppType("222");
        ImportCrmProduct importCrmProductTemp=new ImportCrmProduct();
        importCrmProductTemp.setAppType("222");
        importCrmProductTemp.setProdId("23");
       int s= ReflexUtil.compareTwoObject(importCrmProduct,importCrmProductTemp);
        System.out.println(s);
    }
}
