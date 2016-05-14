package com.stx.zzq.back.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.stx.zzq.back.service.DeductionService;
import com.stx.zzq.back.service.PositionService;
import com.stx.zzq.back.service.WageCountWayService;
import com.stx.zzq.base.BaseAction;
import com.stx.zzq.common.utils.CommonUtils;
import com.stx.zzq.common.utils.ConstantsCode;
import com.stx.zzq.entities.Deduction;
import com.stx.zzq.entities.Position;
import com.stx.zzq.entities.WageCountWay;

@Scope("prototype")
@Controller("action.WageCountWayAction")
public class WageCountWayAction extends BaseAction {

	@Autowired
	private WageCountWayService wageCountWayService;
	@Autowired
	private PositionService positionService;
	@Autowired
	private DeductionService deductionService;

	// 添加
	public String add() {
		String result="";
		String positionId = request.getParameter("positionId");
		Position position = positionService.findById(positionId);
		if(CommonUtils.isEmpty(position)) {
			result = "{\"success\":\"false\",\"Msg\":\"请选择职位\"}";
			responseWrite(result);
			return "add";
		}
		WageCountWay wageCountWay = new WageCountWay();
		wageCountWay.setPositionId(position.getPositionId());
		wageCountWay.setPositionName(position.getPositionName());
		wageCountWay.setBasicWage(request.getParameter("basicWage"));
		wageCountWay.setSecureReduce(request.getParameter("secureReduce"));
		wageCountWay.setOhMoneny(request.getParameter("ohMoneny"));
		wageCountWay.setCdMoneny(request.getParameter("cdMoneny"));
		wageCountWay.setZtMoneny(request.getParameter("ztMoneny"));
		wageCountWay.setKgMoneny(request.getParameter("kgMoneny"));
		wageCountWay.setPercent(request.getParameter("percent"));

		wageCountWayService.add(wageCountWay);
		// TODO 判断是否修改成功在进行修改下面操作
		position.setBasicWage(wageCountWay.getBasicWage());
		position.setSecureReduce(wageCountWay.getSecureReduce());
		positionService.updById(position);
		// 修改扣税中的五险一金
		updateDeduction(position);
		
		return "add";
	}
	/*去修改扣税表中的数据*/
	private void updateDeduction(Position position) {
		List<Deduction> allDeduc = new ArrayList<Deduction>();
		if(CommonUtils.isEmpty(allDeduc)) {
			return ;
		}
		// 不为空则进行修改
		for(Deduction deduction : allDeduc) {
			deduction.setSecureReduce(position.getSecureReduce());
			deduction.setTotalReduce("0");
			deductionService.updById(deduction);
		}
	}

	// 查询所有
	public String findAll() {
		List<WageCountWay> allWageCountWay = new ArrayList<WageCountWay>();
		allWageCountWay = wageCountWayService.findAll();
		writeJsonToResponse(allWageCountWay, response);
		return "findAll";
	}

	// 根据id查询
	public String findById() {
		String id = request.getParameter("id");
		WageCountWay wageCountWay = new WageCountWay();
		wageCountWay = wageCountWayService.findById(id);
		
		writeJsonToResponse(wageCountWay, response);
		return "findById";
	}
	
	// 搜索
	public String searchByKey() {
		String search_name = request.getParameter("positionName");
		if(search_name.getBytes().length != search_name.length()) {
			try {
				search_name = new String(search_name.getBytes("iso-8859-1"), ConstantsCode.ENCODE);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		// 返回的结果
		List<WageCountWay> allWageCountWay = new ArrayList<WageCountWay>();
		// 空则查询全部
		if(CommonUtils.isEmpty(search_name)) {
			allWageCountWay = wageCountWayService.findAll();
		} else {
			allWageCountWay = wageCountWayService.searchByName(search_name);
		}
		writeJsonToResponse(allWageCountWay, response);
		return "searchByName";
	}

	// 修改
	public String updById() {
		String id = request.getParameter("id");
		WageCountWay wageCountWay = new WageCountWay();
		wageCountWay = wageCountWayService.findById(id);
		wageCountWay.setBasicWage(request.getParameter("basicWage"));
		wageCountWay.setSecureReduce(request.getParameter("secureReduce"));
		wageCountWay.setPercent(request.getParameter("percent"));
		wageCountWay.setOhMoneny(request.getParameter("ohMoneny"));
		wageCountWay.setCdMoneny(request.getParameter("cdMoneny"));
		wageCountWay.setZtMoneny(request.getParameter("ztMoneny"));
		wageCountWay.setKgMoneny(request.getParameter("kgMoneny"));
		wageCountWayService.updById(wageCountWay);
		
		// TODO 判断是否修改成功在进行修改下面操作
		Position position = new Position();
		position = positionService.findById(wageCountWay.getPositionId());
		position.setBasicWage(wageCountWay.getBasicWage());
		position.setSecureReduce(wageCountWay.getSecureReduce());
		positionService.updById(position);
		
		return "updById";
	}

	/**
	 * 用于异步请求
	 * 
	 * @return
	 */
	public String findByPosId() {
		String result = "";
		String positionId = request.getParameter("positionId");
		if (CommonUtils.isEmpty(positionId)) {
			result = "{\"success\":\"false\",\"Msg\":\"请选择职位\"}";
			responseWrite(result);
			return null;
		}
		WageCountWay wageCountWay = wageCountWayService.findByPosId(positionId);
		if (wageCountWay == null) {
			result = "{\"success\":\"true\",\"Msg\":\"\"}";
			responseWrite(result);
			return null;
		}
		result = "{\"success\":\"false\",\"Msg\":\"对象已存在\"}";
		responseWrite(result);
		return null;
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

}
