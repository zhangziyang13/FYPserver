package com.xjtlu.account.page;

import java.math.BigDecimal;
import java.util.List;

import com.xjtlu.page.BasePage;

public class AccountPage extends BasePage {

	private java.lang.Integer id;// 用户id
	private java.lang.String username;// 用户名
	private java.lang.String password;// 密码
	private java.lang.String realname;// 实名
	private java.lang.String nickname;// 昵称
	private java.lang.Integer gender;// 性别，0:男,1:女
	private java.lang.String birthday;// 生日
	private java.lang.String mobilephone;// 手机号
	private java.lang.String telephone;// 固定电话
	private java.lang.String fax;// 传真
	private java.lang.String country;// 国家
	private java.lang.String province;// 省
	private java.lang.String city;// 市
	private java.lang.String district;// 区
	private java.lang.String address;// 详细地址
	private java.lang.String longitude;// 经度
	private java.lang.String latitude;// 纬度
	private java.lang.String coordinate;// 坐标代码
	private java.lang.String payPwd;// 支付密码
	private java.lang.String label;// 标签
	private java.lang.String portrait;// 头像
	private java.lang.String picture;// 图片
	private java.lang.String type;// 类型
	private BigDecimal overallRating;// 整体评分
	private BigDecimal serviceRating;// 服务评分
	private BigDecimal environmentRating;// 环境评分
	private java.lang.String budget;// 人均消费
	private java.lang.String detailURL;// 详情
	private java.lang.Integer commentNum;// 评分总人数
	private java.lang.String parentId;// 推荐人id
	private java.lang.String parentIdList;// 推荐人id列表，用|分隔
	private java.lang.Integer isProved;// 是否认证(0:未认证false,1:已认证true)
	private java.lang.String levelIds;// 会员级别，用|分隔
	private java.lang.String groupLabelIds;// 会员分组，用|分隔
	private java.util.Date loginTime;// 上次登录时间
	private java.lang.String loginIP;// 上次登录IP
	private java.util.Date createTime;// 创建时间
	private java.lang.String createBy;// 创建者
	private java.util.Date updateTime;// 更新时间
	private java.lang.String updateBy;// 更新者
	private java.lang.Integer state;// 状态是否启用(0:启用valid,1:不启用invalid)
	private java.lang.Integer minBudget;// 最低价格
	private java.lang.Integer maxBudget;// 最高价格
	private java.lang.String orderBy;// 排序
	private List<String> labelList;// 标签列表
	private List<String> typeList;// 标签列表

	public java.lang.Integer getId() {
		return this.id;
	}

	public void setId(java.lang.Integer id) {
		this.id = id;
	}

	public java.lang.String getUsername() {
		return this.username;
	}

	public void setUsername(java.lang.String username) {
		this.username = username;
	}

	public java.lang.String getPassword() {
		return this.password;
	}

	public void setPassword(java.lang.String password) {
		this.password = password;
	}

	public java.lang.String getRealname() {
		return this.realname;
	}

	public void setRealname(java.lang.String realname) {
		this.realname = realname;
	}

	public java.lang.String getNickname() {
		return this.nickname;
	}

	public void setNickname(java.lang.String nickname) {
		this.nickname = nickname;
	}

	public java.lang.Integer getGender() {
		return this.gender;
	}

	public void setGender(java.lang.Integer gender) {
		this.gender = gender;
	}

	public java.lang.String getBirthday() {
		return this.birthday;
	}

	public void setBirthday(java.lang.String birthday) {
		this.birthday = birthday;
	}

	public java.lang.String getMobilephone() {
		return this.mobilephone;
	}

	public void setMobilephone(java.lang.String mobilephone) {
		this.mobilephone = mobilephone;
	}

	public java.lang.String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(java.lang.String telephone) {
		this.telephone = telephone;
	}

	public java.lang.String getFax() {
		return this.fax;
	}

	public void setFax(java.lang.String fax) {
		this.fax = fax;
	}

	public java.lang.String getCountry() {
		return this.country;
	}

	public void setCountry(java.lang.String country) {
		this.country = country;
	}

	public java.lang.String getProvince() {
		return this.province;
	}

	public void setProvince(java.lang.String province) {
		this.province = province;
	}

	public java.lang.String getCity() {
		return this.city;
	}

	public void setCity(java.lang.String city) {
		this.city = city;
	}

	public java.lang.String getDistrict() {
		return this.district;
	}

	public void setDistrict(java.lang.String district) {
		this.district = district;
	}

