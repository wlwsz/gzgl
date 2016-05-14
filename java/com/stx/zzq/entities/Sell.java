package com.stx.zzq.entities;

import com.stx.zzq.base.BaseEntity;

/**
 * 销售记录
 * 
 * @author zhangzunqiao
 */
public class Sell extends BaseEntity {

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
	private String sellMoney;
	// 评价
	private String memo;

	public String getSellId() {
		return (String) get("sellId");
	}

	public void setSellId(String sellId) {
		put("sellId", sellId);
	}

	public String getEmployeeId() {
		return (String) get("employeeId");
	}

	public void setEmployeeId(String employeeId) {
		put("employeeId", employeeId);
	}

	public String getSellYear() {
		return (String) get("sellYear");
	}

	public void setSellYear(String sellYear) {
		put("sellYear", sellYear);
	}

	public String getSellMonth() {
		return (String) get("sellMonth");
	}

	public void setSellMonth(String sellMonth) {
		put("sellMonth", sellMonth);
	}

	public String getSellMoney() {
		return (String) get("sellMoney");
	}

	public void setSellMoney(String sellMoney) {
		put("sellMoney", sellMoney);
	}

	public String getEmployeeName() {
		return (String) get("employeeName");
	}

	public void setEmployeeName(String employeeName) {
		put("employeeName", employeeName);
	}
	
	public String getMemo() {
		return (String) get("memo");
	}

	public void setMemo(String memo) {
		put("memo", memo);
	}

	@Override
	public String toString() {
		return "Sell [sellId=" + getSellId() + ", employeeId="
				+ getEmployeeId() + ", employeeName=" + getEmployeeName() 
				+ ", sellYear=" + getSellYear()
				+ ", sellMonth=" + getSellMonth() + ", sellMoney="
				+ getSellMoney() + getMemo() + "]";
	}

}
