package network;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.junit.Test;

import java.io.File;

/**
 * <p>
 * Note:
 * <p>
 * Date: 2023/12/5
 *
 * @author Su Xiaolei
 */
public class sdfsd {

    public static String readText(String filePath) throws Exception {
        File file = new File(filePath);
        PDDocument doc = PDDocument.load(file);
        PDFTextStripper textStripper = new PDFTextStripper();
        String str = "";
        for (int i = 1; i <= doc.getNumberOfPages(); i++) {
            textStripper.setStartPage(i);
            textStripper.setEndPage(i);
            String s = textStripper.getText(doc);
            System.out.println(s);
            str = s.replaceAll("\r\n", ";");
        }
        doc.close();
        return str;
    }

    @Test
    public void test() throws Exception {
        String s = readText("/Users/coatardbul/Desktop/钻探技术岗_王能_国聘_1205170727.pdf");
        System.out.println(s);

    }
}
