
package annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author: suxiaolei
 * @date: 2019/7/26
 */
@Target(value={ElementType.FIELD,ElementType.METHOD})
@Retention(value= RetentionPolicy.RUNTIME)
public @interface Hello {
    String value();
}
