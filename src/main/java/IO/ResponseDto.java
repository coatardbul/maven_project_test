/**
 * Copyright  2019-2029 联通集团财务有限公司版权所有。
 */
package IO;



/**
 * @author: suxiaolei
 * @date: 2019/6/24
 */

public class ResponseDto <T>{
    /**
     * 响应头
     */
    private ResponseHeadDro head;
    /**
     * 响应体
     */
    private  T body;

    @Override
    public String toString() {
        return "ResponseDto{" +
                "head=" + head +
                ", body=" + body +
                '}';
    }

    public ResponseHeadDro getHead() {
        return head;
    }

    public void setHead(ResponseHeadDro head) {
        this.head = head;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }
}
