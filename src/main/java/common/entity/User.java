package common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.poi.ss.formula.functions.T;

import java.time.LocalDateTime;
import java.util.List;


public class User {
    private long id;
    private String userName;
    private String addr;
    private LocalDateTime updateTime;
    private List list;

    public User() {
    }

    public User(long id, String userName, String addr, LocalDateTime updateTime, List<org.apache.poi.ss.formula.functions.T> list) {
        this.id = id;
        this.userName = userName;
        this.addr = addr;
        this.updateTime = updateTime;
        this.list = list;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
