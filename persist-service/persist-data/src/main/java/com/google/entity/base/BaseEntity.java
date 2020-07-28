package com.google.entity.base;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author iuv
 * @ClassName: BaseEntity
 * @Description: 基础实体
 * @date 2016年11月1日 上午11:14:35
 */
@MappedSuperclass
public class BaseEntity implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 4074524461107054334L;

    @Version
    private Integer version;

    @Column(name = "STATUS")
    private Integer status; // 0:正常 -1:禁用 -2:删除

    @Column(name = "CREATE_ID")
    private Long createId;

    @Column(name = "CREATER")
    private String creater;

    @Column(name = "CREATE_MOB")
    private String createMob;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATE_TIME")
    private Date createTime;

    @Column(name = "MODIFY_ID")
    private Long modifyId;

    @Column(name = "MODIFIER")
    private String modifier;

    @Column(name = "MODIFY_MOB")
    private String modifyMob;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "MODIFY_TIME")
    private Date modifyTime;

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getCreateId() {
        return createId;
    }

    public void setCreateId(Long createId) {
        this.createId = createId;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public String getCreateMob() {
        return createMob;
    }

    public void setCreateMob(String createMob) {
        this.createMob = createMob;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getModifyId() {
        return modifyId;
    }

    public void setModifyId(Long modifyId) {
        this.modifyId = modifyId;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public String getModifyMob() {
        return modifyMob;
    }

    public void setModifyMob(String modifyMob) {
        this.modifyMob = modifyMob;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

}
