/**
 * Copyright  2019-2029 联通集团财务有限公司版权所有。
 */
package web;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.spi.HttpServerProvider;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.URI;

/**
 * @author: suxiaolei
 * @date: 2019/6/22
 */
public class HttpServerDemo {
    public static void main(String[] args) throws IOException {
        HttpServerProvider provider = HttpServerProvider.provider();
        HttpServer httpserver =provider.createHttpServer(new InetSocketAddress(8888), 100);//监听端口19017,能同时接受100个请求
        httpserver.createContext("uap/Payment/SinglePayQueryResult", new MyResponseHandler());
        httpserver.setExecutor(null);
        httpserver.start();
        System.out.println("server started");



    }
    public static class MyResponseHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange httpExchange) throws IOException {
            System.out.println("receive");
            String requestMethod = httpExchange.getRequestMethod();
            if(!requestMethod.equalsIgnoreCase("GET")){//客户端的请求是get方法
                //设置服务端响应的编码格式，否则在客户端收到的可能是乱码
                Headers responseHeaders = httpExchange.getResponseHeaders();
                responseHeaders.set("Content-Type", "text/html;charset=utf-8");
                //在这里通过httpExchange获取客户端发送过来的消息
                //URI url = httpExchange.getRequestURI();
                //InputStream requestBody = httpExchange.getRequestBody();

                String response = "this is server";

                httpExchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, response.getBytes("UTF-8").length);

                OutputStream responseBody = httpExchange.getResponseBody();
                OutputStreamWriter writer = new OutputStreamWriter(responseBody, "UTF-8");
                writer.write(response);
                writer.close();
                responseBody.close();
            }else {
                InputStream requestBody = httpExchange.getRequestBody();
                Headers requestHeaders = httpExchange.getRequestHeaders();
                URI requestURI = httpExchange.getRequestURI();
                Headers responseHeaders = httpExchange.getResponseHeaders();
                responseHeaders.set("Content-Type", "application/json;charset=utf-8");
               // String response = "this is server";


                String response = "\"name\": \"123\",\n" +
                        "    \"acctName\": \"联通郭芳\",\n" +
                        "    \"currCd\": \"CNY\",\n" +
                        "    \"acctBal\": 8968078.16,\n" +
                        "    \"avlBal\": 4642606.1,\n" +
                        "    \"freezeBal\": 4098000.34";
                httpExchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, response.getBytes("UTF-8").length);


                OutputStream responseBody = httpExchange.getResponseBody();
                OutputStreamWriter writer = new OutputStreamWriter(responseBody, "UTF-8");
                writer.write(response);
                writer.close();
                responseBody.close();
            }

        }
    }
}
