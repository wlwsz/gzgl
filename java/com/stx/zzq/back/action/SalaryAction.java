package com.stx.zzq.back.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.stx.zzq.back.service.AttendanceService;
import com.stx.zzq.back.service.DeductionService;
import com.stx.zzq.back.service.EmployeeService;
import com.stx.zzq.back.service.SalaryService;
import com.stx.zzq.back.service.SellService;
import com.stx.zzq.back.service.WageCountWayService;
import com.stx.zzq.base.BaseAction;
import com.stx.zzq.common.utils.CommonUtils;
import com.stx.zzq.entities.Attendance;
import com.stx.zzq.entities.Deduction;
import com.stx.zzq.entities.Employee;
import com.stx.zzq.entities.Salary;
import com.stx.zzq.entities.Sell;
import com.stx.zzq.entities.WageCountWay;

@Scope("prototype")
@Controller("back.SalaryAction")
public class SalaryAction extends BaseAction {

	@Autowired
	private SalaryService salService;
	// 员工
	@Autowired
	private EmployeeService empService;
	// 考勤
	@Autowired
	private AttendanceService attendanceService;
	// 工资计算方式
	@Autowired
	private WageCountWayService wageWayService;
	// 扣税
	@Autowired
	private DeductionService deductionService;
	// 销售信息
	@Autowired
	private SellService sellService;

	/* 此处是设置页数和显示的条数 */
	private Integer rows;
	private Integer page;

	/* 添加工资 */
	public String add() {
		Salary add = new Salary();
		Employee emp = new Employee();
		String empId = request.getParameter("employeeId");
		emp = empService.findById(empId);
		add.setEmployeeId(emp.getEmployeeId());
		add.setEmployeeName(emp.getName());
		add.setMonth(request.getParameter("month"));
		add.setBasicWage(request.getParameter("basicWage"));
		add.setOvertimeWage(request.getParameter("overtimeWage"));
		add.setSellmoneyGet(request.getParameter("sellmoneyGet"));
		add.setEditTime(CommonUtils.getCurrentTime());
		add.setMemo(request.getParameter("memo"));
		salService.add(add);
		add.put("success", "添加成功");
		writeJsonToResponse(add, response);
		return "add";
	}

	/* 查询全部的工资 */
	public String findAll() {
		List<Salary> all = new ArrayList<Salary>();
		createWageList();
		all = salService.findAll();
		
		writeJsonToResponse(all, response);
		return "findAll";
	}

	// 添加工资
	private String createWageList() {
		List<Salary> listSalary = new ArrayList<Salary>();
		listSalary = salService.findAll();
		if (CommonUtils.isEmpty(listSalary)) {
			String result = "{\"success\":\"false\",\"Msg\":\"工资列表为空，请查看是否有员工入职！！\"}";
			responseWrite(result);
			return "listSalary";
		}
		// 计算工资列表
		for (Salary salary : listSalary) {
			if (CommonUtils.isEmpty(salary.getPositionId())) {
				String result = "{\"success\":\"false\",\"Msg\":\"员工未入职！！\"}";
				salService.delById(salary.getSalaryId());
				responseWrite(result);
				return "listSalary";
			}
			countWage(salary);
		}

		return "listSalary";
	}

	/* 工资计算方式, 运行速度慢, 要进行改进 */
	private void countWage(Salary salary) {
		// 工资计算方式
		WageCountWay wageCountWay = new WageCountWay();
		wageCountWay = wageWayService.findByPosId(salary.getPositionId());
		if (CommonUtils.isEmpty(wageCountWay)) {
			String result = "{\"success\":\"false\",\"Msg\":\"工资待定状态！！\"}";
			responseWrite(result);
			return;
		}
		salary.setBasicWage(wageCountWay.getBasicWage());
		// 扣税
		Deduction deduction = new Deduction();
		deduction = deductionService.findByPosId(salary.getPositionId());
		if (CommonUtils.isEmpty(deduction)) {
			String result = "{\"success\":\"false\",\"Msg\":\"扣税待定状态！！\"}";
			responseWrite(result);
			return;
		}
		// 总扣除税
		salary.setTotalReduce(deduction.getTotalReduce());
		// 考勤
		Attendance attendance = new Attendance();
		attendance = attendanceService.findByEmpId(salary.getEmployeeId());
		if (CommonUtils.isEmpty(attendance)) {
			String result = "{\"success\":\"false\",\"Msg\":\"考勤为空！！\"}";
			responseWrite(result);
			return;
		}
		salary.setYear(attendance.getYear());
		salary.setMonth(attendance.getMonth());
		Sell sell = new Sell();
		sell = sellService.findByEmpId(salary.getEmployeeId());
		if (CommonUtils.isEmpty(sell)) {
			String result = "{\"success\":\"false\",\"Msg\":\"销售不存在！！\"}";
			responseWrite(result);
			return;
		}
		String other = "";
		// 销售提成
		if (attendance.getYear().equals(sell.getSellYear()) && attendance.getMonth().equals(sell.getSellMonth())) {
			// 加班工资
			salary.setOvertimeWage(String.valueOf(
					Float.parseFloat(attendance.getOverHour()) * Float.parseFloat(wageCountWay.getOhMoneny())));
			// 销售提成
			salary.setSellmoneyGet(String
					.valueOf(Float.parseFloat(sell.getSellMoney()) * Float.parseFloat(wageCountWay.getPercent())));
			// 其他扣除包括 （迟到、早退、旷工）
			other = String
					.valueOf(
							Float.parseFloat(attendance.getChidao()) * Float.parseFloat(wageCountWay.getCdMoneny()
									+ Float.parseFloat(attendance.getZaotui())
											* Float.parseFloat(wageCountWay.getZtMoneny())
									+ Float.parseFloat(attendance.getKuangGong())
											* Float.parseFloat(wageCountWay.getKgMoneny())));
			// 总工资 = 加班 + 基本工资 + 销售提成 + 交通补贴 扣税中的总扣除 = 五险一金 + 其他扣除（单独）- 交通补贴
			salary.setTotalWage(String.valueOf(Float.parseFloat(salary.getBasicWage())
					+ Float.parseFloat(salary.getOvertimeWage()) + Float.parseFloat(deduction.getTrafficWage())));
			// 实际工资 = 总工资 - 总扣除 - 交通补贴（多加了）
			salary.setRealWage(String.valueOf(Float.parseFloat(salary.getTotalWage()) - (Float.parseFloat(other))
					+ Float.parseFloat(deduction.getTotalReduce())));
		} else {
			// 总工资 = 基本工资 + 交通补贴 总扣除 = 五险一金 + 其他扣除（单独）
			salary.setSellmoneyGet("0");
			salary.setTotalReduce(String
					.valueOf(Float.parseFloat(salary.getBasicWage()) + Float.parseFloat(deduction.getTrafficWage())));
			salary.setRealWage(String.valueOf(Float.parseFloat(salary.getTotalWage())
					- Float.parseFloat(salary.getTotalReduce()) - Float.parseFloat(deduction.getTrafficWage())));
		}
		salService.add(salary);
	}

