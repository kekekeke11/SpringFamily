package com.google.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * 员工信息表
 *
 * @author iuv
 */
@Entity
@Table(name = "T_CUST_UAC")
public class CustUac {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "BID")
    private String bid;

    // 手机号
    @Column(name = "MOBILE", length = 11, unique = true)
    private String mobile;

    // 密码
    @Column(name = "PASSWORD", length = 32)
    private String password;

    // 用户名称/姓名
    @Column(name = "USER_NAME", length = 20)
    private String userName;

    // 邮箱
    @Column(name = "EMAIL")
    private String email;

    // QQ
    @Column(name = "QQ")
    private String QQ;

    // 微信
    @Column(name = "WECHAT")
    private String wechat;

    // 证件类型
    @Column(name = "ID_TYPE")
    private Integer idType;

    // 证件号码
    @Column(name = "ID_CARD", length = 20)
    private String idCard;

    // 性别
    @Column(name = "GENDER", length = 1)
    private String gender; // (M-男，F-女，N-未知)

    // 是否注册
    @Column(name = "IS_REG")
    private Integer isReg;

    /**
     * 头像
     */
    private String photo;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQQ() {
        return QQ;
    }

    public void setQQ(String qQ) {
        QQ = qQ;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public Integer getIdType() {
        return idType;
    }

    public void setIdType(Integer idType) {
        this.idType = idType;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getIsReg() {
        return isReg;
    }

    public void setIsReg(Integer isReg) {
        this.isReg = isReg;
    }

    // 是否锁定(0:正常 1:锁定)
    @Column(name = "LOCKED")
    private Integer locked;

    // 锁定时间
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "LOCKED_TIME")
    private Date lockedTime;

    // 创建时间
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATE_TIME")
    private Date createTime;

    private Integer status;

    // 是否修改密码
    @Column(name = "MODIFY_PWD")
    private Integer modifyPwd;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public Integer getLocked() {
        return locked;
    }

    public void setLocked(Integer locked) {
        this.locked = locked;
    }

    public Date getLockedTime() {
        return lockedTime;
    }

    public void setLockedTime(Date lockedTime) {
        this.lockedTime = lockedTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getModifyPwd() {
        return modifyPwd;
    }

    public void setModifyPwd(Integer modifyPwd) {
        this.modifyPwd = modifyPwd;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }


}
