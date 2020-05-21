package designMode.adapt;

/**
 * 对扩展开放，对修改关闭
 * ac220是原有的类，通过适配器将原有地的方法包装，改用新的类
 */
public class AC220 {

    public int output220V() {
        int output = 220;
        return output;
    }
}
interface IDC5 {

    public int output5V();
}
class DC5Adapter extends AC220 implements IDC5 {

    @Override
    public int output5V() {
        int v = output220V();
        return v / 44;
    }
}
class AdapteTest {
    public static void main(String[] args) {
        IDC5 d = new DC5Adapter();
        System.out.println(d.output5V());
    }
}
