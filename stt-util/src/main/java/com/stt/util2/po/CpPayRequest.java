package com.stt.util2.po;

import java.util.Date;

public class CpPayRequest {
    private Integer id;

    private String uuid;

    private String orderNo;

    private String parentOrderNo;

    private String courseOrderUuid;

    private String coursePriceUuid;

    private String gaUserUuid;

    private String promotionCodeUuid;

    private String userUuid;

    private String leadsUuid;

    private String buyLength;

    private Byte courseLevel;

    private Double money;

    private String inActivities;

    private String payType;

    private String bank;

    private String chargeId;

    private String transactionNo;

    private Double alreadyPay;

    private Double pendingPay;

    private String payer;

    private String payTime;

    private Date checkTime;

    private Byte isPay;

    private String checkReamrk;

    private String realUserUuid;

    private String webOrderNo;

    private Byte payChannel;

    private Boolean isMerge;

    private Boolean status;

    private Date createDate;

    private Date updateDate;

    private Boolean isSplit;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public String getParentOrderNo() {
        return parentOrderNo;
    }

    public void setParentOrderNo(String parentOrderNo) {
        this.parentOrderNo = parentOrderNo == null ? null : parentOrderNo.trim();
    }

    public String getCourseOrderUuid() {
        return courseOrderUuid;
    }

    public void setCourseOrderUuid(String courseOrderUuid) {
        this.courseOrderUuid = courseOrderUuid == null ? null : courseOrderUuid.trim();
    }

    public String getCoursePriceUuid() {
        return coursePriceUuid;
    }

    public void setCoursePriceUuid(String coursePriceUuid) {
        this.coursePriceUuid = coursePriceUuid == null ? null : coursePriceUuid.trim();
    }

    public String getGaUserUuid() {
        return gaUserUuid;
    }

    public void setGaUserUuid(String gaUserUuid) {
        this.gaUserUuid = gaUserUuid == null ? null : gaUserUuid.trim();
    }

    public String getPromotionCodeUuid() {
        return promotionCodeUuid;
    }

    public void setPromotionCodeUuid(String promotionCodeUuid) {
        this.promotionCodeUuid = promotionCodeUuid == null ? null : promotionCodeUuid.trim();
    }

    public String getUserUuid() {
        return userUuid;
    }

    public void setUserUuid(String userUuid) {
        this.userUuid = userUuid == null ? null : userUuid.trim();
    }

    public String getLeadsUuid() {
        return leadsUuid;
    }

    public void setLeadsUuid(String leadsUuid) {
        this.leadsUuid = leadsUuid == null ? null : leadsUuid.trim();
    }

    public String getBuyLength() {
        return buyLength;
    }

    public void setBuyLength(String buyLength) {
        this.buyLength = buyLength == null ? null : buyLength.trim();
    }

    public Byte getCourseLevel() {
        return courseLevel;
    }

    public void setCourseLevel(Byte courseLevel) {
        this.courseLevel = courseLevel;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public String getInActivities() {
        return inActivities;
    }

    public void setInActivities(String inActivities) {
        this.inActivities = inActivities == null ? null : inActivities.trim();
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType == null ? null : payType.trim();
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank == null ? null : bank.trim();
    }

    public String getChargeId() {
        return chargeId;
    }

    public void setChargeId(String chargeId) {
        this.chargeId = chargeId == null ? null : chargeId.trim();
    }

    public String getTransactionNo() {
        return transactionNo;
    }

    public void setTransactionNo(String transactionNo) {
        this.transactionNo = transactionNo == null ? null : transactionNo.trim();
    }

    public Double getAlreadyPay() {
        return alreadyPay;
    }

    public void setAlreadyPay(Double alreadyPay) {
        this.alreadyPay = alreadyPay;
    }

    public Double getPendingPay() {
        return pendingPay;
    }

    public void setPendingPay(Double pendingPay) {
        this.pendingPay = pendingPay;
    }

    public String getPayer() {
        return payer;
    }

    public void setPayer(String payer) {
        this.payer = payer == null ? null : payer.trim();
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime == null ? null : payTime.trim();
    }

    public Date getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }

    public Byte getIsPay() {
        return isPay;
    }

    public void setIsPay(Byte isPay) {
        this.isPay = isPay;
    }

    public String getCheckReamrk() {
        return checkReamrk;
    }

    public void setCheckReamrk(String checkReamrk) {
        this.checkReamrk = checkReamrk == null ? null : checkReamrk.trim();
    }

    public String getRealUserUuid() {
        return realUserUuid;
    }

    public void setRealUserUuid(String realUserUuid) {
        this.realUserUuid = realUserUuid == null ? null : realUserUuid.trim();
    }

    public String getWebOrderNo() {
        return webOrderNo;
    }

    public void setWebOrderNo(String webOrderNo) {
        this.webOrderNo = webOrderNo == null ? null : webOrderNo.trim();
    }

    public Byte getPayChannel() {
        return payChannel;
    }

    public void setPayChannel(Byte payChannel) {
        this.payChannel = payChannel;
    }

    public Boolean getIsMerge() {
        return isMerge;
    }

    public void setIsMerge(Boolean isMerge) {
        this.isMerge = isMerge;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Boolean getIsSplit() {
        return isSplit;
    }

    public void setIsSplit(Boolean isSplit) {
        this.isSplit = isSplit;
    }
}