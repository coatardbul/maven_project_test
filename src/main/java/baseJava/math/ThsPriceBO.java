package baseJava.math;

import lombok.Data;

import java.util.List;

/**
 * <p>
 * Note:
 * <p>
 * Date: 2021/7/7
 *
 * @author Su Xiaolei
 */
@Data
public class ThsPriceBO {


    private  String total;


    private String start;

    private String name;


    private String marketType;

    private List<List<Integer>> sortYear;


    private Integer priceFactor;


    private String price;


    private String volumn;


    private String dates;
    private  String afterVolumn;

    private String issuePrice;
}
