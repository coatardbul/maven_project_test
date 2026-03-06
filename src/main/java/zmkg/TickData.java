package zmkg;

import lombok.Data;

@Data
public class TickData {

    /**
     * 成交时间
     */
    private String time;

    /**
     * 成交价
     */
    private String price;

    /**
     * 手数
     */
    private String vol;

    /**
     * 未知1
     */
    private String un;

    /**
     * 未知2
     */
    private String flag;
    public TickData(){

    }
    public TickData(String time, String price, String vol, String un, String flag) {
        this.time = time;
        this.price = price;
        this.vol = vol;
        this.un = un;
        this.flag = flag;
    }
}
