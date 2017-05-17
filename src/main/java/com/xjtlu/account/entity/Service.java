package com.xjtlu.account.entity;

import java.math.BigDecimal;
import com.xjtlu.entity.BaseEntity;

public class Service extends BaseEntity {

	private java.lang.Integer id;// 服务id
	private java.lang.String serviceName;// 服务名
	private BigDecimal price;// 服务价格
	private java.lang.String picture;// 服务图片
	private java.lang.String description;// 服务描述
	private java.lang.Integer typeID;// 类型id
	private java.util.Date createTime;// 创建时间
	private java.lang.String createBy;// 创建者
	private java.util.Date updateTime;// 更新时间
	private java.lang.String updateBy;// 更新者
	private java.lang.Integer state;// 状态是否启用(0:启用valid,1:不启用invalid)

	public java.lang.Integer getId() {
		return this.id;
	}

	public void setId(java.lang.Integer id) {
		this.id = id;
	}

	public java.lang.String getServiceName() {
		return this.serviceName;
	}

	public void setServiceName(java.lang.String serviceName) {
		this.serviceName = serviceName;
	}

	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public java.lang.String getPicture() {
		return this.picture;
	}

	public void setPicture(java.lang.String picture) {
		this.picture = picture;
	}

	public java.lang.String getDescription() {
		return this.description;
	}

	public void setDescription(java.lang.String description) {
		this.description = description;
	}

	public java.lang.Integer getTypeID() {
		return this.typeID;
	}

	public void setTypeID(java.lang.Integer typeID) {
		this.typeID = typeID;
	}

	public java.util.Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

	public java.lang.String getCreateBy() {
		return this.createBy;
	}

	public void setCreateBy(java.lang.String createBy) {
		this.createBy = createBy;
	}

	public java.util.Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}

	public java.lang.String getUpdateBy() {
		return this.updateBy;
	}

	public void setUpdateBy(java.lang.String updateBy) {
		this.updateBy = updateBy;
	}

	public java.lang.Integer getState() {
		return this.state;
	}

	public void setState(java.lang.Integer state) {
		this.state = state;
	}
}
