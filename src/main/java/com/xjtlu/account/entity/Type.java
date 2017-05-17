package com.xjtlu.account.entity;

import com.xjtlu.entity.BaseEntity;

public class Type extends BaseEntity {

	private java.lang.Integer id;// 类型id
	private java.lang.String typeName;// 类型名
	private java.lang.Integer parentID;// 父id
	private java.lang.Integer shopID;// 商户id
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

	public java.lang.String getTypeName() {
		return this.typeName;
	}

	public void setTypeName(java.lang.String typeName) {
		this.typeName = typeName;
	}

	public java.lang.Integer getParentID() {
		return this.parentID;
	}

	public void setParentID(java.lang.Integer parentID) {
		this.parentID = parentID;
	}

	public java.lang.Integer getShopID() {
		return this.shopID;
	}

	public void setShopID(java.lang.Integer shopID) {
		this.shopID = shopID;
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
