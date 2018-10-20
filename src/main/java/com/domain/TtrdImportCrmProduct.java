package com.domain;

import java.math.BigDecimal;

public class TtrdImportCrmProduct {
    /** crm系统预约ID*/
    private Long dateId;

    /** 更新时间*/
    private String updateTime;

    /** crm系统产品ID，关联CRM_F_PD_RECORD的PROD_ID基于这个字段关联到信惠产品的交易预约*/
    private String prodId;

    /** 预约类型:020-认购（集合产品）;021-认购-成立后的首次购买（信惠产品）;022-申购-成立后的追加认购;024-赎回*/
    private String appType;

    /** 金额*/
    private BigDecimal custBal;

    /** 预约状态:0-未提交；1-预约失败；2-预约取消；3-预约中；4-审批通过；5-成功汇款；6-退回；7-预约待审核；8-取消待审核；9-修改待审核*/
    private String resStatus;

    /** 是否打印申请书（申购、赎回）*/
    private String printAppYes;

    /** 是否打印确认书*/
    private String printConfimYes;

    /** 预约登记时间*/
    private String regAppTime;

    /** 金额交易时间*/
    private String tradeTime;

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

    public BigDecimal getCustBal() {
        return custBal;
    }

    public void setCustBal(BigDecimal custBal) {
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
}