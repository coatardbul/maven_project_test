import org.owasp.encoder.Encode;

import java.lang.reflect.Field;

public class ReflexUtil {
    public static void main(String[] args) {
        UserIp userIp = new UserIp();
        userIp.setCreateUser("<img src=1 . onerror=alter(2)><>fds");
        //readAttributeValue(userIp);
        setAttributeValue("mac","12312312",userIp);
        setAttributeValue("updateTime","2019-09-09",userIp);
        System.out.println(userIp);
    }

    /**
     * 根据name设置对应的value
     *
     * @param name,value,Object
     */
    public static void setAttributeValue(String name, Object value, Object object) {
        //得到class
        Class objectClass = object.getClass();
        //得到所有属性
        Field[] fields = objectClass.getDeclaredFields();
        try {
            for (int i = 0; i < fields.length; i++) {//遍历
                //得到属性
                Field field = fields[i];
                //打开私有访问
                field.setAccessible(true);
                //获取属性
                String nameTemp = field.getName();
                if (nameTemp.equals(name)) {
                    field.set(object,value);
                }
            }
        }catch(IllegalAccessException e){
                e.printStackTrace();
        }
    }


}
