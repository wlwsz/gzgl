package com.stx.zzq.entities;

import com.stx.zzq.base.BaseEntity;

/**
 * 部门
 * 
 * @author zhangzunqiao
 */
public class Department extends BaseEntity {

	/* 部门编号 */
	private String departmentId;
	/* 部门名称 */
	private String departmentName;

	/* 部门描述 */
	private String memo;

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

	public void setMemo(String memo) {
		put("memo", memo);
	}

	public String getMemo() {
		return (String) get("memo");
	}

	@Override
	public String toString() {
		return "Department [departmentId=" + getDepartmentId()
				+ ", departmentName=" + getDepartmentName() + "]";
	}

}
