
package annotation;

import java.lang.reflect.Method;

/**
 * @author: suxiaolei
 * @date: 2019/7/26
 */
public class Test {
    @Hello("sb123")
    public static void main(String[] args) throws NoSuchMethodException {
        Class c = Test.class;
        Method main = c.getMethod( "main", String[].class);
        Hello annotation = main.getAnnotation(Hello.class);
    }
}
