package question.json;

import com.alibaba.fastjson.JSON;
import entity.User;
import entity.UserIp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ObjectOfficeToJson {
    public static void main(String[] args) {
        User user = new User();
        user.setId(123131L);
        user.setUserName("asdfg");
        user.setAddr("123456");

        List<UserIp> l = new ArrayList<>();
        UserIp u = new UserIp();
        u.setUserCheckId(0L);
        u.setUserId(0L);
        u.setUpdateUser("11111");
        u.setUpdateTime("11111");
        u.setRemark("aaaaaa");
        u.setIpFlag("aaaaaaaaa");
        l.add(u);

        UserIp u1 = new UserIp();

        u1.setCreateTime("2222");
        u1.setCreateUser("bbb");
        u1.setUpdateUser("bbb");
        u1.setUpdateTime("222");
        l.add(u1);


        //user.setList(l);
        user.setUpdateTime(LocalDateTime.now());
       String str = JSON.toJSONString(user);
       System.out.println(str);
    }

}
