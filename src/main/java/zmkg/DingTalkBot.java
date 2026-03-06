package zmkg;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import com.google.gson.Gson;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class DingTalkBot {

    public static void main(String[] args) {
        try {
            DingTalkBot dingTalkBot=new DingTalkBot();
            dingTalkBot.sendMsgProcess("1234567890");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void sendMsgProcess(String codeStr) throws Exception {
        String webhook="https://oapi.dingtalk.com/robot/send?access_token=4cf2c11beff7387252e4581d2282ea37deb70140222648a260ce917b4219cedd";
        String secret = "你的机器人密钥"; // 如果机器人安全设置里没有设置签名，则不需要处理密钥

        // 构建消息内容
        Map<String, Object> message = new HashMap<>();
        message.put("msgtype", "text");
        Map<String, String> textContent = new HashMap<>();
        textContent.put("content", "hello"+codeStr);
        message.put("text", textContent);

        Gson gson = new Gson();
        String jsonMessage = gson.toJson(message);

        // 发送HTTP POST请求
        sendPostRequest(webhook, jsonMessage);
    }


    private  void sendPostRequest(String webhook, String jsonMessage) throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(webhook);
        httpPost.addHeader("Content-Type", "application/json; charset=utf-8");

        StringEntity requestEntity = new StringEntity(jsonMessage, StandardCharsets.UTF_8);
        httpPost.setEntity(requestEntity);

        HttpResponse response = httpClient.execute(httpPost);
        HttpEntity responseEntity = response.getEntity();

        if (responseEntity != null) {
            String responseString = EntityUtils.toString(responseEntity, StandardCharsets.UTF_8);
            System.out.println("Response: " + responseString);
        }

        httpClient.close();
    }
}
