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
import com.stx.zzq.back.service.PositionService;
import com.stx.zzq.back.service.SalaryService;
import com.stx.zzq.back.service.SellService;
import com.stx.zzq.back.service.WageCountWayService;
import com.stx.zzq.base.BaseAction;
import com.stx.zzq.common.utils.CommonUtils;
import com.stx.zzq.common.utils.ConstantsCode;
import com.stx.zzq.entities.Attendance;
import com.stx.zzq.entities.Deduction;
import com.stx.zzq.entities.Position;
import com.stx.zzq.entities.Salary;
import com.stx.zzq.entities.Sell;
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
	@Autowired
	private SalaryService salService;
	// 考勤
	@Autowired
	private AttendanceService attendanceService;
	// 销售信息
	@Autowired
	private SellService sellService;

	// 添加
	public String add() {
		String result = "";
		// 获取工资计算id
		String positionId = request.getParameter("positionId");
		// 查询特定的职位
		Position position = positionService.findById(positionId);
		// 判断职位是否存在
		if (CommonUtils.isEmpty(position)) {
			// 不存在则给出提示信息
			result = "{\"success\":\"false\",\"Msg\":\"请选择职位\"}";
			responseWrite(result);
			return "add";
		}
		// 存在则设置计算方式的具体内容
		WageCountWay wageCountWay = new WageCountWay();
		wageCountWay.setPositionId(position.getPositionId());
		wageCountWay.setPositionName(position.getPositionName());
		// TODO 将要做的是如果上面设置或重新调整了基本工资，则需要改变职位的基本工资
		// 以及员工工资记录中的基本工资，但是首先得先判断是否有实际工资，如果有则不需要改变，没有则改变
		//（有表示记录已经工资已经发放完成，没有则是未发的）
		wageCountWay.setBasicWage(request.getParameter("basicWage"));
		wageCountWay.setSecureReduce(request.getParameter("secureReduce"));
		wageCountWay.setOhMoneny(request.getParameter("ohMoneny"));
		wageCountWay.setCdMoneny(request.getParameter("cdMoneny"));
		wageCountWay.setZtMoneny(request.getParameter("ztMoneny"));
		wageCountWay.setKgMoneny(request.getParameter("kgMoneny"));
		wageCountWay.setPercent(request.getParameter("percent"));
		// 添加工资计算方式到数据库
		wageCountWayService.add(wageCountWay);
		// TODO 判断是否修改成功在进行修改下面操作
		/* 更新职位的基本工资 */
		position.setBasicWage(wageCountWay.getBasicWage());
		position.setSecureReduce(wageCountWay.getSecureReduce());
		positionService.updById(position);

		// 修改扣税中的五险一金
		updateDeduction(position);
		
		/*createWageList();*/

		return "add";
	}

	/**
	 *  计算工资
	 */
	private void createWageList() {
		// 存放结果集合
		List<Salary> listSalary = new ArrayList<Salary>();
		// 通过员工工资服务查询所有的工资
		listSalary = salService.findAll();
		// 判断员工工资结果集是否为空
		if (CommonUtils.isEmpty(listSalary)) {
			// 为空则给出相应的提示
			String result = "{\"success\":\"false\",\"Msg\":\"工资列表为空，请查看是否有员工入职！！\"}";
			responseWrite(result);
			return;
		}
		// 计算工资列表
		for (Salary salary : listSalary) {
			if (CommonUtils.isEmpty(salary.getPositionId())) {
				String result = "{\"success\":\"false\",\"Msg\":\"员工未入职！！\"}";
				salService.delById(salary.getSalaryId());
				responseWrite(result);
				return;
			}
			if (CommonUtils.isEmpty(salary.getRealWage())) {
				countWage(salary);
			}
		}

		return;
	}
	/* 工资计算方式, 运行速度慢, 要进行改进 */
	private void countWage(Salary salary) {
		String result = "";
		// 工资计算方式
		WageCountWay wageCountWay = new WageCountWay();
		// 查询相应的计算方式
		wageCountWay = wageCountWayService.findByPosId(salary.getPositionId());
		if (CommonUtils.isEmpty(wageCountWay)) {
			// 不存在则给出提示
			result = "{\"success\":\"false\",\"Msg\":\"工资待定状态！！\"}";
			responseWrite(result);
			return ;
		}
		// 存在则先设置基本工资
		salary.setBasicWage(wageCountWay.getBasicWage());
		// 扣税
		Deduction deduction = new Deduction();
		// 查询职位扣税
		deduction = deductionService.findByPosId(salary.getPositionId());
		if (CommonUtils.isEmpty(deduction)) {
			// 不存在则给出提示
			result = "{\"success\":\"false\",\"Msg\":\"扣税待定状态！！\"}";
			responseWrite(result);
			return ;
		}
		// 总扣除税
		salary.setTotalReduce(deduction.getTotalReduce());
		// 考勤
		Attendance attendance = new Attendance();
		attendance = attendanceService.findByEmpId(salary.getEmployeeId());
		if (CommonUtils.isEmpty(attendance)) {
			result = "{\"success\":\"false\",\"Msg\":\"考勤为空！！\"}";
			responseWrite(result);
			return ;
		}
		salary.setYear(attendance.getYear());
		salary.setMonth(attendance.getMonth());
		Sell sell = new Sell();
		// 销售
		sell = sellService.findByEmpId(salary.getEmployeeId());
		if (CommonUtils.isEmpty(sell)) {
			result = "{\"success\":\"false\",\"Msg\":\"销售不存在！！\"}";
			// 总工资 = 基本工资 + 交通补贴 总扣除 = 五险一金 + 其他扣除（单独）
			salary.setSellmoneyGet("0");
			salary.setTotalWage(String
					.valueOf(Float.parseFloat(salary.getBasicWage()) + Float.parseFloat(deduction.getTrafficWage())));
			salary.setRealWage(String.valueOf(Float.parseFloat(salary.getTotalWage())
					- Float.parseFloat(salary.getTotalReduce()) - Float.parseFloat(deduction.getTrafficWage())));
			salService.updById(salary);

			responseWrite(result);
			return ;
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
					.valueOf(Float.parseFloat(attendance.getChidao()) * Float.parseFloat(wageCountWay.getCdMoneny())
							+ Float.parseFloat(attendance.getZaotui()) * Float.parseFloat(wageCountWay.getZtMoneny())
							+ Float.parseFloat(attendance.getKuangGong())
									* Float.parseFloat(wageCountWay.getKgMoneny()));
			// 总工资 = 加班 + 基本工资 + 销售提成 + 交通补贴 扣税中的总扣除 = 五险一金 + 其他扣除（单独）- 交通补贴
			salary.setTotalWage(String.valueOf(Float.parseFloat(salary.getBasicWage())
					+ Float.parseFloat(salary.getOvertimeWage()) + Float.parseFloat(deduction.getTrafficWage())));
			// 实际工资 = 总工资 - 总扣除 - 交通补贴（多加了）
			salary.setRealWage(String.valueOf(Float.parseFloat(salary.getTotalWage()) + (-(Float.parseFloat(other)))
					+ Float.parseFloat(deduction.getTotalReduce())));
			result = "{\"success\":\"true\",\"Msg\":\"\"}";
		} else {
			// 总工资 = 基本工资 + 交通补贴 总扣除 = 五险一金 + 其他扣除（单独）
			salary.setSellmoneyGet("0");
			salary.setTotalWage(String
					.valueOf(Float.parseFloat(salary.getBasicWage()) + Float.parseFloat(deduction.getTrafficWage())));
			salary.setRealWage(String.valueOf(Float.parseFloat(salary.getTotalWage())
					- Float.parseFloat(salary.getTotalReduce()) - Float.parseFloat(deduction.getTrafficWage())));
			result = "{\"success\":\"true\",\"Msg\":\"\"}";
		}
		salService.updById(salary);
		
		responseWrite(result);
		return ;
	}

	/* 去修改扣税表中的数据 */
	private void updateDeduction(Position position) {
		List<Deduction> allDeduc = new ArrayList<Deduction>();
		if (CommonUtils.isEmpty(allDeduc)) {
			return;
		}
		// 不为空则进行修改
		for (Deduction deduction : allDeduc) {
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
		if (search_name.getBytes().length != search_name.length()) {
			try {
				search_name = new String(search_name.getBytes("iso-8859-1"), ConstantsCode.ENCODE);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 返回的结果
		List<WageCountWay> allWageCountWay = new ArrayList<WageCountWay>();
		// 空则查询全部
		if (CommonUtils.isEmpty(search_name)) {
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
