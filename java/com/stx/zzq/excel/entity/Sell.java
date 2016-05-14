package com.stx.zzq.excel.entity;

/**
 * 销售记录
 * 
 * @author zhangzunqiao
 */
public class Sell {

	/* 销售id */
	private String sellId;
	/* 员工id */
	private String employeeId;
	/* 员工姓名 */
	private String employeeName;
	/* 销售年份 */
	private String sellYear;
	private String sellMonth;
	/* 销售金额 */
	private float sellMoney;

	private String memo;

	public String getSellId() {
		return sellId;
	}

	public void setSellId(String sellId) {
		this.sellId = sellId;
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

	public String getSellYear() {
		return sellYear;
	}

	public void setSellYear(String sellYear) {
		this.sellYear = sellYear;
	}

	public String getSellMonth() {
		return sellMonth;
	}

	public void setSellMonth(String sellMonth) {
		this.sellMonth = sellMonth;
	}

	public float getSellMoney() {
		return sellMoney;
	}

	public void setSellMoney(float sellMoney) {
		this.sellMoney = sellMoney;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	@Override
	public String toString() {
		return "Sell [sellId=" + sellId + ", employeeId=" + employeeId + ", employeeName=" + employeeName
				+ ", sellYear=" + sellYear + ", sellMonth=" + sellMonth + ", sellMoney=" + sellMoney + ", memo=" + memo
				+ "]";
	}

}
