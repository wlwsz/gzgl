package com.stx.zzq.entities;

import net.sf.json.JSONString;

import com.stx.zzq.base.BaseEntity;

/**
 * 员工等级
 * @author zhangzunqiao
 */
public class Grade extends BaseEntity implements JSONString {

	/* 等级编号 */
	private String gradeId;
	/* 员工编号 */
	private String employeeId;
	/* 员工部门 */
	private String departmentId;
	/* 职位编号 */
	private String positionId;

	public String getGradeId() {
		return (String) get("gradeId");
	}

	public void setGradeId(String gradeId) {
		put("gradeId", gradeId);
	}

	public String getEmployeeId() {
		return (String) get("employeeId");
	}

	public void setEmployeeId(String employeeId) {
		put("employeeId", employeeId);
	}

	public String getDepartmentId() {
		return (String) get("departmentId");
	}

	public void setDepartmentId(String departmentId) {
		put("departmentId", departmentId);
	}

	public String getPositionId() {
		return (String) get("positionId");
	}

	public void setPositionId(String positionId) {
		put("positionId", positionId);
	}

	@Override
	public String toString() {
		return "Grade [gradeId=" + getGradeId() + ", employeeId=" + getEmployeeId()
				+ ", departmentId=" + getDepartmentId()+ ", positionId="
				+ getPositionId() + "]";
	}

	@Override
	public String toJSONString() {
		// TODO Auto-generated method stub
		return "{gradeId:'" + getGradeId() + "', employeeId:'" + getEmployeeId()
				+ "', departmentId:'" + getDepartmentId()+ "', positionId:'"
				+ getPositionId() + "'}";
	}

}
