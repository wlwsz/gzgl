package com.stx.zzq.back.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.stx.zzq.back.service.EmployeeService;
import com.stx.zzq.back.service.SalaryService;
import com.stx.zzq.base.BaseAction;
import com.stx.zzq.common.utils.CommonUtils;
import com.stx.zzq.entities.Employee;
import com.stx.zzq.entities.Salary;

@Scope("prototype")
@Controller("back.SalaryAction")
public class SalaryAction extends BaseAction {

	@Autowired
	private SalaryService salService;
	@Autowired
	private EmployeeService empService;

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
		add.setBasicWage(request
				.getParameter("basicWage")));
		add.setOvertimeWage(Float.parseFloat(request.getParameter(
				"overtimeWage")));
		add.setSellmoneyGet(Float.parseFloat(request.getParameter(
				"sellmoneyGet")));
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
		all = salService.findAll();
		writeJsonToResponse(all, response);
		return "findAll";
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
		//TODO	
		int i = 0;
		int id = Integer.parseInt(request.getParameter("salaryId"));
		Salary sal = new Salary();
		i = salService.delById(id);
		if(i == 0){
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
		updSal.setSalaryId(Integer.parseInt(request.getParameter(
				"salaryId")));
		updSal.setEmployeeId(emp.getEmployeeId());
		updSal.setEmployeeName(emp.getName());
		updSal.setMonth(request.getParameter("month"));
		updSal.setBasicWage(Float.parseFloat(request.getParameter(
				"basicWage")));
		updSal.setOvertimeWage(Float.parseFloat(request.getParameter(
				"overtimeWage")));
		updSal.setSellmoneyGet(Float.parseFloat(request.getParameter(
				"sellmoneyGet")));
		updSal.setTotalWage(Float.parseFloat(request.getParameter(
				"totalWage")));
		updSal.setTotalReduce(Float.parseFloat(request.getParameter(
				"totalReduce")));
		updSal.setRealWage(Float.parseFloat(request.getParameter(
				"realWage")));
		updSal.setEditTime(request.getParameter("editTime"));
		updSal.setMemo(request.getParameter("memo"));
		salService.updSal(updSal);
		return "updSal";
	}
	
	/**
	 * 通过id 修改
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
		updById.setBasicWage(Float.parseFloat(request
				.getParameter("basicWage")));
		updById.setOvertimeWage(Float.parseFloat(request.getParameter(
				"overtimeWage")));
		updById.setSellmoneyGet(Float.parseFloat(request.getParameter(
				"sellmoneyGet")));
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
