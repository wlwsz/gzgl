package com.stx.zzq.back.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.stx.zzq.back.service.DepartmentService;
import com.stx.zzq.back.service.PositionService;
import com.stx.zzq.base.BaseAction;
import com.stx.zzq.common.utils.CommonUtils;
import com.stx.zzq.common.utils.ConstantsCode;
import com.stx.zzq.entities.Department;
import com.stx.zzq.entities.Position;

@Scope("prototype")
@Controller("back.PositionAction")
public class PositionAction extends BaseAction {

	@Autowired
	private PositionService positionService;
	@Autowired
	private DepartmentService departService;
	
	/* 此处是设置页数和显示的条数 */
	private Integer rows;
	private Integer page;

	/* 新添加职位 */
	public String add() {
		Position position = new Position();
		String id = request.getParameter("positionId");
		position = positionService.findById(id);
		if(!CommonUtils.isEmpty(position)) {
			position.put("fail", "职位存在");
			writeJsonToResponse(position, response);
			return "add";
		}
		Department dep = new Department();
		Position addPosition = new Position();
		dep = departService.findById(request.getParameter("departmentId"));
		if(CommonUtils.isEmpty(dep)) {
			addPosition.put("fail", "请选择部门");
			writeJsonToResponse(addPosition, response);
			return "add";
		}
		addPosition.setPositionId(request.getParameter("positionId"));
		addPosition.setDepartmentId(dep.getDepartmentId());
		addPosition.setDepartmentName(dep.getDepartmentName());
		addPosition.setPositionName(request.getParameter("positionName"));
		addPosition.setBasicWage("待定中");
		addPosition.setSecureReduce("待定中");
		addPosition.setMemo(request.getParameter("memo"));
		System.out.println(addPosition);
		positionService.add(addPosition);
		addPosition.put("success", "保存成功");
		writeJsonToResponse(addPosition, response);
		return "add";
	}

	/* 查询全部职位 */
	public String findAll() {
		List<Position> all = new ArrayList<Position>();
		all = positionService.findAll();
		writeJsonToResponse(all, response);
		return "findAll";
	}

	/* 根据id查询 */
	public String findById() {
		// TODO
		String id = request.getParameter("positionId");
		Position pos = new Position();
		pos = positionService.findById(id);
		writeJsonToResponse(pos, response);
		return "findById";
	}
	
	/* 搜索功能实现 */
	public String searchByKey() {
		List<Position> listPosition = new ArrayList<Position>();
		String s_id = request.getParameter("positionId");
		String s_name1 = request.getParameter("positionName");
		String s_name2 = request.getParameter("departmentName");
		// 处理中文乱码
		if (!CommonUtils.isEmpty(s_id) && s_id.getBytes().length != s_id.length()) {
			try {
				s_id = new String(s_id.getBytes("iso-8859-1"), ConstantsCode.ENCODE);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (!CommonUtils.isEmpty(s_name1) && s_name1.getBytes().length != s_name1.length()) {
			try {
				s_name1 = new String(s_name1.getBytes("iso-8859-1"), ConstantsCode.ENCODE);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (!CommonUtils.isEmpty(s_name2) && s_name2.getBytes().length != s_name2.length()) {
			try {
				s_name2 = new String(s_name2.getBytes("iso-8859-1"), ConstantsCode.ENCODE);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 查询全部
		if (CommonUtils.isEmpty(s_id) && CommonUtils.isEmpty(s_name1) && CommonUtils.isEmpty(s_name2)) {
			listPosition = positionService.findAll();
		} 
		// 查询一个字段
		else if (!CommonUtils.isEmpty(s_id) && CommonUtils.isEmpty(s_name1) && CommonUtils.isEmpty(s_name2)) {
			// s_id
			listPosition = positionService.searchByKey(s_id, ConstantsCode.CODE0);
		} else if (CommonUtils.isEmpty(s_id) && !CommonUtils.isEmpty(s_name1) && CommonUtils.isEmpty(s_name2)) {
			// s_positionName
			listPosition = positionService.searchByKey(s_name1, ConstantsCode.CODE1);
		} else if (CommonUtils.isEmpty(s_id) && CommonUtils.isEmpty(s_name1) && !CommonUtils.isEmpty(s_name2)) {
			// s_departmentName
			listPosition = positionService.searchByKey(s_name2, ConstantsCode.CODE2);
		} 
		// 查询两个字段
		else if (!CommonUtils.isEmpty(s_id) && !CommonUtils.isEmpty(s_name1) && CommonUtils.isEmpty(s_name2)) {
			// s_id @ s_positionName
			listPosition = positionService.searchByKey(s_id, ConstantsCode.CODE0, s_name1, ConstantsCode.CODE1);
		} else if (!CommonUtils.isEmpty(s_id) && CommonUtils.isEmpty(s_name1) && !CommonUtils.isEmpty(s_name2)) {
			// s_id @ s_departmentName
			listPosition = positionService.searchByKey(s_id, ConstantsCode.CODE0, s_name2, ConstantsCode.CODE2);
		} else if (CommonUtils.isEmpty(s_id) && !CommonUtils.isEmpty(s_name1) && !CommonUtils.isEmpty(s_name2)) {
			// s_positionName @ s_departmentName
			listPosition = positionService.searchByKey(s_name1, ConstantsCode.CODE1, s_name2, ConstantsCode.CODE2);
		} 
		// 查询全部条件
		else {
			listPosition = positionService.searchByKey(s_id, s_name1, s_name2);
		}

		writeJsonToResponse(listPosition, response);
		return SUCCESS;
	}

	/* 根据id修改职位 */
	public String updById() {
		String id = request.getParameter("positionId");
		Position upPos = null;
		upPos = positionService.findById(id);
		if(CommonUtils.isEmpty(upPos)) {
			upPos = new Position();
			upPos.put("fail", "不存在此职位");
			writeJsonToResponse(upPos, response);
			return "updById";
		}
		upPos.setPositionName(request.getParameter("positionName"));
		Department dep = new Department();
		dep = departService.findById(request.getParameter("departmentId"));
		upPos.setDepartmentId(dep.getDepartmentId());
		upPos.setDepartmentName(dep.getDepartmentName());
		upPos.setBasicWage("待定中");
		upPos.setSecureReduce("待定中");
		upPos.setMemo(request.getParameter("memo"));
		positionService.updById(upPos);
		upPos.put("success", "修改成功");
		writeJsonToResponse(upPos, response);
		return "updById";
	}

	/* 删除特定的对象 */
	public String delPos() {
		Position delPos = new Position();
		delPos.setPositionId(request.getParameter("positionId"));
		delPos.setPositionName(request.getParameter("positionName"));
		positionService.delPos(delPos);
		return "delPos";
	}

	/* 通过id进行删除 */
	public String delById() {
		String id = request.getParameter("positionId");
		int i = positionService.delById(id);
		Position post = new Position();
		post.put("success", "删除成功");
		writeJsonToResponse(post, response);
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
