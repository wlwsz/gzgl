package com.stx.zzq.excel.entity;

/**
 * 员工
 * 
 * @author zhangzunqiao
 */
public class Employee {

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
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getGraduateSchool() {
		return graduateSchool;
	}

	public void setGraduateSchool(String graduateSchool) {
		this.graduateSchool = graduateSchool;
	}

	public String getSchoolRecord() {
		return schoolRecord;
	}

	public void setSchoolRecord(String schoolRecord) {
		this.schoolRecord = schoolRecord;
	}

	public String getPositionId() {
		return positionId;
	}

	public void setPositionId(String positionId) {
		this.positionId = positionId;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEditTime() {
		return editTime;
	}

	public void setEditTime(String editTime) {
		this.editTime = editTime;
	}

	public String getIconPath() {
		return iconPath;
	}

	public void setIconPath(String iconPath) {
		this.iconPath = iconPath;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", name=" + name + ", cardNumber=" + cardNumber + ", nation="
				+ nation + ", sex=" + sex + ", birthday=" + birthday + ", graduateSchool=" + graduateSchool
				+ ", schoolRecord=" + schoolRecord + ", positionId=" + positionId + ", positionName=" + positionName
				+ ", telephone=" + telephone + ", email=" + email + ", editTime=" + editTime + ", iconPath=" + iconPath
				+ ", memo=" + memo + ", password=" + password + "]";
	}

}
