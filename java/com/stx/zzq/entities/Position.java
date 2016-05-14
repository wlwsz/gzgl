package com.stx.zzq.entities;

import com.stx.zzq.base.BaseEntity;

/**
 * 职位
 * 
 * @author zhangzunqiao
 */
public class Position extends BaseEntity {

	/* 职位编号 */
	private String positionId;
	/* 部门编号 */
	private String departmentId;
	/* 部门名称 */
	private String departmentName;
	/* 职位名称 */
	private String positionName;
	/* 基本工资 */
	private String basicWage;
	/* 五险一金扣除 */
	private String secureReduce;
	/* 职位等级 评定的规则暂时未定 */
	private String level;
	/* 职位描述 */
	private String memo;

	public String getPositionId() {
		return (String) get("positionId");
	}

	public void setPositionId(String positionId) {
		put("positionId", positionId);
	}

	public String getDepartmentId() {
		return (String) get("departmentId");
	}

	public void setDepartmentId(String departmentId) {
		put("departmentId", departmentId);
	}

	public String getDepartmentName() {
		return (String) get("departmentName");
	}

	public void setDepartmentName(String departmentName) {
		put("departmentName", departmentName);
	}

	public String getPositionName() {
		return (String) get("positionName");
	}

	public void setPositionName(String positionName) {
		put("positionName", positionName);
	}

	public String getBasicWage() {
		return (String) get("basicWage");
	}

	public void setBasicWage(String basicWage) {
		put("basicWage", basicWage);
	}

	public String getSecureReduce() {
		return (String) get("secureReduce");
	}

	public void setSecureReduce(String secureReduce) {
		put("secureReduce", secureReduce);
	}

	public String getLevel() {
		return (String) get("level");
	}

	public void setLevel(String level) {
		put("level", level);
	}

	public String getMemo() {
		return (String) get("memo");
	}

	public void setMemo(String memo) {
		put("memo", memo);
	}

	@Override
	public String toString() {
		return "Position [positionId=" + getPositionId() + ", departmentId=" + getDepartmentId() + ", departmentName="
				+ getDepartmentName() + ", positionName=" + getPositionName() + ", basicWage=" + getBasicWage() + ", secureReduce="
				+ getSecureReduce() + ", level=" + getLevel() + ", memo=" + getMemo() + "]";
	}

}
