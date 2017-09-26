package com.stt.util2.po;

import java.util.Date;

/**
 * 课程学生表
 * @author shitongtong
 * Created by shitongtong on 2017/09/25
 */
public class CourseLeads {
    private Integer id;

    private String courseLeadsUuid;

    /**
     * cp_course_uuid
     */
    private String courseUuid;

    /**
     * leadsUuid
     */
    private String leadsUuid;

    /**
     * 消耗课程订单uuid
     */
    private String courseOrderUuid;

    /**
     * 课程请求uuid
     */
    private String courseRequestUuid;

    /**
     * 付费申请uuid或者官网支付订单号
     */
    private String payUuid;

    /**
     * 状态:0:无效，1：正常
     */
    private Integer status;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 创建人uuid
     */
    private String createUid;

    /**
     * 更新人uuid
     */
    private String updateUid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCourseLeadsUuid() {
        return courseLeadsUuid;
    }

    public void setCourseLeadsUuid(String courseLeadsUuid) {
        this.courseLeadsUuid = courseLeadsUuid == null ? null : courseLeadsUuid.trim();
    }

    public String getCourseUuid() {
        return courseUuid;
    }

    public void setCourseUuid(String courseUuid) {
        this.courseUuid = courseUuid == null ? null : courseUuid.trim();
    }

    public String getLeadsUuid() {
        return leadsUuid;
    }

    public void setLeadsUuid(String leadsUuid) {
        this.leadsUuid = leadsUuid == null ? null : leadsUuid.trim();
    }

    public String getCourseOrderUuid() {
        return courseOrderUuid;
    }

    public void setCourseOrderUuid(String courseOrderUuid) {
        this.courseOrderUuid = courseOrderUuid == null ? null : courseOrderUuid.trim();
    }

    public String getCourseRequestUuid() {
        return courseRequestUuid;
    }

    public void setCourseRequestUuid(String courseRequestUuid) {
        this.courseRequestUuid = courseRequestUuid == null ? null : courseRequestUuid.trim();
    }

    public String getPayUuid() {
        return payUuid;
    }

    public void setPayUuid(String payUuid) {
        this.payUuid = payUuid == null ? null : payUuid.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateUid() {
        return createUid;
    }

    public void setCreateUid(String createUid) {
        this.createUid = createUid == null ? null : createUid.trim();
    }

    public String getUpdateUid() {
        return updateUid;
    }

    public void setUpdateUid(String updateUid) {
        this.updateUid = updateUid == null ? null : updateUid.trim();
    }
}