
package enumUtil;

import org.junit.Test;

/**
 * @author: suxiaolei
 * @date: 2019/7/2
 */
public class enumTest {


    @Test
    public void tlekj() {
        for (CommonResult c : CommonResult.values()) {
            System.out.println(c.toString());
        }
    }
}
