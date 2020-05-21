package xss;

import entity.ImportCrmProduct;
import org.owasp.encoder.Encode;

import java.util.ArrayList;
import java.util.List;

public class EncodeForHtmlTest {
    public static void main(String[] args) {
        int x=1;
        double y=3;
        System.out.println(x/y);
    }
}
