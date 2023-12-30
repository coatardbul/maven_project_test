package baseJava.java8;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * <p>
 * Note:
 * <p>
 * Date: 2022/11/20
 *
 * @author Su Xiaolei
 */
public class ddfgfd {

    public static void main(String[] args) throws FileNotFoundException, ScriptException, NoSuchMethodException {
        String str="width: 34%; animation-duration: 3s";
        int i = str.indexOf("%");
        str = str.substring(0,i);
       str= str.replace("width:","").trim();
        System.out.println(Integer.valueOf(str));

    }
}