	public java.lang.String getAddress() {
		return this.address;
	}

	public void setAddress(java.lang.String address) {
		this.address = address;
	}

	public java.lang.String getLongitude() {
		return this.longitude;
	}

	public void setLongitude(java.lang.String longitude) {
		this.longitude = longitude;
	}

	public java.lang.String getLatitude() {
		return this.latitude;
	}

	public void setLatitude(java.lang.String latitude) {
		this.latitude = latitude;
	}

	public java.lang.String getCoordinate() {
		return this.coordinate;
	}

	public void setCoordinate(java.lang.String coordinate) {
		this.coordinate = coordinate;
	}

	public java.lang.String getPayPwd() {
		return this.payPwd;
	}

	public void setPayPwd(java.lang.String payPwd) {
		this.payPwd = payPwd;
	}

	public java.lang.String getLabel() {
		return this.label;
	}

	public void setLabel(java.lang.String label) {
		this.label = label;
	}

	public java.lang.String getPortrait() {
		return this.portrait;
	}

	public void setPortrait(java.lang.String portrait) {
		this.portrait = portrait;
	}

	public java.lang.String getPicture() {
		return this.picture;
	}

	public void setPicture(java.lang.String picture) {
		this.picture = picture;
	}

	public java.lang.String getType() {
		return this.type;
	}

	public void setType(java.lang.String type) {
		this.type = type;
	}

	public BigDecimal getOverallRating() {
		return this.overallRating;
	}

	public void setOverallRating(BigDecimal overallRating) {
		this.overallRating = overallRating;
	}

	public BigDecimal getServiceRating() {
		return this.serviceRating;
	}

	public void setServiceRating(BigDecimal serviceRating) {
		this.serviceRating = serviceRating;
	}

	public BigDecimal getEnvironmentRating() {
		return this.environmentRating;
	}

	public void setEnvironmentRating(BigDecimal environmentRating) {
		this.environmentRating = environmentRating;
	}

	public java.lang.String getDetailURL() {
		return this.detailURL;
	}

	public void setDetailURL(java.lang.String detailURL) {
		this.detailURL = detailURL;
	}

	public java.lang.Integer getCommentNum() {
		return this.commentNum;
	}

	public void setCommentNum(java.lang.Integer commentNum) {
		this.commentNum = commentNum;
	}

	public java.lang.String getParentId() {
		return this.parentId;
	}

	public void setParentId(java.lang.String parentId) {
		this.parentId = parentId;
	}

	public java.lang.String getParentIdList() {
		return this.parentIdList;
	}

	public void setParentIdList(java.lang.String parentIdList) {
		this.parentIdList = parentIdList;
	}

	public java.lang.Integer getIsProved() {
		return this.isProved;
	}

	public void setIsProved(java.lang.Integer isProved) {
		this.isProved = isProved;
	}

	public java.lang.String getLevelIds() {
		return this.levelIds;
	}

	public void setLevelIds(java.lang.String levelIds) {
		this.levelIds = levelIds;
	}

	public java.lang.String getGroupLabelIds() {
		return this.groupLabelIds;
	}

	public void setGroupLabelIds(java.lang.String groupLabelIds) {
		this.groupLabelIds = groupLabelIds;
	}

	public java.util.Date getLoginTime() {
		return this.loginTime;
	}

	public void setLoginTime(java.util.Date loginTime) {
		this.loginTime = loginTime;
	}

	public java.lang.String getLoginIP() {
		return this.loginIP;
	}

	public void setLoginIP(java.lang.String loginIP) {
		this.loginIP = loginIP;
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

	public java.lang.String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(java.lang.String orderBy) {
		this.orderBy = orderBy;
	}

	public List<String> getLabelList() {
		return labelList;
	}

	public void setLabelList(List<String> labelList) {
		this.labelList = labelList;
	}

	public List<String> getTypeList() {
		return typeList;
	}

	public void setTypeList(List<String> typeList) {
		this.typeList = typeList;
	}

	public java.lang.String getBudget() {
		return budget;
	}

	public void setBudget(java.lang.String budget) {
		this.budget = budget;
	}

	public java.lang.Integer getMinBudget() {
		return minBudget;
	}

	public void setMinBudget(java.lang.Integer minBudget) {
		this.minBudget = minBudget;
	}

	public java.lang.Integer getMaxBudget() {
		return maxBudget;
	}

	public void setMaxBudget(java.lang.Integer maxBudget) {
		this.maxBudget = maxBudget;
	}

}
