package com.stx.zzq.entities;

import com.stx.zzq.base.BaseEntity;

/**
 * 扣税
 * 
 * @author zzq_eason
 */
public class Deduction extends BaseEntity {

	/* 编号自增长 */
	private Integer deductionId;
	/* 职位编号 */
	private String positionId;
	/* 职位名称 */
	private String positionName;
	/* 部门编号 */
	private String departmentId;
	/* 部门名称 */
	private String departmentName;
	/* 五险一金 */
	private String secureReduce;
	/* 交通补助 */
	private String trafficWage;
	/* 税收扣除 */
	private String taxReduce;
	/* 总扣除 */
	private String totalReduce;

	public Integer getDeductionId() {
		return (Integer) get("deductionId");
	}

	public void setDeductionId(Integer deductionId) {
		put("deductionId", deductionId);
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

	public String getTrafficWage() {
		return (String) get("trafficWage");
	}

	public void setTrafficWage(String trafficWage) {
		put("trafficWage", trafficWage);
	}

	public String getTaxReduce() {
		return (String) get("taxReduce");
	}

	public void setTaxReduce(String taxReduce) {
		put("taxReduce", taxReduce);
	}

	public String getTotalReduce() {
		return (String) get("totalReduce");
	}

	public void setTotalReduce(String totalReduce) {
		totalReduce = String.valueOf((Float.parseFloat(this.getSecureReduce()) + Float.parseFloat(this.getTaxReduce()) - Float.parseFloat(this.getTrafficWage())));
		put("totalReduce", totalReduce);
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

	public String getSecureReduce() {
		return (String) get("secureReduce");
	}

	public void setSecureReduce(String secureReduce) {
		put("secureReduce", secureReduce);
	}

	@Override
	public String toString() {
		return "Deduction [deductionId=" + getDeductionId() + ", positionId=" + getPositionId() + ", positionName="
				+ getPositionName() + ", departmentId=" + getDepartmentId() + ", departmentName=" + getDepartmentName()
				+ ", trafficWage=" + getTrafficWage() + ", taxReduce=" + getTaxReduce() + ", totalReduce="
				+ getTotalReduce() + "]";
	}

}