	/**
	 * json返回客户端
	 * 
	 * @param result
	 */
	private void responseWrite(String result) {
		try {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println(result);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/* 查询某个人工资 */
	public String findByEmpId() {
		String empId = request.getParameter("employeeId");
		Employee emp = new Employee();
		emp = empService.findById(empId);
		Salary sal = new Salary();
		sal = salService.findByEmpId(emp);
		writeJsonToResponse(sal, response);
		return "findByEmpId";
	}

	/* 某个月份的工资 */
	/* 某年的工资 */
	/* 删除工资记录 */
	public String delSal() {
		String id = request.getParameter("salaryId");
		Salary delSal = new Salary();
		delSal.setSalaryId(Integer.parseInt(id));
		salService.delSal(delSal);
		delSal.put("success", "删除成功");
		writeJsonToResponse(delSal, response);
		return "delSal";
	}

	/* id删除 */
	public String delById() {
		// TODO
		int i = 0;
		int id = Integer.parseInt(request.getParameter("salaryId"));
		Salary sal = new Salary();
		i = salService.delById(id);
		if (i == 0) {
			sal.put("errMsg", "删除失败");
		} else {
			sal.put("success", "删除成功");
		}
		writeJsonToResponse(sal, response);

		return "delById";
	}

	/* 修改工资 */
	public String updSal() {
		Salary updSal = new Salary();
		Employee emp = new Employee();
		String empId = request.getParameter("employeeId");
		emp = empService.findById(empId);
		updSal.setSalaryId(Integer.parseInt(request.getParameter("salaryId")));
		updSal.setEmployeeId(emp.getEmployeeId());
		updSal.setEmployeeName(emp.getName());
		updSal.setMonth(request.getParameter("month"));
		updSal.setBasicWage(request.getParameter("basicWage"));
		updSal.setOvertimeWage(request.getParameter("overtimeWage"));
		updSal.setSellmoneyGet(request.getParameter("sellmoneyGet"));
		updSal.setTotalWage(request.getParameter("totalWage"));
		updSal.setTotalReduce(request.getParameter("totalReduce"));
		updSal.setRealWage(request.getParameter("realWage"));
		updSal.setEditTime(request.getParameter("editTime"));
		updSal.setMemo(request.getParameter("memo"));
		salService.updSal(updSal);
		return "updSal";
	}

	/**
	 * 通过id 修改
	 * 
	 * @return
	 */
	public String updById() {
		Salary updById = new Salary();
		Employee emp = new Employee();
		String empId = request.getParameter("employeeId");
		emp = empService.findById(empId);
		updById.setSalaryId(Integer.parseInt(request.getParameter("salaryId")));
		updById.setEmployeeId(emp.getEmployeeId());
		updById.setEmployeeName(emp.getName());
		updById.setMonth(request.getParameter("month"));
		updById.setBasicWage(request.getParameter("basicWage"));
		updById.setOvertimeWage(request.getParameter("overtimeWage"));
		updById.setSellmoneyGet(request.getParameter("sellmoneyGet"));
		updById.setEditTime(CommonUtils.getCurrentTime());
		updById.setMemo(request.getParameter("memo"));
		salService.updById(updById);
		updById.put("success", "修改成功");
		writeJsonToResponse(updById, response);

		return "updById";
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

}
