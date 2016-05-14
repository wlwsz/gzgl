package com.stx.zzq.excel.entity;

public class Attendance {
	/* 考情编号 */
	private Integer attendanceId;
	/* 员工编号 */
	private String employeeId;
	/* 员工姓名 */
	private String employeeName;
	/* 考勤年份 */
	private String year;
	/* 考勤月份 */
	private String month;
	/* 加班时间 */
	private float overHour;
	/* 迟到分钟数 */
	private int chidao;
	/* 早退总计分钟数 */
	private int zaotui;
	/* 员工旷工天数 */
	private float kuangGong;
	/* 附加信息 */
	private String memo;

	public Integer getAttendanceId() {
		return attendanceId;
	}

	public void setAttendanceId(Integer attendanceId) {
		this.attendanceId = attendanceId;
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

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public float getOverHour() {
		return overHour;
	}

	public void setOverHour(float overHour) {
		this.overHour = overHour;
	}

	public int getChidao() {
		return chidao;
	}

	public void setChidao(int chidao) {
		this.chidao = chidao;
	}

	public int getZaotui() {
		return zaotui;
	}

	public void setZaotui(int zaotui) {
		this.zaotui = zaotui;
	}

	public float getKuangGong() {
		return kuangGong;
	}

	public void setKuangGong(float kuangGong) {
		this.kuangGong = kuangGong;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	@Override
	public String toString() {
		return "Attendance [attendanceId=" + attendanceId + ", employeeId=" + employeeId + ", employeeName="
				+ employeeName + ", year=" + year + ", month=" + month + ", overHour=" + overHour + ", chidao=" + chidao
				+ ", zaotui=" + zaotui + ", kuangGong=" + kuangGong + ", memo=" + memo + "]";
	}

}
