package zmkg;

import lombok.Data;

import java.math.BigDecimal;

/**
 * <p>
 * Note:
 * <p>
 * Date: 2022/11/23
 *
 * @author Su Xiaolei
 */
@Data
public class TickPriceStaticBo {

    /**
     * 分价
     */
    private BigDecimal price;

    private BigDecimal buyVol;

    private BigDecimal buyAmount;

    private BigDecimal sellVol;

    private BigDecimal sellAmount;

    private  Integer status;



    /**
     * 占比 19%  取百分值
     */
    private BigDecimal volRate;
    private BigDecimal high;
    private BigDecimal low;
    private BigDecimal close;
    private BigDecimal open;


    private String region;


    public TickPriceStaticBo(BigDecimal price) {
        this.price = price;
        this.buyVol = BigDecimal.ZERO;
        this.buyAmount =  BigDecimal.ZERO;
        this.sellVol =  BigDecimal.ZERO;
        this.sellAmount =  BigDecimal.ZERO;
        this.volRate =  BigDecimal.ZERO;
    }

    public TickPriceStaticBo(BigDecimal price, BigDecimal buyVol, BigDecimal buyAmount, BigDecimal sellVol, BigDecimal sellAmount, BigDecimal volRate) {
        this.price = price;
        this.buyVol = buyVol;
        this.buyAmount = buyAmount;
        this.sellVol = sellVol;
        this.sellAmount = sellAmount;
        this.volRate = volRate;
    }




}
