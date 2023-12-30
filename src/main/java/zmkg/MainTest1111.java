package zmkg;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONUtil;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Token认证测试
 *
 *  认证过程主要采用RSA非对称加密算法
 *
 * @author
 */
public class MainTest1111 {
    /**
     * 模拟缓存服务
     */
    private static final Map<String,String> SYSTEM_CACHE = new HashMap <>();

    /**
     * ecology系统发放的授权许可证(appid)
     */
    private static final String APPID = "62acf88c-55d0-465c-b08d-99cd36271231";

    public static void main(String[] args) {
        testRestful("http://10.10.10.40","/api/system/appmanage/route",null);
    }



    public static final String
            PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDVbvTa4rSLFoj2SHMCZBbpqLskLx+as8WmBQ34QYW/huF6QKMdnxj6Q8yo7xPdTjjnMbdivuLEtlh+QS2ZzvLrGqfFYOX1FiTJift1t8rOHVsn8RkVtXF1dNuCLO+W6h+MLt1mJs7Wo4/XVnCbMnQteMkY5Lpnx5FvW05i8blcvQIDAQAB";

    public static final String PRIVATE_KEY="MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBANVu9NritIsWiPZIcwJkFumouyQvH5qzxaYFDfhBhb+G4XpAox2fGPpDzKjvE91OOOcxt2K+4sS2WH5BLZnO8usap8Vg5fUWJMmJ+3W3ys4dWyfxGRW1cXV024Is75bqH4wu3WYmztajj9dWcJsydC14yRjkumfHkW9bTmLxuVy9AgMBAAECgYAdYBQbPDCVJBjdrMMHhTcSGcMIC+RRqoR9gRyXGo2J+sp1xQeuQAN0DnjQl3cocmeqilEvKg6T/HTZi6b3EGCmcpsrAGhI8Xajn5FLbBD8wUoZo5Abzf9B/4VK7WyBaIkPfDPooJvqqAYKvDRtONPRwhkLjIYPoYdtyl85LZCwEQJBAPoRJrJeBqVSwAw4kJA60o7ZPBBiAgxbLCWkcOBvu+hMJnHysQQV6LDG4kpw0k0sVQpPvYdWlRwsQwaPBnJ/j28CQQDaf00uuLWDyoN38glxrtaYpM3cH+D35n4FGmPBOEEJebYxEhPNG9S/Vo8Jhn+/5rUvKSlBar5cLlwS/MqYJgCTAkBqEF4+Zz0gYAHO7Sr70cc/CYq9VCSUQxNtf9rUhu/Hbo0b5ZkJJ5XDbQfMFSBN1VDYh8vWnHWwNmHTZVSRueMHAkEAq7f0vs1nyoX0QOY1yeSwvxJ2v5NGSU4aqIQO9iHx6zJ+n54ndcFzuFSc4c2UxQraRpBA3iq4JNZCgqGtz1m+hQJBAKRusQ22+rU9AkpeNk/ANZkwFUJmOujRUhbD2adiG1fovHh5Oo3gQGJchWiichnouVrAO+8FGrCQ8S4EptfdeUs=";
    @Test
    public void testR1232(){
        RSA rsa = new RSA();
        String publicKey = rsa.getPublicKeyBase64();
        String privateKey = rsa.getPrivateKeyBase64();

        System.out.println("公钥："+publicKey);
        System.out.println("私钥："+privateKey);


    }
    /**
     * 第一步：
     *
     * 调用ecology注册接口,根据appid进行注册,将返回服务端公钥和Secret信息
     */
    @Test
    public  void test222333(String address){



        // 客户端RSA私钥
        SYSTEM_CACHE.put("LOCAL_PRIVATE_KEY",PRIVATE_KEY);
        // 客户端RSA公钥
        SYSTEM_CACHE.put("LOCAL_PUBLIC_KEY",PUBLIC_KEY);

        //调用ECOLOGY系统接口进行注册
        String data = HttpRequest.post(address + "/api/ec/dev/auth/regist")
                .header("appid",APPID)
                .header("cpk",PUBLIC_KEY)
                .timeout(2000)
                .execute().body();

        // 打印ECOLOGY响应信息
        System.out.println("注册返回结果："+data);
        Map<String,Object> datas = JSONUtil.parseObj(data);

        //ECOLOGY返回的系统公钥
        SYSTEM_CACHE.put("SERVER_PUBLIC_KEY",StrUtil.nullToEmpty((String)datas.get("spk")));
        //ECOLOGY返回的系统密钥
        SYSTEM_CACHE.put("SERVER_SECRET",StrUtil.nullToEmpty((String)datas.get("secrit")));
    }



