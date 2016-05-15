package com.stx.zzq.entities;

import com.stx.zzq.base.BaseEntity;

/**
 * 工资
 * 
 * @author zhangzunqiao
 */
public class Salary extends BaseEntity {

	/* 工资编号 */
	private Integer salaryId;
	/* 员工编号 */
	private String employeeId;
	/* 员工姓名 */
	private String employeeName;
	/* 职位 编号 */
	private String positionId;
	/* 职位名称 */
	private String positionName;
	/* 考勤月份 */
	private String month;
	/* 考勤年份 */
	private String year;
	/* 基本工资 */
	private String basicWage;
	/* 加班工资 */
	private String overtimeWage;
	/* 销售提成 */
	private String sellmoneyGet;
	/* 总工资 */
	private String totalWage;
	/* 总扣除 */
	private String totalReduce;
	/* 实际工资 */
	private String realWage;
	/* 编辑时间 */
	private String editTime;
	/* 附加信息 */
	private String memo;

	public Integer getSalaryId() {
		return (Integer) get("salaryId");
	}

	public void setSalaryId(Integer salaryId) {
		put("salaryId", salaryId);
	}

	public String getEmployeeId() {
		return (String) get("employeeId");
	}

	public void setEmployeeId(String employeeId) {
		put("employeeId", employeeId);
	}

	public String getEmployeeName() {
		return (String) get("employeeName");
	}

	public void setEmployeeName(String employeeName) {
		put("employeeName", employeeName);
	}

	public String getMonth() {
		return (String) get("month");
	}

	public void setMonth(String month) {
		put("month", month);
	}

	public String getYear() {
		return (String) get("year");
	}

	public void setYear(String year) {
		put("year", year);
	}

	public String getBasicWage() {
		return (String) get("basicWage");
	}

	public void setBasicWage(String basicWage) {
		put("basicWage", basicWage);
	}

	public String getOvertimeWage() {
		return (String) get("overtimeWage");
	}

	public void setOvertimeWage(String overtimeWage) {
		put("overtimeWage", overtimeWage);
	}

	public String getSellmoneyGet() {
		return (String) get("sellmoneyGet");
	}

	public void setSellmoneyGet(String sellmoneyGet) {
		put("sellmoneyGet", sellmoneyGet);
	}

	public String getTotalWage() {
		return (String) get("totalWage");
	}

	public void setTotalWage(String totalWage) {
		put("totalWage", totalWage);
	}

	public String getTotalReduce() {
		return (String) get("totalReduce");
	}

	public void setTotalReduce(String totalReduce) {
		put("totalReduce", totalReduce);
	}

	public String getRealWage() {
		return (String) get("realWage");
	}

	public void setRealWage(String realWage) {
		put("realWage", realWage);
	}

	public String getEditTime() {
		return (String) get("editTime");
	}

	public void setEditTime(String editTime) {
		put("editTime", editTime);
	}

	public String getMemo() {
		return (String) get("memo");
	}

	public void setMemo(String memo) {
		put("memo", memo);
	}

	public String getPositionId() {
		return (String) get("positionId");
	}

	public void setPositionId(String positionId) {
		put("positionId", positionId);
	}

	public String getPositionName() {
		return (String) get("positionName");
	}

	public void setPositionName(String positionName) {
		put("positionName", positionName);
	}

	@Override
	public String toString() {
		return "Salary [salaryId=" + getSalaryId() + ", employeeId=" + getEmployeeId() + ", employeeName=" + getEmployeeName()
				+ ", positionId=" + getPositionId() + ", positionName=" + getPositionName() + ", month=" + getMonth() + ", year="
				+ getYear() + ", basicWage=" + getBasicWage() + ", overtimeWage=" + getOvertimeWage() + ", sellmoneyGet="
				+ getSellmoneyGet() + ", totalWage=" + getTotalWage() + ", totalReduce=" + getTotalReduce() + ", realWage=" + getRealWage()
				+ ", editTime=" + getEditTime() + ", memo=" + getMemo() + "]";
	}

}
