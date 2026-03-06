package zmkg;

import baseJava.java8.BusinessException;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.dingtalk.api.response.OapiRobotSendResponse;
import com.taobao.api.ApiException;
import org.apache.commons.lang.StringUtils;
import util.DateTimeUtil;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * Note:
 * <p>
 * Date: 2024/4/23
 *
 * @author Su Xiaolei
 */
public class sfdjlks {


    private static final String url="https://oapi.dingtalk.com/robot/send?access_token=f1078ec6be9eeb0211ebe6ee26571a90482175c6d5251667162258e2c30b8c33";


    public static void main(String[] args) throws ApiException {
        sfdjlks sd=new sfdjlks();
        DingDingMsgBo dingDingMsgBo=new DingDingMsgBo();
        dingDingMsgBo.setMsgType("text");
        dingDingMsgBo.setContent("hello,my friend");
        sd.sendDingDingMsg(dingDingMsgBo);
    }
    private  String sendDingDingMsg(DingDingMsgBo dto) throws ApiException {


        //发送消息
        DingTalkClient client = new DefaultDingTalkClient(url);
        OapiRobotSendRequest request = new OapiRobotSendRequest();
        request.setMsgtype(dto.getMsgType());
        if("text".equals(dto.getMsgType())){
            OapiRobotSendRequest.Text text = new OapiRobotSendRequest.Text();
            if(!StringUtils.isNotBlank(dto.getContent())){
                throw new BusinessException("发送的消息不能为空");
            }
            text.setContent( dto.getContent());
            request.setText(text);
        }
        if("link".equals(dto.getMsgType())){
            OapiRobotSendRequest.Link link = new OapiRobotSendRequest.Link();
            if(!StringUtils.isNotBlank(dto.getTitle())
                    ||!StringUtils.isNotBlank(dto.getText())
                    ||!StringUtils.isNotBlank(dto.getMessageUrl())

            ){
                throw new BusinessException("发送的消息不能为空");
            }
            link.setMessageUrl(dto.getMessageUrl());
            link.setPicUrl("");
            link.setTitle(dto.getTitle());
            link.setText(dto.getText());
            request.setLink(link);
        }

        if("markdown".equals(dto.getMsgType())){
            OapiRobotSendRequest.Markdown markdown = new OapiRobotSendRequest.Markdown();
            if(!StringUtils.isNotBlank(dto.getTitle())
                    ||!StringUtils.isNotBlank(dto.getText())
            ){
                throw new BusinessException("发送的消息不能为空");
            }

            markdown.setTitle(dto.getTitle());
            markdown.setText(dto.getText());
            request.setMarkdown(markdown);
        }
        OapiRobotSendResponse response = client.execute(request);
        System.out.println("钉钉消息结果"+response.getMsg());
        return response.getMsg();
    }

}
