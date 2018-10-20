package JDBCTest;

public class UserIp {
    /**
     * 主键
     */
    private  Long    userCheckId;
    /**
     * id
     */
    private  Long   userId;
    /**
     *用户ip地址
     */
    private  String  ip;
    /**
     *用户 mac地址
     */
    private  String  mac;
    /**
     * 有效开始时间
     */
    private  String     beginActiveTime;
    /**
     * 有效结束时间
     */
    private  String    endActiveTime;
    /**
     * 创建时间
     */
    private  String    createTime;
    /**
     * 创建人
     */
    private  String    createUser;
    /**
     * 修改人
     */
    private  String    updateUser;
    /**
     * 修改时间
     */
    private  String   updateTime;
    /**
     * 备注
     */
    private  String    remark;

    /**
     * 页面是否显示 0 不显示，1显示
     */
    private String ipFlag;
    public UserIp() {
    }

    public UserIp(Long userCheckId, Long userId, String ip, String mac, String beginActiveTime, String endActiveTime, String createTime, String createUser, String updateUser, String updateTime, String remark, String ipFlag) {
        this.userCheckId = userCheckId;
        this.userId = userId;
        this.ip = ip;
        this.mac = mac;
        this.beginActiveTime = beginActiveTime;
        this.endActiveTime = endActiveTime;
        this.createTime = createTime;
        this.createUser = createUser;
        this.updateUser = updateUser;
        this.updateTime = updateTime;
        this.remark = remark;
        this.ipFlag = ipFlag;
    }

    public String getIpFlag() {
        return ipFlag;
    }

    public void setIpFlag(String ipFlag) {
        this.ipFlag = ipFlag;
    }

    public Long getUserCheckId() {
        return userCheckId;
    }

    public void setUserCheckId(Long userCheckId) {
        this.userCheckId = userCheckId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getBeginActiveTime() {
        return beginActiveTime;
    }

    public void setBeginActiveTime(String beginActiveTime) {
        this.beginActiveTime = beginActiveTime;
    }

    public String getEndActiveTime() {
        return endActiveTime;
    }

    public void setEndActiveTime(String endActiveTime) {
        this.endActiveTime = endActiveTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "UserIp{" +
                "userCheckId=" + userCheckId +
                ", userId=" + userId +
                ", ip='" + ip + '\'' +
                ", mac='" + mac + '\'' +
                ", beginActiveTime='" + beginActiveTime + '\'' +
                ", endActiveTime='" + endActiveTime + '\'' +
                ", createTime='" + createTime + '\'' +
                ", createUser='" + createUser + '\'' +
                ", updateUser='" + updateUser + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", remark='" + remark + '\'' +
                ", ipFlag='" + ipFlag + '\'' +
                '}';
    }
}
