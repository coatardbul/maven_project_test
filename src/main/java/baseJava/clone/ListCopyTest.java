package baseJava.clone;
import common.entity.ImportCrmProduct;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class ListCopyTest {



    public <T> List<T> test1() {
//        ReflexTest reflexTest = new ReflexTest();
//        ImportCrmProduct importCrmProduct = reflexTest.getObject();
//        List<ImportCrmProduct> baseJava.list = new ArrayList<ImportCrmProduct>();
//        for (int i = 0; i < 19; i++) {
//            baseJava.list.add(reflexTest.getObject());
//        }
        return  null;

    }

    @Test
    public void test2() {
        try {
            List<ImportCrmProduct> list = test1();
            List<ImportCrmProduct> listCopy =ListCopyUtil.deepCopy(list);
            System.out.println(list.size());
            System.out.println(listCopy.size());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
