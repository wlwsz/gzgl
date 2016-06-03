package com.stx.zzq.front.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.stx.zzq.back.service.EmployeeService;
import com.stx.zzq.back.service.SalaryService;
import com.stx.zzq.base.BaseAction;
import com.stx.zzq.entities.Employee;
import com.stx.zzq.entities.Salary;

@Scope("prototype")
@Controller("front.SalaryAction")
public class SalaryAction extends BaseAction {

	@Autowired
	private SalaryService salService;
	// 员工
	@Autowired
	private EmployeeService empService;
	/* 此处是设置页数和显示的条数 */
	private Integer rows;
	private Integer page;

	/* 查询某个人工资 */
	public String findByEmpId() {
		Employee emp = (Employee) session.getAttribute("employee");
		List<Salary> salaryAll = new ArrayList<Salary>();
		salaryAll = salService.findByEmpId(emp.getEmployeeId());
		request.setAttribute("all", salaryAll);
//		writeJsonToResponse(salaryAll, response);
		return SUCCESS;
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
