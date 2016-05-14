package com.stx.zzq.back.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.stx.zzq.back.service.EmployeeService;
import com.stx.zzq.back.service.SellService;
import com.stx.zzq.base.BaseAction;
import com.stx.zzq.common.utils.CommonUtils;
import com.stx.zzq.common.utils.ConstantsCode;
import com.stx.zzq.entities.Attendance;
import com.stx.zzq.entities.Employee;
import com.stx.zzq.entities.Sell;

@Scope("prototype")
@Controller("back.SellAction")
public class SellAction extends BaseAction {

	@Autowired
	private SellService sellService;
	@Autowired
	private EmployeeService empService;

	/* 此处是设置页数和显示的条数 */
	private Integer rows;
	private Integer page;

	/* 添加销售 */
	public String add() {
		Sell add = new Sell();
		Employee emp = new Employee();
		add.setSellId(request.getParameter("sellId"));
		String id = request.getParameter("employeeId");
		emp = empService.findById(id);
		add.setEmployeeId(emp.getEmployeeId());
		add.setEmployeeName(emp.getName());
		add.setSellYear(request.getParameter("sellYear"));
		add.setSellMonth(request.getParameter("sellMonth"));
		add.setMemo(request.getParameter("memo"));
		add.setSellMoney(request.getParameter("sellMoney"));
		sellService.add(add);
		add.put("success", "添加成功");
		writeJsonToResponse(add, response);
		return "add";
	}

	/* 查询全部销售 */
	public String findAll() {
		List<Sell> all = new ArrayList<Sell>();
		all = sellService.findAll();
		writeJsonToResponse(all, response);
		return "findAll";
	}

	/* 搜索 */
	public String searchByKey() {
		List<Sell> listSell = new ArrayList<Sell>();
		String s_name = request.getParameter("employeeName");
		String s_year = request.getParameter("year");
		String s_month = request.getParameter("month");
		// 处理中文乱码
		if (!CommonUtils.isEmpty(s_name) && s_name.getBytes().length != s_name.length()) {
			try {
				s_name = new String(s_name.getBytes("iso-8859-1"), ConstantsCode.ENCODE);
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
		if (CommonUtils.isEmpty(s_name) && CommonUtils.isEmpty(s_year) && CommonUtils.isEmpty(s_month)) {
			listSell = sellService.findAll();
		}
		// 查询一个字段
		else if (!CommonUtils.isEmpty(s_name) && CommonUtils.isEmpty(s_year) && CommonUtils.isEmpty(s_month)) {
			// s_id
			listSell = sellService.searchByKey(s_name, ConstantsCode.CODE0);
		} else if (CommonUtils.isEmpty(s_name) && !CommonUtils.isEmpty(s_year) && CommonUtils.isEmpty(s_month)) {
			// s_positionName
			listSell = sellService.searchByKey(s_year, ConstantsCode.CODE1);
		} else if (CommonUtils.isEmpty(s_name) && CommonUtils.isEmpty(s_year) && !CommonUtils.isEmpty(s_month)) {
			// s_departmentName
			listSell = sellService.searchByKey(s_month, ConstantsCode.CODE2);
		}
		// 查询两个字段
		else if (!CommonUtils.isEmpty(s_name) && !CommonUtils.isEmpty(s_year) && CommonUtils.isEmpty(s_month)) {
			// s_id @ s_positionName
			listSell = sellService.searchByKey(s_name, ConstantsCode.CODE0, s_year, ConstantsCode.CODE1);
		} else if (!CommonUtils.isEmpty(s_name) && CommonUtils.isEmpty(s_year) && !CommonUtils.isEmpty(s_month)) {
			// s_id @ s_departmentName
			listSell = sellService.searchByKey(s_name, ConstantsCode.CODE0, s_month, ConstantsCode.CODE2);
		} else if (CommonUtils.isEmpty(s_name) && !CommonUtils.isEmpty(s_year) && !CommonUtils.isEmpty(s_month)) {
			// s_positionName @ s_departmentName
			listSell = sellService.searchByKey(s_year, ConstantsCode.CODE1, s_month, ConstantsCode.CODE2);
		}
		// 查询全部条件
		else {
			listSell = sellService.searchByKey(s_name, s_year, s_month);
		}

		writeJsonToResponse(listSell, response);
		return SUCCESS;
	}

	/* 修改销售 */
	public String updSell() {
		Sell updSell = new Sell();
		Employee emp = new Employee();
		updSell.setSellId(request.getParameter("sellId"));
		emp = empService.findById(request.getParameter("employeeId"));
		updSell.setEmployeeId(emp.getEmployeeId());
		updSell.setEmployeeName(emp.getName());
		updSell.setSellYear(request.getParameter("sellYear"));
		updSell.setSellMonth(request.getParameter("sellMonth"));
		updSell.setMemo(request.getParameter("memo"));
		updSell.setSellMoney(request.getParameter(
				"sellMoney"));
		sellService.updSell(updSell);
		updSell.put("success", "更新成功");
		writeJsonToResponse(updSell, response);
		return "updSell";
	}

	/* 通过id来修改数据 */
	public String updById() {
		Employee emp = null;
		emp = empService.findById(request.getParameter("employeeId"));
		if (CommonUtils.isEmpty(emp)) {
			emp = new Employee();
			emp.put("fail", "没有此员工");
			writeJsonToResponse(emp, response);
			return "updById";
		}
		Sell updById = new Sell();
		updById.setSellId(request.getParameter("sellId"));
		updById.setEmployeeId(emp.getEmployeeId());
		updById.setEmployeeName(emp.getName());
		updById.setSellYear(request.getParameter("sellYear"));
		updById.setSellMonth(request.getParameter("sellMonth"));
		updById.setMemo(request.getParameter("memo"));
		updById.setSellMoney(request.getParameter("sellMoney"));
		sellService.updSell(updById);
		updById.put("success", "更新成功");
		writeJsonToResponse(updById, response);
		return "updById";
	}

	/* 删除销售 */
	public String delSell() {
		String id = request.getParameter("sellId");
		Sell delSell = new Sell();
		delSell.setSellId(id);
		sellService.delSell(delSell);
		delSell.put("success", "删除成功");
		writeJsonToResponse(delSell, response);
		return "delSell";
	}

	/* 通过id来删除数据 */
	public String delById() {
		String id = request.getParameter("sellId");
		Sell delById = new Sell();
		int i = sellService.delById(id);
		if (i == 0) {
			delById.put("errorMsg", "删除失败");
			writeJsonToResponse(delById, response);
			return "delById";
		}
		delById.put("success", "删除成功");
		writeJsonToResponse(delById, response);
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

	/* 添加销售 */
	/* 添加销售 */
	/* 添加销售 */

}
