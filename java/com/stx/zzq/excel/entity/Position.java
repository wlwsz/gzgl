package com.stx.zzq.excel.entity;

/**
 * 职位
 * 
 * @author zhangzunqiao
 */
public class Position {

	/* 职位编号 */
	private String positionId;
	/* 部门编号 */
	private String departmentId;
	/* 部门名称 */
	private String departmentName;
	/* 职位名称 */
	private String positionName;
	/* 基本工资 */
	private float basicWage;
	/* 职位等级 评定的规则暂时未定 */
	private String level;

	// /* 加班一个小时费用 */
	// private float overtimeMoney;
	// /* 销售提成百分比 */
	// private int sellGetpercent;
	// /* 交通补助 */
	// private float trafficWage;
	// /* 保险扣除 */
	// private float secureReduce;
	// /* 迟到一分钟扣款 */
	// private float chidaoReduce;
	// /* 早退一分钟扣款 */
	// private float zaotuiReduce;
	// /* 矿工一天口扣款金额 */
	// private float kuanggongReduce;
	/* 职位描述 */
	private String memo;

	public String getPositionId() {
		return positionId;
	}

	public void setPositionId(String positionId) {
		this.positionId = positionId;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public float getBasicWage() {
		return basicWage;
	}

	public void setBasicWage(float basicWage) {
		this.basicWage = basicWage;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	@Override
	public String toString() {
		return "Position [positionId=" + positionId + ", departmentId=" + departmentId + ", departmentName="
				+ departmentName + ", positionName=" + positionName + ", basicWage=" + basicWage + ", level=" + level
				+ ", memo=" + memo + "]";
	}

}
