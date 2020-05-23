package common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserIp implements Serializable {
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

}
