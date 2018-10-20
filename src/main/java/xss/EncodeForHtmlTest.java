import org.owasp.encoder.Encode;

public class EncodeForHtmlTest {
    public static void main(String[] args) {
        String str="<img src=1 . onerror=alter(2)>";
        System.out.println(Encode.forHtml(str));
    }
}
