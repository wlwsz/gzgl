package com.stx.zzq.entities;

import net.sf.json.JSONString;

import com.stx.zzq.base.BaseEntity;

/**
 * 考勤
 * 
 * @author zhangzunqiao
 */
public class Attendance extends BaseEntity {

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
	private String overHour;
	/* 迟到分钟数 */
	private String chidao;
	/* 早退总计分钟数 */
	private String zaotui;
	/* 员工旷工天数 */
	private String kuangGong;
	/* 附加信息 */
	private String memo;

	public Integer getAttendanceId() {
		return (Integer) get("attendanceId");
	}

	public void setAttendanceId(Integer attendanceId) {
		put("attendanceId", attendanceId);
	}

	public String getEmployeeId() {
		return (String) get("employeeId");
	}

	public void setEmployeeId(String employeeId) {
		put("employeeId", employeeId);
	}

	public String getYear() {
		return (String) get("year");
	}

	public void setYear(String year) {
		put("year", year);
	}

	public String getMonth() {
		return (String) get("month");
	}

	public void setMonth(String month) {
		put("month", month);
	}

	public String getOverHour() {
		return (String) get("overHour");
	}

	public void setOverHour(String overHour) {
		put("overHour", overHour);
	}

	public String getChidao() {
		return (String) get("chidao");
	}

	public void setChidao(String chidao) {
		put("chidao", chidao);
	}

	public String getZaotui() {
		return (String) get("zaotui");
	}

	public void setZaotui(String zaotui) {
		put("zaotui", zaotui);
	}

	public String getKuangGong() {
		return (String) get("kuangGong");
	}

	public void setKuangGong(String kuangGong) {
		put("kuangGong", kuangGong);
	}

	public String getMemo() {
		return (String) get("memo");
	}

	public void setMemo(String memo) {
		put("memo", memo);
	}

	public String getEmployeeName() {
		return (String) get("employeeName");
	}

	public void setEmployeeName(String employeeName) {
		put("employeeName", employeeName);
	}

	@Override
	public String toString() {
		return "Attendance [attendanceId=" + getAttendanceId()
				+ ", employeeId=" + getEmployeeId() + ", year=" + getYear()
				+ ", month=" + getMonth() + ", overHour=" + getOverHour()
				+ ", chidao=" + getChidao() + ", zaotui=" + getZaotui()
				+ ", kuangGong=" + getKuangGong() + ", employeeName=" + getEmployeeName()
				+ ", memo=" + getMemo() + "]";
	}

}
