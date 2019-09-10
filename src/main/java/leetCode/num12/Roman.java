package leetCode.num12;

/**
 * Symbol       Value
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 */
public enum Roman {
    ONE("I",1),
    FIVE("V",5),
    TEN("X",10),
    FIVTY("L",50),
    HUNDRED("C",100),
    FIVEHUNDRED("D",500),
    THOUSAND("M",1000),
    ;
    private String code;
    private int value;

    public String getCode() {
        return code;
    }

    public int getValue() {
        return value;
    }

    Roman(String code, int value) {
        this.code = code;
        this.value = value;
    }

}
