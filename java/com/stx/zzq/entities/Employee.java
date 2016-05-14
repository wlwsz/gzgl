package com.stx.zzq.entities;

import com.stx.zzq.base.BaseEntity;

/**
 * 员工
 * 
 * @author zhangzunqiao
 */
public class Employee extends BaseEntity {

	/* 员工编号 */
	private String employeeId;
	/* 员工姓名 */
	private String name;
	/* 身份证号 */
	private String cardNumber;
	/* 民族 */
	private String nation;
	/* 性别 */
	private String sex;
	/* 生日 */
	private String birthday;
	/* 毕业学校 */
	private String graduateSchool;
	/* 学历 */
	private String schoolRecord;
	/* 职位 编号 */
	private String positionId;
	/* 职位名称 */
	private String positionName;
	/* 电话号码 */
	private String telephone;
	/* email地址 */
	private String email;
	/* 编辑时间 */
	private String editTime;
	/* 头像路径 */
	private String iconPath;
	/* 描述信息 */
	private String memo;

	/* 每个员工都是此系统的普通用户 */
	private String password;

	public String getEmployeeId() {
		return (String) get("employeeId");
	}

	public void setEmployeeId(String employeeId) {
		put("employeeId", employeeId);
	}

	public String getName() {
		return (String) get("name");
	}

	public void setName(String name) {
		put("name", name);
	}

	public String getCardNumber() {
		return (String) get("cardNumber");
	}

	public void setCardNumber(String cardNumber) {
		put("cardNumber", cardNumber);
	}

	public String getNation() {
		return (String) get("nation");
	}

	public void setNation(String nation) {
		put("nation", nation);
	}

	public String getSex() {
		return (String) get("sex");
	}

	public void setSex(String sex) {
		put("sex", sex);
	}

	public String getBirthday() {
		return (String) get("birthday");
	}

	public void setBirthday(String birthday) {
		put("birthday", birthday);
	}

	public String getGraduateSchool() {
		return (String) get("graduateSchool");
	}

	public void setGraduateSchool(String graduateSchool) {
		put("graduateSchool", graduateSchool);
	}

	public String getSchoolRecord() {
		return (String) get("schoolRecord");
	}

	public void setSchoolRecord(String schoolRecord) {
		put("schoolRecord", schoolRecord);
	}

	public String getPositionId() {
		return (String) get("positionId");
	}

	public void setPositionId(String positionId) {
		put("positionId", positionId);
	}

	public String getTelephone() {
		return (String) get("telephone");
	}

	public void setTelephone(String telephone) {
		put("telephone", telephone);
	}

	public String getEmail() {
		return (String) get("email");
	}

	public void setEmail(String email) {
		put("email", email);
	}

	public String getEditTime() {
		return (String) get("editTime");
	}

	public void setEditTime(String editTime) {
		put("editTime", editTime);
	}

	public void setIconPath(String iconPath) {
		put("iconPath", iconPath);
	}

	public String getIconPath() {
		return (String) get("iconPath");
	}

	public String getMemo() {
		return (String) get("memo");
	}

	public void setMemo(String memo) {
		put("memo", memo);
	}

	public String getPositionName() {
		return (String) get("positionName");
	}

	public void setPositionName(String positionName) {
		put("positionName", positionName);
	}

	public void setPassword(String password) {
		put("password", password);
	}

	public String getPassword() {
		return (String) get("password");
	}

	@Override
	public String toString() {
		return "Employee [employeeId=" + getEmployeeId() + ", name="
				+ getName() + ", cardNumber=" + getCardNumber() + ", nation="
				+ getNation() + ", sex=" + getSex() + ", birthday="
				+ getBirthday() + ", graduateSchool=" + getGraduateSchool()
				+ ", schoolRecord=" + getSchoolRecord() + ", positionId="
				+ getPositionId() + ",positionName=" + getPositionName()
				+ ", telephone=" + getTelephone() + ", email=" + getEmail()
				+ ", editTime=" + getEditTime() + ", memo=" + getMemo() + "]";
	}

}
