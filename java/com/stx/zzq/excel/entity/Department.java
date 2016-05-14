package com.stx.zzq.excel.entity;

/**
 * 部门
 * 
 * @author zhangzunqiao
 */
public class Department {

	/* 部门编号 */
	private String departmentId;
	/* 部门名称 */
	private String departmentName;

	/* 部门描述 */
	private String memo;

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

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	@Override
	public String toString() {
		return "Department [departmentId=" + departmentId + ", departmentName=" + departmentName + ", memo=" + memo
				+ "]";
	}

}