    /**
     * 第二步：
     *
     * 通过第一步中注册系统返回信息进行获取token信息
     */
    public static Map<String,Object> testGetoken(String address){
        // 从系统缓存或者数据库中获取ECOLOGY系统公钥和Secret信息
        String secret = SYSTEM_CACHE.get("SERVER_SECRET");
        String spk = SYSTEM_CACHE.get("SERVER_PUBLIC_KEY");

        // 如果为空,说明还未进行注册,调用注册接口进行注册认证与数据更新
        if (Objects.isNull(secret)||Objects.isNull(spk)){
//            testRegist(address);
            // 重新获取最新ECOLOGY系统公钥和Secret信息
            secret = SYSTEM_CACHE.get("SERVER_SECRET");
            spk = SYSTEM_CACHE.get("SERVER_PUBLIC_KEY");
        }

        // 公钥加密,所以RSA对象私钥为null
        RSA rsa = new RSA(null,spk);
        //对秘钥进行加密传输，防止篡改数据
        String encryptSecret = rsa.encryptBase64(secret,CharsetUtil.CHARSET_UTF_8,KeyType.PublicKey);

        //调用ECOLOGY系统接口进行注册
        String data = HttpRequest.post(address+ "/api/ec/dev/auth/applytoken")
                .header("appid",APPID)
                .header("secret",encryptSecret)
                .header("time","3600")
                .execute().body();

        System.out.println("testGetoken()："+data);
        Map<String,Object> datas = JSONUtil.parseObj(data);

        //ECOLOGY返回的token
        // TODO 为Token缓存设置过期时间
        SYSTEM_CACHE.put("SERVER_TOKEN",StrUtil.nullToEmpty((String)datas.get("token")));

        return datas;
    }

    /**
     * 第三步：
     *
     * 调用ecology系统的rest接口，请求头部带上token和用户标识认证信息
     *
     * @param address ecology系统地址
     * @param api rest api 接口地址(该测试代码仅支持GET请求)
     * @param jsonParams 请求参数json串
     *
     * 注意：ECOLOGY系统所有POST接口调用请求头请设置 "Content-Type","application/x-www-form-urlencoded; charset=utf-8"
     */
    public static String testRestful(String address,String api,String jsonParams){

        //ECOLOGY返回的token
        String token= SYSTEM_CACHE.get("SERVER_TOKEN");
        if (StrUtil.isEmpty(token)){
            token = (String) testGetoken(address).get("token");
        }

        String spk = SYSTEM_CACHE.get("SERVER_PUBLIC_KEY");
        //封装请求头参数
        RSA rsa = new RSA(null,spk);
        //对用户信息进行加密传输,暂仅支持传输OA用户ID
        String encryptUserid = rsa.encryptBase64("1",CharsetUtil.CHARSET_UTF_8,KeyType.PublicKey);

        //调用ECOLOGY系统接口
        String data = HttpRequest.get(address + api)
                .header("appid",APPID)
                .header("token",token)
                .header("userid",encryptUserid)
                .body(jsonParams)
                .execute().body();
        System.out.println("testRestful()："+data);
        return data;
    }


}
