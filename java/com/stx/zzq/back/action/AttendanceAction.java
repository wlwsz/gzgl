package com.stx.zzq.back.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.stx.zzq.back.service.AttendanceService;
import com.stx.zzq.back.service.EmployeeService;
import com.stx.zzq.base.BaseAction;
import com.stx.zzq.common.utils.CommonUtils;
import com.stx.zzq.common.utils.ConstantsCode;
import com.stx.zzq.entities.Attendance;
import com.stx.zzq.entities.Employee;
import com.stx.zzq.entities.Position;

@Scope("prototype")
@Controller("back.AttendanceAction")
public class AttendanceAction extends BaseAction {

	@Autowired
	private AttendanceService attService;
	@Autowired
	private EmployeeService empService;

	/* 此处是设置页数和显示的条数 */
	private Integer rows;
	private Integer page;

	/* 添加考勤记录 */
	public String add() {
		Attendance add = new Attendance();
		String employeeId = request.getParameter("employeeId");
		Employee empId = new Employee();
		empId = empService.findById(employeeId);
		add.setKuangGong(request.getParameter("kuangGong"));
		add.setEmployeeId(empId.getEmployeeId());
		add.setEmployeeName(empId.getName());
		add.setChidao(request.getParameter("chidao"));
		add.setYear(request.getParameter("year"));
		add.setMonth(request.getParameter("month"));
		add.setOverHour(request.getParameter("overHour"));
		add.setZaotui(request.getParameter("zaotui"));
		add.setMemo(request.getParameter("memo"));
		attService.add(add);
		add.put("success", "添加成功");
		writeJsonToResponse(add, response);
		return "add";
	}

	/* 查询所有的考情记录 */
	public String findAll() {
		List<Attendance> all = new ArrayList<Attendance>();
		all = attService.findAll();
		writeJsonToResponse(all, response);
		return "findAll";
	}

	/* 查询某个员工的考情记录 */
	public String findByEmpId() {
		String empId = request.getParameter("employeeId");
		Employee emp = new Employee();
		Attendance att = new Attendance();
		emp = empService.findById(empId);
		att.setEmployeeId(empId);
		att = attService.findByEmpId(att);
		return "findByEmpId";
	}

	/* 查询一段时间段的某个员工的考情记录 */

	/* 查询某条的考情记录 */
	public String findById() {
		String id = request.getParameter("attendanceId");
		Attendance attId = new Attendance();
		attId = attService.findById(id);
		return "findById";
	}

	/* 搜索功能实现 */
	public String searchByKey() {
		List<Attendance> listAttendance = new ArrayList<Attendance>();
		String s_id = request.getParameter("employeeId");
		String s_year = request.getParameter("year");
		String s_month = request.getParameter("month");
		// 处理中文乱码
		if (!CommonUtils.isEmpty(s_id) && s_id.getBytes().length != s_id.length()) {
			try {
				s_id = new String(s_id.getBytes("iso-8859-1"), ConstantsCode.ENCODE);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (!CommonUtils.isEmpty(s_year) && s_year.getBytes().length != s_year.length()) {
			try {
				s_year = new String(s_year.getBytes("iso-8859-1"), ConstantsCode.ENCODE);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (!CommonUtils.isEmpty(s_month) && s_month.getBytes().length != s_month.length()) {
			try {
				s_month = new String(s_month.getBytes("iso-8859-1"), ConstantsCode.ENCODE);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 查询全部
		if (CommonUtils.isEmpty(s_id) && CommonUtils.isEmpty(s_year) && CommonUtils.isEmpty(s_month)) {
			listAttendance = attService.findAll();
		}
		// 查询一个字段
		else if (!CommonUtils.isEmpty(s_id) && CommonUtils.isEmpty(s_year) && CommonUtils.isEmpty(s_month)) {
			// s_id
			listAttendance = attService.searchByKey(s_id, ConstantsCode.CODE0);
		} else if (CommonUtils.isEmpty(s_id) && !CommonUtils.isEmpty(s_year) && CommonUtils.isEmpty(s_month)) {
			// s_positionName
			listAttendance = attService.searchByKey(s_year, ConstantsCode.CODE1);
		} else if (CommonUtils.isEmpty(s_id) && CommonUtils.isEmpty(s_year) && !CommonUtils.isEmpty(s_month)) {
			// s_departmentName
			listAttendance = attService.searchByKey(s_month, ConstantsCode.CODE2);
		}
		// 查询两个字段
		else if (!CommonUtils.isEmpty(s_id) && !CommonUtils.isEmpty(s_year) && CommonUtils.isEmpty(s_month)) {
			// s_id @ s_positionName
			listAttendance = attService.searchByKey(s_id, ConstantsCode.CODE0, s_year, ConstantsCode.CODE1);
		} else if (!CommonUtils.isEmpty(s_id) && CommonUtils.isEmpty(s_year) && !CommonUtils.isEmpty(s_month)) {
			// s_id @ s_departmentName
			listAttendance = attService.searchByKey(s_id, ConstantsCode.CODE0, s_month, ConstantsCode.CODE2);
		} else if (CommonUtils.isEmpty(s_id) && !CommonUtils.isEmpty(s_year) && !CommonUtils.isEmpty(s_month)) {
			// s_positionName @ s_departmentName
			listAttendance = attService.searchByKey(s_year, ConstantsCode.CODE1, s_month, ConstantsCode.CODE2);
		}
		// 查询全部条件
		else {
			listAttendance = attService.searchByKey(s_id, s_year, s_month);
		}

		writeJsonToResponse(listAttendance, response);
		return SUCCESS;
	}

	/* 通过id修改考勤记录 */
	public String updById() {
		Attendance updById = new Attendance();
		String employeeId = request.getParameter("employeeId");
		Employee empId = new Employee();
		empId = empService.findById(employeeId);
		updById.setKuangGong(request.getParameter("kuangGong"));
		updById.setAttendanceId(Integer.parseInt(request.getParameter("attendanceId")));
		updById.setEmployeeId(empId.getEmployeeId());
		updById.setEmployeeName(empId.getName());
		updById.setChidao(request.getParameter("chidao"));
		updById.setYear(request.getParameter("year"));
		updById.setMonth(request.getParameter("month"));
		updById.setOverHour(request.getParameter("overHour"));
		updById.setZaotui(request.getParameter("zaotui"));
		updById.setMemo(request.getParameter("memo"));
		attService.updById(updById);
		updById.put("success", "添加成功");
		writeJsonToResponse(updById, response);
		return "updById";
	}

	/* 删除特定的考情记录 */
	public String delById() {
		String delById = request.getParameter("attendanceId");
		Attendance result = new Attendance();
		int i = 0;
		i = attService.delById(delById);
		if (i == 0) {
			result.put("errorMsg", "删除失败");
			writeJsonToResponse(result, response);
			return "delById";
		}
		result.put("success", "删除成功");
		writeJsonToResponse(result, response);
		return "delById";
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	/* 按月份查询的考情记录 */

	/* 按年查询考情记录 */

}
