package com.stx.zzq.back.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.stx.zzq.back.service.DepartmentService;
import com.stx.zzq.base.BaseAction;
import com.stx.zzq.common.utils.CommonUtils;
import com.stx.zzq.common.utils.ConstantsCode;
import com.stx.zzq.entities.Department;

@Scope("prototype")
@Controller("back.DepartmentAction")
public class DepartmentAction extends BaseAction {

	@Autowired
	private DepartmentService departService;

	/* 此处是设置页数和显示的条数 */
	private Integer rows;
	private Integer page;

	/* 添加部门 */
	public String add() {
		Department depart = new Department();
		depart.setDepartmentId(request.getParameter("departmentId"));
		depart.setDepartmentName(request.getParameter("departmentName"));
		depart.setMemo(request.getParameter("memo"));
		departService.add(depart);
		depart.put("success", "新建部门成功");
		writeJsonToResponse(depart, response);
		return "add";
	}

	/* 查询所有部门 */
	public String findAll() {
		List<Department> all = new ArrayList<Department>(); // 定义存放结果的list集合
		all = departService.findAll(); // 通过部门服务查询全部数据信息
		request.setAttribute("all", all); // 将结果放入到request对象中
		writeJsonToResponse(all, response); // 通过将结果转化为json数据返回到页面
		return "findAll";
	}

	/* 通过id查询部门 */
	public String findById() {
		String id = request.getParameter("departmentId");
		Department departId = departService.findById(id);
		request.setAttribute("departId", departId);
		return "findById";
	}

	/* 通过实体类查询部门 */
	public String findByDep() {
		Department departDep = new Department();
		departDep.setDepartmentId(request.getParameter("departmentId"));
		departDep.setDepartmentName(request.getParameter("departmentName"));
		departDep.setMemo(request.getParameter("memo"));

		departDep = departService.findByDep(departDep);
		return "findByDep";
	}

	/* 搜索功能 */
	public String searchByKey() {
		List<Department> listDepartment = new ArrayList<Department>();
		String s_id = request.getParameter("departmentId");
		String s_name = request.getParameter("departmentName");
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
		if (CommonUtils.isEmpty(s_id) && CommonUtils.isEmpty(s_name)) {
			listDepartment = departService.findAll();
		} else if (CommonUtils.isEmpty(s_id) && !CommonUtils.isEmpty(s_name)) {
			listDepartment = departService.searchByKey(s_name, ConstantsCode.CODE1);
		} else if (!CommonUtils.isEmpty(s_id) && CommonUtils.isEmpty(s_name)) {
			listDepartment = departService.searchByKey(s_id, ConstantsCode.CODE0);
		} else {
			listDepartment = departService.searchByKey(s_id, s_name);
		}

		writeJsonToResponse(listDepartment, response);
		return SUCCESS;
	}

	/* 修改部门信息 */
	public String updDep() {
		Department updDep = new Department();
		updDep.setDepartmentId(request.getParameter("departmentId"));
		updDep.setDepartmentName(request.getParameter("departmentName"));
		updDep.setMemo(request.getParameter("memo"));
		departService.updDep(updDep);
		request.setAttribute("updDep", updDep);
		return "updDep";
	}

	/* 通过Id修改部门信息 */
	public String updById() {
		Department updDepId = new Department();
		updDepId.setDepartmentId(request.getParameter("departmentId"));
		updDepId.setDepartmentName(request.getParameter("departmentName"));
		updDepId.setMemo(request.getParameter("memo"));
		
		int i = departService.updById(updDepId);
		if (i == 0) {
			updDepId.put("errorMsg", "修改失败");
		} else {
			updDepId.put("success", "修改成功");
		}
		writeJsonToResponse(updDepId, response);
		return "updById";
	}

	/* 删除部门信息 */
	public String delDep() {
		Department delDep = new Department();
		delDep.setDepartmentId(request.getParameter("departmentId"));
		delDep.setDepartmentName(request.getParameter("departmentName"));
		delDep.setMemo(request.getParameter("memo"));
		
		departService.delDep(delDep);

		return "delDep";
	}

	/* 通过id编号删除 */
	public String delById() {
		Department delIdDep = new Department();
		String id = request.getParameter("departmentId");
		int i = departService.delById(id);
		if (i == 0) {
			delIdDep.put("errorMsg", "删除失败");
		} else {
			delIdDep.put("success", "删除成功");
		}
		writeJsonToResponse(delIdDep, response);
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

}
