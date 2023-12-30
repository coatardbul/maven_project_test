package pic;

import net.sourceforge.tess4j.*;
import java.io.File;

public class TesseractExample {
    public static void main(String[] args) {
        File image = new File("path/to/your/image.jpg"); // 替换为你的图片路径
        ITesseract instance = new Tesseract();
        instance.setDatapath("path/to/tessdata/");  // 替换为你的tessdata路径

        try {
            String result = instance.doOCR(image);
            System.out.println(result);
        } catch (TesseractException e) {
            System.err.println(e.getMessage());
        }
    }
}
