package com.stx.zzq.entities;

import com.stx.zzq.base.BaseEntity;

/**
 * 工资计算方式
 * 
 * @author zzq_eason
 *
 */
public class WageCountWay extends BaseEntity {

	private Integer id;
	/* 职位编号 */
	private String positionId;
	/* 职位名称 */
	private String positionName;
	/* 加班每小时多少钱 */
	private String ohMoneny;
	/* 迟到每小时扣除多少钱 */
	private String cdMoneny;
	/* 早退每小时 */
	private String ztMoneny;
	/* 旷工没钱多少 */
	private String kgMoneny;
	/* 五险一金扣除 */
	private String secureReduce;
	/* 职位的基本工资 */
	private String basicWage;
	/* 销售提成，总销售的百分比 */
	private String percent;

	public Integer getId() {
		return (Integer) get("id");
	}

	public void setId(Integer id) {
		put("id", id);
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

	public String getOhMoneny() {
		return (String) get("ohMoneny");
	}

	public void setOhMoneny(String ohMoneny) {
		put("ohMoneny", ohMoneny);
	}

	public String getCdMoneny() {
		return (String) get("cdMoneny");
	}

	public void setCdMoneny(String cdMoneny) {
		put("cdMoneny", cdMoneny);
	}

	public String getKgMoneny() {
		return (String) get("kgMoneny");
	}

	public void setKgMoneny(String kgMoneny) {
		put("kgMoneny", kgMoneny);
	}

	public String getSecureReduce() {
		return (String) get("secureReduce");
	}

	public void setSecureReduce(String secureReduce) {
		put("secureReduce", secureReduce);
	}

	public String getBasicWage() {
		return (String) get("basicWage");
	}

	public void setBasicWage(String basicWage) {
		put("basicWage", basicWage);
	}

	public String getPercent() {
		return (String) get("percent");
	}

	public void setPercent(String percent) {
		put("percent", percent);
	}

	public String getZtMoneny() {
		return (String) get("ztMoneny");
	}

	public void setZtMoneny(String ztMoneny) {
		put("ztMoneny", ztMoneny);
	}

	@Override
	public String toString() {
		return "WageCountWay [id=" + getId() + ", positionId=" + getPositionId() + ", positionName=" + getPositionName()
				+ ", ohMoneny=" + getOhMoneny() + ", cdMoneny=" + getCdMoneny() + ", ztMoneny=" + getZtMoneny()
				+ ", kgMoneny=" + getKgMoneny() + ", secureReduce=" + getSecureReduce() + ", basicWage="
				+ getBasicWage() + ", percent=" + getPercent() + "]";
	}

}
