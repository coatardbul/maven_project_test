package jsonInfo;

import common.entity.User;
import common.entity.UserIp;
import org.junit.Test;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * {
 * "reqSeqNo": "6000000002",
 * "reqDate": "20190729",
 * "reqTime": "120110",
 * "channelId": "0001",
 * "bankCode": "psbc",
 * "busiSeqNoList":[
 * { "busiSeqNo":"20191012000200014401"},
 * { "busiSeqNo":"201910120002214401"}
 * <p>
 * ]
 * <p>
 * <p>
 * }
 */
public class ObjectToJson {
    public final String DOUBLE_QUOTE = "\"";


    @Test
    public void test1() throws IllegalAccessException {
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


       // user.setList(l);
        user.setUpdateTime(LocalDateTime.now());


//        getList(l);




        Field[] fields = user.getClass().getDeclaredFields();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < fields.length; i++) {//遍历
            //得到属性
            Field field = fields[i];
            //打开私有访问
            field.setAccessible(true);
            //获取属性
            if (field.get(user) != null) {
                get(sb, field, user);
                System.out.println(sb.toString());
            }

        }


    }

    /**
     * @param field
     * @return
     */
    public StringBuffer get(StringBuffer sb, Field field, Object o) throws IllegalAccessException {


        if ("Long".equals(field.getGenericType().toString().trim().substring(field.getGenericType().toString().trim().lastIndexOf(".") + 1))) {
            return sb.append(DOUBLE_QUOTE).append(field.getName()).append(DOUBLE_QUOTE).append(":").append(field.get(o)).append(",");
        } else if ("long".equals(field.getGenericType().toString().trim().substring(field.getGenericType().toString().trim().lastIndexOf(".") + 1))) {
            return sb.append(DOUBLE_QUOTE).append(field.getName()).append(DOUBLE_QUOTE).append(":").append(field.get(o)).append(",");
        } else if ("List<T>".equals(field.getGenericType().toString().trim().substring(field.getGenericType().toString().trim().lastIndexOf(".") + 1))) {
            return sb.append(DOUBLE_QUOTE).append(field.getName()).append(DOUBLE_QUOTE).append(":").append("{" + getList((List)field.get(o))+ "}");
        } else {
            return sb.append(DOUBLE_QUOTE).append(field.getName()).append(DOUBLE_QUOTE).append(":").append(DOUBLE_QUOTE).append(field.get(o).toString()).append(DOUBLE_QUOTE).append(",");
        }

    }

    public StringBuffer getList(List o) throws IllegalAccessException {
        StringBuffer sb=new StringBuffer();
        for(Object sb1:o){
            Field[] fields = sb1.getClass().getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {//遍历
                //得到属性
                Field field = fields[i];
                //打开私有访问
                field.setAccessible(true);
                //获取属性
                if (field.get(sb1) != null) {
                    get(sb, field, sb1);
                    //System.out.println(sb.toString());
                }

            }
        }
        return sb;
    }

}
