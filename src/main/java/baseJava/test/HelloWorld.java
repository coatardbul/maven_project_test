package baseJava.test;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class HelloWorld {



    public static void main(String[] args) throws NoSuchMethodException, ScriptException, FileNotFoundException {

        String heXinStr = getHeXinStr();
        System.out.println(heXinStr);
    }

    public static String getHeXinStr() throws ScriptException, NoSuchMethodException, FileNotFoundException {
        String result="";
        // 获取JS执行引擎
        ScriptEngine se = new ScriptEngineManager().getEngineByName("javascript");
        FileReader fileReader = new FileReader("/Users/coatardbul/Desktop/sb2.js");

        se.eval(fileReader);
        // 是否可调用
        if (se instanceof Invocable) {
            Invocable in = (Invocable) se;
            result = (String) in.invokeFunction("EMTradeEncrypt.encrypt","144590");
        }
        return  result;
    }
}