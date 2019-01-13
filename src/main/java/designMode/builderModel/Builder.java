package designMode.builderModel;

public abstract class Builder {

    /**
     * 建造引擎
     */
    abstract void builderEngine();

    /**
     * 建造外壳
     */
    abstract void buildShape();

    /**
     * 建造车灯
     */
    abstract void buildLamp();

    /**
     * 得到汽车
     * @return
     */
   // abstract ICar getCar();

}
