
package IO;



/**
 * @author: suxiaolei
 * @date: 2019/6/24
 */

public class ResponseHeadDro {
    /**
     * 平台应答码
     */

    private String resCd;
    /**
     * 平台应答信息
     */

    private String resMsg;
    /**
     * 平台应答日期
     */

    private String resDt;
    /**
     * 平台应答时间
     */

    private String resTm;
    /**
     * 平台应答流水
     */

    private String resSeqNum;
    /**
     * 请求方流水号
     */

    private String reqSeqNum;

    @Override
    public String toString() {
        return "ResponseHeadDro{" +
                "resCd='" + resCd + '\'' +
                ", resMsg='" + resMsg + '\'' +
                ", resDt='" + resDt + '\'' +
                ", resTm='" + resTm + '\'' +
                ", resSeqNum='" + resSeqNum + '\'' +
                ", reqSeqNum='" + reqSeqNum + '\'' +
                '}';
    }

    public String getResCd() {
        return resCd;
    }

    public void setResCd(String resCd) {
        this.resCd = resCd;
    }

    public String getResMsg() {
        return resMsg;
    }

    public void setResMsg(String resMsg) {
        this.resMsg = resMsg;
    }

    public String getResDt() {
        return resDt;
    }

    public void setResDt(String resDt) {
        this.resDt = resDt;
    }

    public String getResTm() {
        return resTm;
    }

    public void setResTm(String resTm) {
        this.resTm = resTm;
    }

    public String getResSeqNum() {
        return resSeqNum;
    }

    public void setResSeqNum(String resSeqNum) {
        this.resSeqNum = resSeqNum;
    }

    public String getReqSeqNum() {
        return reqSeqNum;
    }

    public void setReqSeqNum(String reqSeqNum) {
        this.reqSeqNum = reqSeqNum;
    }
}
