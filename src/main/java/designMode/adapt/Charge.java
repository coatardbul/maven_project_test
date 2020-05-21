package designMode.adapt;

/**
 * 充电器
 */
public interface Charge {
    public void charge(int power);
}

class DefaultChargeAdapter implements Charge {


    @Override
    public void charge(int power) {
        Phone phone = getPhone(power);
        phone.chargeProcess();


    }

    public Phone getPhone(int power) {
        if(power<40){
            return new XiaoMi();
        }
        if(power>40){
            return new Huawei();
        }
        return null;
    }

    public static void main(String[] args) {
        Charge c = new DefaultChargeAdapter();
        c.charge(30);
    }
}

interface Phone {
    public void chargeProcess();

}

class Huawei implements Phone {
    @Override
    public void chargeProcess() {
        System.out.println("Huawei充电中");
    }
}

class XiaoMi implements Phone {
    @Override
    public void chargeProcess() {
        System.out.println("Xiaomi充电中");
    }
}