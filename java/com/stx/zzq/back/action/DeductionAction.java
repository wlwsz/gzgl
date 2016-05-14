package com.stx.zzq.back.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.stx.zzq.back.service.DeductionService;
import com.stx.zzq.back.service.PositionService;
import com.stx.zzq.base.BaseAction;
import com.stx.zzq.common.utils.CommonUtils;
import com.stx.zzq.common.utils.ConstantsCode;
import com.stx.zzq.entities.Admin;
import com.stx.zzq.entities.Deduction;
import com.stx.zzq.entities.Position;

@Scope("prototype")
@Controller("back.DeductionAction")
public class DeductionAction extends BaseAction {

	@Autowired
	private DeductionService deductionService;
	@Autowired
	private PositionService positionService;

	/* 此处是设置页数和显示的条数 */
	private Integer rows;
	private Integer page;

	// 增加
	public String add() {
		// TODO
		Deduction addDeduction = new Deduction();
		Position position = null;
		position = positionService.findById(request.getParameter("positionId"));
		if(CommonUtils.isEmpty(position)) {
			position = new Position();
			position.put("fail", "请选择职位");
			writeJsonToResponse(position, response);
			return "add";
		}
		addDeduction.setPositionId(position.getPositionId());
		addDeduction.setPositionName(position.getPositionName());
		addDeduction.setDepartmentId(position.getDepartmentId());
		addDeduction.setDepartmentName(position.getDepartmentName());
		addDeduction.setSecureReduce(position.getSecureReduce()); /*request.getParameter("secureReduce")*/
		addDeduction.setTaxReduce(request.getParameter("taxReduce"));
		addDeduction.setTrafficWage(request.getParameter("trafficWage"));

		addDeduction.setTotalReduce("");
		deductionService.add(addDeduction);
		addDeduction.put("success", "保存成功");

		writeJsonToResponse(addDeduction, response);
		return "add";
	}

	// 查询全部
	public String findAll() {
		// TODO
		List<Deduction> all = new ArrayList<Deduction>();
		all = deductionService.findAll();
		writeJsonToResponse(all, response);
		return "findAll";
	}

	// 通过EmployeeId查询
	public String findByPosId() {
		// TODO
		String posId = request.getParameter("positionId");
		Deduction dedByPosId = new Deduction();
		dedByPosId = deductionService.findByPosId(posId);
		if (CommonUtils.isEmpty(dedByPosId)) {
			dedByPosId.put("errorMsg", "没有找到");
		} else {
			dedByPosId.put("success", "成功");
		}
		writeJsonToResponse(dedByPosId, response);
		return "findByEmpId";
	}

	// 通过id查找
	public String findById() {
		int id = Integer.parseInt(request.getParameter("deductionId"));
		Deduction findById = new Deduction();
		findById = deductionService.findById(id);

		return "findById";
	}

	// 修改
	public String updById() {
		Deduction updById = null;
		updById = deductionService.findById(Integer.parseInt(request.getParameter("deductionId")));
		if(CommonUtils.isEmpty(updById)) {
			updById = new Deduction();
			updById.put("fail", "没有相应的记录");
			writeJsonToResponse(updById, response);
			return "updById";
		}
		updById.setDeductionId(Integer.parseInt(request.getParameter("deductionId")));
		updById.setTaxReduce(request.getParameter("taxReduce"));
		updById.setTrafficWage(request.getParameter("trafficWage"));
		updById.setTotalReduce("1");
		deductionService.updById(updById);
		updById.put("success", "修改成功");
		writeJsonToResponse(updById, response);

		return "updById";
	}

	// 通过id删除数据
	public String delById() {
		Deduction result = new Deduction();
		Integer id = Integer.parseInt(request.getParameter("deductionId"));
		int i = 0;
		i = deductionService.delById(id);
		if (i == 0) {
			result.put("errorMsg", "删除失败");
			writeJsonToResponse(result, response);
			return "delById";
		}
		result.put("success", "删除成功");
		writeJsonToResponse(result, response);

		return "delById";
	}

	// 搜索
	public String searchByKey() {
		String search_name = request.getParameter("positionName");
		if (search_name.getBytes().length != search_name.length()) {
			try {
				search_name = new String(search_name.getBytes("iso-8859-1"), ConstantsCode.ENCODE);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 返回的结果
		List<Deduction> listDeduction = new ArrayList<Deduction>();
		// 空则查询全部
		if (CommonUtils.isEmpty(search_name)) {
			listDeduction = deductionService.findAll();
		} else {
			listDeduction = deductionService.searchByName(search_name);
		}
		writeJsonToResponse(listDeduction, response);
		return "searchByKey";
	}

	// getter和setter方法
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
