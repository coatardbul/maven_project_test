package wide;

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
        String result = "";
        // 获取JS执行引擎
        ScriptEngine se = new ScriptEngineManager().getEngineByName("javascript");
        FileReader fileReader = new FileReader( "/Users/coat/bbb.js");

        se.eval(fileReader);
        // 是否可调用
        if (se instanceof Invocable) {
            Invocable in = (Invocable) se;
            //timestamp
            long l = System.currentTimeMillis();
            Object ccc = in.invokeFunction("ccc", "Uu0KfOB8iUP69d3c:" + l);
            Object wordsToBytes = in.invokeFunction("wordsToBytes", ccc);
            //token
            result = (String) in.invokeFunction("bytesToHex", wordsToBytes);

            System.out.println(l);
            System.out.println(result);
        }


    }
}
