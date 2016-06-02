package com.stx.zzq.front.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.stx.zzq.back.service.EmployeeService;
import com.stx.zzq.back.service.PositionService;
import com.stx.zzq.back.service.SalaryService;
import com.stx.zzq.base.BaseAction;
import com.stx.zzq.common.utils.CommonUtils;
import com.stx.zzq.common.utils.ConstantsCode;
import com.stx.zzq.entities.Employee;
import com.stx.zzq.entities.Position;

@Scope("prototype")
@Controller("front.EmployeeAction")
public class EmployeeAction extends BaseAction {

	@Autowired
	private EmployeeService empService;
	@Autowired
	private PositionService posService;
	@Autowired
	private SalaryService salaryService;

	/* 此处是设置页数和显示的条数 */
	private Integer rows;
	private Integer page;
	
	public String login() {
		String id = request.getParameter("employeeId");
		Employee employee = new Employee();
		employee = empService.findById(id);
		return "findById";
	}

	/* 通过id查询员工 */
	public String findById() {
		String id = request.getParameter("employeeId");
		Employee employee = new Employee();
		employee = empService.findById(id);
		return "findById";
	}

	/* 修改员工信息员工 */
	public String updEmp() {
		Employee updEmp = new Employee();
		updEmp.setEmployeeId(request.getParameter("employeeId"));
		updEmp.setName(request.getParameter("name"));
		updEmp.setSex(request.getParameter("sex"));
		updEmp.setCardNumber(request.getParameter("cardNumber"));
		updEmp.setNation(request.getParameter("nation"));
		updEmp.setBirthday(request.getParameter("birthday"));
		String positionId = request.getParameter("positionId");
		Position posit = new Position();
		posit = posService.findById(positionId);
		updEmp.setPositionId(posit.getPositionId());
		updEmp.setPositionName(posit.getPositionName());
		updEmp.setGraduateSchool(request.getParameter("graduateSchool"));
		updEmp.setSchoolRecord(request.getParameter("schoolRecord"));
		updEmp.setTelephone(request.getParameter("telephone"));
		updEmp.setEmail(request.getParameter("email"));
		updEmp.setEditTime(request.getParameter("editTime"));
		updEmp.setMemo(request.getParameter("memo"));
		empService.updEmp(updEmp);
		updEmp.put("success", "修改成功");
		writeJsonToResponse(updEmp, response);
		return "updEmp";
	}

	/* 通过id修改员工 */
	public String updById() {
		Employee updById = new Employee();
		updById.setEmployeeId(request.getParameter("employeeId"));
		updById.setName(request.getParameter("name"));
		updById.setSex(request.getParameter("sex"));
		updById.setCardNumber(request.getParameter("cardNumber"));
		updById.setNation(request.getParameter("nation"));
		updById.setBirthday(request.getParameter("birthday"));
		String positionId = request.getParameter("positionId");
		Position posit = new Position();
		posit = posService.findById(positionId);
		updById.setPositionId(posit.getPositionId());
		updById.setPositionName(posit.getPositionName());
		updById.setGraduateSchool(request.getParameter("graduateSchool"));
		updById.setSchoolRecord(request.getParameter("schoolRecord"));
		updById.setTelephone(request.getParameter("telephone"));
		updById.setEmail(request.getParameter("email"));
		updById.setEditTime(request.getParameter("editTime"));
		updById.setMemo(request.getParameter("memo"));
		empService.updEmp(updById);
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

	/* 搜索 */
	public String searchByKey() {
		List<Employee> listEmployee = new ArrayList<Employee>(); // 存放结果变量
		String s_id = request.getParameter("employeeId"); // 获取搜索的编号
		String s_name = request.getParameter("name"); // 获取搜索的姓名
		// 处理中文乱码
		if (!CommonUtils.isEmpty(s_id) && s_id.getBytes().length != s_id.length()) {
			try {
				s_id = new String(s_id.getBytes("iso-8859-1"), ConstantsCode.ENCODE);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (!CommonUtils.isEmpty(s_name) && s_name.getBytes().length != s_name.length()) {
			try {
				s_name = new String(s_name.getBytes("iso-8859-1"), ConstantsCode.ENCODE);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 判断搜索条件是否都为空，都为空则返回全部数据
		if (CommonUtils.isEmpty(s_id) && CommonUtils.isEmpty(s_name)) {
			listEmployee = empService.findAll();
		} else if (CommonUtils.isEmpty(s_id) && !CommonUtils.isEmpty(s_name)) {
			// CODE1值是1，代表查询的是name字段，目的是为了方便SQL语句的重用
			listEmployee = empService.searchByKey(s_name, ConstantsCode.CODE1);
		} else if (!CommonUtils.isEmpty(s_id) && CommonUtils.isEmpty(s_name)) {
			// CODE0值是0，代表查询的是id字段，目的是为了方便SQL语句的重用
			listEmployee = empService.searchByKey(s_id, ConstantsCode.CODE0);
		} else {
			// 此处是进行两个字段进行匹配
			listEmployee = empService.searchByKey(s_id, s_name);
		}
		// 返回信息给请求端
		writeJsonToResponse(listEmployee, response);
		return SUCCESS;
	}
}
