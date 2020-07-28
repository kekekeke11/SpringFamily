package com.google.entity;

import com.google.entity.base.BaseEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 员工信息表
 * 
 * @author iuv
 *
 */
@Entity
@Table(name = "T_CUST_USER")
public class CustUser extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2232925269378317767L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(name="BID")
	private String bid;
	// 用户账户ID	
	@Column(name = "UAC_ID")
	private Long uacId;
	// 企业ID	
	@Column(name = "COMP_ID")
	private Long compId;
	// root管理员
	@Column(name = "ROOT_MANAGER")
	private Integer rootManager;
	
	// 是否激活
	@Column(name="IS_ACTIVE")
	private Integer isActive;
	// 用户角色
	@Column(name="USER_ROLE")
	private Integer userRole;
	// 激活时间
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ACTIVE_TIME")
	private Date activeTime;
	
	public Integer getUserRole() {
		return userRole;
	}
	public void setUserRole(Integer userRole) {
		this.userRole = userRole;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUacId() {
		return uacId;
	}
	public void setUacId(Long uacId) {
		this.uacId = uacId;
	}
	public Long getCompId() {
		return compId;
	}
	public void setCompId(Long compId) {
		this.compId = compId;
	}
	
	public Integer getRootManager() {
		return rootManager;
	}
	public void setRootManager(Integer rootManager) {
		this.rootManager = rootManager;
	}

	public Integer getIsActive() {
		return isActive;
	}
	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}
	public Date getActiveTime() {
		return activeTime;
	}
	public void setActiveTime(Date activeTime) {
		this.activeTime = activeTime;
	}
	public String getBid() {
		return bid;
	}
	public void setBid(String bid) {
		this.bid = bid;
	}
	
}
