package com.google.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * 员工信息表
 *
 * @author iuv
 */
@Data
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
}
