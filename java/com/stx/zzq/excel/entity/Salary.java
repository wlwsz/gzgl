package com.stx.zzq.excel.entity;

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
	/* 考勤月份 */
	private String month;
	/* 编辑时间 */
	private String editTime;
	/* 附加信息 */
	private String memo;

	public Integer getSalaryId() {
		return salaryId;
	}

	public void setSalaryId(Integer salaryId) {
		this.salaryId = salaryId;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getBasicWage() {
		return basicWage;
	}

	public void setBasicWage(String basicWage) {
		this.basicWage = basicWage;
	}

	public String getOvertimeWage() {
		return overtimeWage;
	}

	public void setOvertimeWage(String overtimeWage) {
		this.overtimeWage = overtimeWage;
	}

	public String getSellmoneyGet() {
		return sellmoneyGet;
	}

	public void setSellmoneyGet(String sellmoneyGet) {
		this.sellmoneyGet = sellmoneyGet;
	}

	public String getTotalWage() {
		return totalWage;
	}

	public void setTotalWage(String totalWage) {
		this.totalWage = totalWage;
	}

	public String getTotalReduce() {
		return totalReduce;
	}

	public void setTotalReduce(String totalReduce) {
		this.totalReduce = totalReduce;
	}

	public String getRealWage() {
		return realWage;
	}

	public void setRealWage(String realWage) {
		this.realWage = realWage;
	}

	public String getEditTime() {
		return editTime;
	}

	public void setEditTime(String editTime) {
		this.editTime = editTime;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	@Override
	public String toString() {
		return "Salary [salaryId=" + salaryId + ", employeeId=" + employeeId + ", employeeName=" + employeeName
				+ ", month=" + month + ", basicWage=" + basicWage + ", overtimeWage=" + overtimeWage + ", sellmoneyGet="
				+ sellmoneyGet + ", totalWage=" + totalWage + ", totalReduce=" + totalReduce + ", realWage=" + realWage
				+ ", editTime=" + editTime + ", memo=" + memo + "]";
	}

}
