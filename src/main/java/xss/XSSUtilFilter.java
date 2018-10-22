package xss;

import entity.UserIp;
import org.owasp.encoder.Encode;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class XSSUtilFilter {
    public String str;
    public Integer num;
    public UserIp userIp;
    public int numTemp;

    public int getNumTemp() {
        return numTemp;
    }

    public void setNumTemp(int numTemp) {
        this.numTemp = numTemp;
    }

    public UserIp getUserIp() {
        return userIp;
    }

    public void setUserIp(UserIp userIp) {
        this.userIp = userIp;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "XSSUtilFilter{" +
                "str='" + str + '\'' +
                ", num=" + num +
                ", userIp=" + userIp +
                ", numTemp=" + numTemp +
                '}';
    }

    public static void main(String[] args) {
        XSSUtilFilter xs = new XSSUtilFilter();
        xs.setStr("<img src=1 . onerror=alter(2)><>fds");
        xs.setNum(new Integer(123));
        UserIp userIp = new UserIp();
        userIp.setCreateUser("<img src=1 . onerror=alter(2)><>fds");
        xs.setUserIp(userIp);
        setAttributeValue(xs);
        System.out.println(xs.toString());
    }

    /**
     * 得到属性值
     *
     * @param obj
     */
    public static void readAttributeValueTemp(Object obj) {

        //得到class
        Class cls = obj.getClass();
        //得到所有属性
        Field[] fields = cls.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {//遍历
            try {
                //得到属性
                Field field = fields[i];
                //打开私有访问
                field.setAccessible(true);
                //获取属性
                String name = field.getName();
                //获取属性值
                Object value = field.get(obj);
                //一个个赋值
                if (field.getGenericType().toString().equals("class java.lang.String")) {
                    field.set(obj, Encode.forHtml((String) value));
                }
                if (field.getGenericType().getClass() instanceof Object) {
                    System.out.print(name + ";" + value);
                    System.out.print("-----");
                    System.out.print(field.getGenericType().getClass().isPrimitive());
                    System.out.print("-----");
                    System.out.println(field.getGenericType().toString());
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

    }

    // 把一个字符串的第一个字母大写、效率是最高的、
    private static String getMethodName(String fildeName) throws Exception {
        byte[] items = fildeName.getBytes();
        items[0] = (byte) ((char) items[0] - 'a' + 'A');
        return new String(items);
    }

    /**
     * 得到属性值
     *
     * @param obj
     */
    public static void setAttributeValue(Object obj) {
        //得到class
        Class cls = obj.getClass();
        //得到所有属性
        Field[] fields = cls.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            try {
                //得到属性
                Field field = fields[i];
                //打开私有访问
                field.setAccessible(true);
                //获取属性
                String name = field.getName();
                //获取属性值
                Object value = field.get(obj);

                if (field.getGenericType().toString().equals("class java.lang.String")) {
                    field.set(obj, Encode.forHtml((String) value));
                }

                if (!field.getGenericType().toString().equals("class java.lang.String") &&
                        !field.getGenericType().toString().equals("class java.lang.Long") &&
                        !field.getGenericType().toString().equals("long") &&
                        !field.getGenericType().toString().equals("class java.lang.Integer") &&
                        !field.getGenericType().toString().equals("int") &&
                        !field.getGenericType().toString().equals("class java.lang.Double") &&
                        !field.getGenericType().toString().equals("double") &&
                        !field.getGenericType().toString().equals("class java.lang.Float") &&
                        !field.getGenericType().toString().equals("float") &&
                        !field.getGenericType().toString().equals("class java.lang.Character") &&
                        !field.getGenericType().toString().equals("char") &&
                        !field.getGenericType().toString().equals("class java.lang.Boolean") &&
                        !field.getGenericType().toString().equals("boolean") &&
                        !field.getGenericType().toString().equals("class java.lang.Short") &&
                        !field.getGenericType().toString().equals("short") &&
                        !field.getGenericType().toString().equals("class java.lang.Byte") &&
                        !field.getGenericType().toString().equals("byte")) {
                    setAttributeValue(value);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

    }
}
