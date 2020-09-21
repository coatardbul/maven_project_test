package network.urlSpider;

public enum ProvinceEnum {
    BEIJING("1", "北京市"),
    TIANJING("2", "天津市"),
    HEBEI("3", "河北省"),
    SHANXISHENG("4", "山西省"),
    NEIMENGGU("5", "内蒙古自治区"),
    LIAONING("6", "辽宁省"),
    JILIN("7", "吉林省"),
    HEILONGJIANG("8", "黑龙江省"),
    SHANGHAI("9", "上海市"),
    JIANGSU("10", "江苏省"),
    ZHEJIANG("11", "浙江省"),
    ANHUI("12", "安徽省"),
    FUJIAN("13", "福建省"),
    JINGXI("14", "江西省"),
    SHANDONG("15", "山东省"),
    HENAN("16", "河南省"),
    HUBEI("17", "湖北省"),
    HUNAN("18", "湖南省"),
    GUANGDONG("19", "广东省"),
    GUANGXI("20", "广西壮族自治区"),
    HAINAN("21", "海南省"),
    SICHUAN("22", "四川省"),
    CHONGQING("23", "重庆市"),
    GUIZHOU("24", "贵州省"),
    YUNNAN("25", "云南省"),
    XIZANG("26", "西藏自治区"),
    SHANXI("27", "陕西省"),
    GUSU("28", "甘肃省"),
    QINGHAI("29", "青海省"),
    NINGXIA("30", "宁夏回族自治区"),
    XINJIANG("31", "新疆维吾尔族自治区"),
    TAIWAN("32", "台湾"),
    XIANGGANG("33", "香港"),
    AOMEN("34", "澳门");
    private String code;
    private String message;

    ProvinceEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }
}
