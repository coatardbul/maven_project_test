package entity;

import java.io.Serializable;
import java.util.Objects;

public class ImportCrmProductParent implements Serializable {
    /**
     *预约ID
     */
    private Long dateId;
    /**
     *更新时间
     */
    private String updateTime;
    /**
     *产品ID
     */
    private String prodId;

    /**
     *预约类型
     */
    private String appType;
    /**
     *金额
     */
    private Double custBal;
    /**
     *预约状态
     */
    private String resStatus;
    /**
     *是否打印申请书
     */
    private String printAppYes;
    /**
     *是否打印确认书
     */
    private String printConfimYes;
    /**
     *预约登记时间
     */
    private String regAppTime;
    /**
     *交易时间
     */
    private String tradeTime;

    public ImportCrmProductParent(Long dateId, String updateTime, String prodId, String appType, Double custBal, String resStatus, String printAppYes, String printConfimYes, String regAppTime, String tradeTime) {
        this.dateId = dateId;
        this.updateTime = updateTime;
        this.prodId = prodId;
        this.appType = appType;
        this.custBal = custBal;
        this.resStatus = resStatus;
        this.printAppYes = printAppYes;
        this.printConfimYes = printConfimYes;
        this.regAppTime = regAppTime;
        this.tradeTime = tradeTime;
    }

    public ImportCrmProductParent() {
    }

    public Long getDateId() {
        return dateId;
    }

    public void setDateId(Long dateId) {
        this.dateId = dateId;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }

    public Double getCustBal() {
        return custBal;
    }

    public void setCustBal(Double custBal) {
        this.custBal = custBal;
    }

    public String getResStatus() {
        return resStatus;
    }

    public void setResStatus(String resStatus) {
        this.resStatus = resStatus;
    }

    public String getPrintAppYes() {
        return printAppYes;
    }

    public void setPrintAppYes(String printAppYes) {
        this.printAppYes = printAppYes;
    }

    public String getPrintConfimYes() {
        return printConfimYes;
    }

    public void setPrintConfimYes(String printConfimYes) {
        this.printConfimYes = printConfimYes;
    }

    public String getRegAppTime() {
        return regAppTime;
    }

    public void setRegAppTime(String regAppTime) {
        this.regAppTime = regAppTime;
    }

    public String getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(String tradeTime) {
        this.tradeTime = tradeTime;
    }

    @Override
    public String toString() {
        return "ImportCrmProductParent{" +
                "dateId=" + dateId +
                ", updateTime='" + updateTime + '\'' +
                ", prodId='" + prodId + '\'' +
                ", appType='" + appType + '\'' +
                ", custBal=" + custBal +
                ", resStatus='" + resStatus + '\'' +
                ", printAppYes='" + printAppYes + '\'' +
                ", printConfimYes='" + printConfimYes + '\'' +
                ", regAppTime='" + regAppTime + '\'' +
                ", tradeTime='" + tradeTime + '\'' +
                '}';
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImportCrmProductParent that = (ImportCrmProductParent) o;
        return Objects.equals(dateId, that.dateId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(dateId);
    }
}
