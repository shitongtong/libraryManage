package com.stt.util2.po;

import java.math.BigDecimal;
import java.util.Date;

public class CpCoursePrice {
    private Integer id;

    private String uuid;

    private String level;

    private String type;

    private Integer length;

    private Long originalUnitPrice;

    private Long originalPrice;

    private Long nowUnitPrice;

    private Long nowPrice;

    private BigDecimal specialDiscount;

    private Long specialPrice;

    private Byte onlineStatus;

    private Date createDate;

    private Date updateDate;

    private String creater;

    private String updater;

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

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level == null ? null : level.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Long getOriginalUnitPrice() {
        return originalUnitPrice;
    }

    public void setOriginalUnitPrice(Long originalUnitPrice) {
        this.originalUnitPrice = originalUnitPrice;
    }

    public Long getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(Long originalPrice) {
        this.originalPrice = originalPrice;
    }

    public Long getNowUnitPrice() {
        return nowUnitPrice;
    }

    public void setNowUnitPrice(Long nowUnitPrice) {
        this.nowUnitPrice = nowUnitPrice;
    }

    public Long getNowPrice() {
        return nowPrice;
    }

    public void setNowPrice(Long nowPrice) {
        this.nowPrice = nowPrice;
    }

    public BigDecimal getSpecialDiscount() {
        return specialDiscount;
    }

    public void setSpecialDiscount(BigDecimal specialDiscount) {
        this.specialDiscount = specialDiscount;
    }

    public Long getSpecialPrice() {
        return specialPrice;
    }

    public void setSpecialPrice(Long specialPrice) {
        this.specialPrice = specialPrice;
    }

    public Byte getOnlineStatus() {
        return onlineStatus;
    }

    public void setOnlineStatus(Byte onlineStatus) {
        this.onlineStatus = onlineStatus;
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

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater == null ? null : creater.trim();
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater == null ? null : updater.trim();
    }
}