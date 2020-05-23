
package common.enumInfo;


import java.time.LocalDateTime;

/**
 * @author: suxiaolei
 * @date: 2019/7/2
 */
public class enumTest {

    public static final String DATE_TIME = "yyyy-MM-dd HH:mm:ssSSSSSS";

    public static void main(String[] args) {
        System.out.println(LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern(DATE_TIME)));
    }
}
