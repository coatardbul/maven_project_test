package network;

import baseJava.java8.JsonUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONArray;
import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;
import groovy.lang.GroovyShell;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.apache.poi.util.StringUtil;
import org.junit.Test;
import scala.annotation.meta.param;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static org.apache.commons.codec.binary.Base64.encodeBase64;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class dsjkdsl {
    @Test
    public void tes() {


        String curlCommand = "curl 'http://project.cctegitc.com:808/project/api/project/team/SeANJuV5/items/graphql?t=userManhours-THNWgLoBfjt1p4Ie-3yuRJz3o' \n" +
                "  -H 'Accept: application/json, text/plain, */*' \n" +
                "  -H 'Accept-Language: zh' \n" +
                "  -H 'Cache-Control: no-cache' \n" +
                "  -H 'Connection: keep-alive' \n" +
                "  -H 'Content-Type: application/json;charset=UTF-8' \n" +
                "  -H 'Cookie: language=zh; ones-uid=3yuRJz3o; ones-lt=1RJOlKQehaVgYdOc75l4BHnrB1fjPzfN0SmorY7SjeOGJPRAVCuIzbvoXIGQyjiT; timezone=Asia/Shanghai; ct=ff810bf2edb3e9692822bf68befc32b0c003fe537bc8f27c3ecbb541aadebb5d' \n" +
                "  -H 'Origin: http://project.cctegitc.com:808' \n" +
                "  -H 'Pragma: no-cache' \n" +
                "  -H 'Referer: http://project.cctegitc.com:808/project/' \n" +
                "  -H 'User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/119.0.0.0 Safari/537.36' \n" +
                "  -H 'X-CSRF-TOKEN: ff810bf2edb3e9692822bf68befc32b0c003fe537bc8f27c3ecbb541aadebb5d' \n" +
                "  --data-raw '{\"query\":\"n    query FetchUserManhours {n      manhours(n        filter: $manhourFiltern        orderBy: $manhoursOrderByn      ) {n        keyn        hoursn        startTime(unit: ONESDATE)n        descriptionn        typen        owner {n          uuidn          avatarn          namen          emailn        }n      }n    }n  \",\"variables\":{\"manhourFilter\":{\"task\":{\"uuid_in\":[\"THNWgLoBfjt1p4Ie\"]},\"owner_in\":[\"3yuRJz3o\"]},\"manhoursOrderBy\":{\"startTime\":\"DESC\",\"createTime\":\"DESC\"}}}' \n" +
                "  --compressed \n" +
                "  --insecure";
        try {
            String[] curlSplit = curlCommand.split("\n");
            String url = curlSplit[0].split("'")[1].trim();
            int queryIndex = url.indexOf("?");
            //请求里面的参数
            String paramUrl = url.substring(queryIndex + 1, url.length());
            Map<String, String> paramurlMap = new HashMap<String, String>();
            if (StringUtils.isNotBlank(paramUrl)) {
                String[] paramStrs = paramUrl.split("&");
                for (String paramStr : paramStrs) {
                    String[] split1 = paramStr.trim().split("=");
                    paramurlMap.put(split1[0], split1[1]);
                }
            }
            //请求头
            List<String> headList = Stream.of(curlSplit).filter(item -> item.trim().startsWith("-H")).collect(Collectors.toList());
            Map<String, String> headMap = new HashMap<String, String>();
            if (headList != null && headList.size() > 0) {
                for (String headLineStr : headList) {
                    String headStr = headLineStr.split("'")[1];
                    String[] split = headStr.split(":");
                    headMap.put(split[0], split[1].trim());
                }
            }

            //请求对象
            List<String> bodyList = Stream.of(curlSplit).filter(item -> item.trim().startsWith("--data")).collect(Collectors.toList());
            String bodyResultStr = "";
            if (bodyList != null && bodyList.size() > 0) {
                bodyResultStr = bodyList.get(0).split("'")[1];
            }
            Map bodyMap = new HashMap<String, Object>();
            bodyMap = JsonUtil.readToValue(bodyResultStr, Map.class);
            System.out.println(12312);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 1为阳
     */
    @Test
    public void test() throws ClassNotFoundException, MalformedURLException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        String code = "public class MyCode { public String execute() { return \"123232\"; } }";

        String fileName = "MyCode.java";
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(code);
        } catch (IOException e) {
            e.printStackTrace();
        }
        compiler.run(null, null, null, fileName);

        String classPath = Paths.get(".").toAbsolutePath().normalize().toString();
        // 创建类加载器并加载编译后的类
        URLClassLoader classLoader = URLClassLoader.newInstance(new URL[]{Paths.get(classPath).toUri().toURL()});
        Class<?> clazz = classLoader.loadClass("MyCode");
        // 获取需要执行的方法
        Method method = clazz.getMethod("execute");
        // 创建实例并执行方法
        Object instance = clazz.getConstructor().newInstance();
        Object invoke = method.invoke(instance);
        int i = 12312;
    }

    @Test
    public void testsfsfsd() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        GroovyClassLoader loader = new GroovyClassLoader();
        // java代码
        String java = "public class Test01 {\n" +
                "                    public int add(double a, double b) {\n" +
                "                       double sum = a + b;\n" +
                "                       System.out.println(\"Script sum=\" + sum); \n" +
                "                       return sum.intValue();\n" +
                "                    }\n" +
                "                  \n" +
                "                }";
        Class scriptClass = loader.parseClass(java);
        GroovyObject scriptInstance = (GroovyObject) scriptClass.getDeclaredConstructor().newInstance();
        Object result = scriptInstance.invokeMethod("add", new Object[]{13.1, 20.1});
        System.out.println("Groovy result=" + result);

    }

    @Test
    public void tessdfsfst() {
        String text = "这里有一些变量 [[[variable1]], 和一些其他的变量 [[variable2]] 在这个字符串中。";
        Pattern pattern = Pattern.compile("\\[\\[(.+?)\\]\\]");
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            System.out.println("找到变量: " + matcher.group(1));
        }
    }
    @Test
    public void testdsfsd() {
        String host = "https://gjbsb.market.alicloudapi.com";
        String path = "/ocrservice/advanced";
        String method = "POST";
        String appcode = "46bb5bf04acf450eb90260f292f647a9";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        //根据API的要求，定义相对应的Content-Type
        headers.put("Content-Type", "application/json; charset=UTF-8");
        Map<String, String> querys = new HashMap<String, String>();
        String bodys = "{\"img\":\"\",\"url\":\"http://43.142.151.181:9000/file-system/Xnip2023-12-05_17-59-54.jpg?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=2CAAjWaWe7SKI44b1yqt%2F20231205%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20231205T101235Z&X-Amz-Expires=604800&X-Amz-SignedHeaders=host&X-Amz-Signature=b66ac93d33ee69b87a5a9a5fbfa9c1f8ecdfd534ed788d5e6a6a82fae1e2fede\",\"prob\":false,\"charInfo\":false,\"rotate\":false,\"table\":false}";


        try {
            /**
             * 重要提示如下:
             * HttpUtils请从
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
             * 下载
             *
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             */
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
            System.out.println(response.toString());
            //获取response的body
            System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void ddddd() throws ScriptException {

        String code = "class MyClass { public static void main(String[] args) { System.out.println(\"Hello, world!\") } }";
        GroovyShell shell = new GroovyShell();
        shell.evaluate(code);


        int i;
    }


}
