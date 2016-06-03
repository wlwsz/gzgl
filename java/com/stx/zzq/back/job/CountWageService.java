package com.stx.zzq.back.job;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.stx.zzq.back.service.AttendanceService;
import com.stx.zzq.back.service.DeductionService;
import com.stx.zzq.back.service.SalaryService;
import com.stx.zzq.back.service.SellService;
import com.stx.zzq.back.service.WageCountWayService;
import com.stx.zzq.common.utils.CommonUtils;
import com.stx.zzq.entities.Attendance;
import com.stx.zzq.entities.Deduction;
import com.stx.zzq.entities.Salary;
import com.stx.zzq.entities.Sell;
import com.stx.zzq.entities.WageCountWay;

/**
 * 定时任务-----计算工资情况
 * 
 * @author zzq_eason
 *
 */
@Service("CountWageService")
public class CountWageService {

	@Autowired
	private SalaryService salService;
	@Autowired
	private WageCountWayService wageCountWayService;
	@Autowired
	private DeductionService deductionService;
	@Autowired
	private AttendanceService attendanceService;
	@Autowired
	private SellService sellService;

	/**
	 * 计算工资
	 */
	public void createWageList() {
		// 存放结果集合
		List<Salary> listSalary = new ArrayList<Salary>();
		// 通过员工工资服务查询所有的工资
		listSalary = salService.findAll();
		// 判断员工工资结果集是否为空
		if (CommonUtils.isEmpty(listSalary)) {
			// 为空则给出相应的提示
			String result = "{\"success\":\"false\",\"Msg\":\"工资列表为空，请查看是否有员工入职！！\"}";

			return;
		}
		countWage(listSalary);
	}

	/* 工资计算方式, 运行速度慢, 要进行改进 */
	private synchronized void countWage(List<Salary> listSalary) {
		String result = "";
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());

		// 计算工资列表
		for (Salary salary : listSalary) {
			// ==================单个计算全部的工资===========================
			if (CommonUtils.isEmpty(salary.getRealWage())) {
				// 工资计算方式
				WageCountWay wageCountWay = new WageCountWay();
				// 查询相应的计算方式
				wageCountWay = wageCountWayService.findByPosId(salary.getPositionId());
				if (CommonUtils.isEmpty(wageCountWay)) {
					// 不存在则给出提示
					System.out.println("=======工资待定中=======");
					continue ;
				}
				// 存在则先设置基本工资
				salary.setBasicWage(wageCountWay.getBasicWage());
				// 扣税
				Deduction deduction = new Deduction();
				// 查询职位扣税
				deduction = deductionService.findByPosId(salary.getPositionId());
				if (CommonUtils.isEmpty(deduction)) {
					// 不存在则给出提示
					System.out.println("=======扣税待定状态！！=======");
					continue ;
				}
				// 总扣除税
				salary.setTotalReduce(deduction.getTotalReduce());
				// 考勤
				Attendance attendance = new Attendance();
				attendance = attendanceService.findByEmpId(salary.getEmployeeId());
				// =======================================考勤为空，则查看销售计算提成=============================================
				if (CommonUtils.isEmpty(attendance)) {
					System.out.println("考勤为空！！");
					Sell sell = new Sell();
					// 销售
					sell = sellService.findByEmpId(salary.getEmployeeId());
					// =======================================销售为空=============================================
					if (CommonUtils.isEmpty(sell)) {
						System.out.println("销售不存在!!!");
						// 总工资 = 基本工资 + 交通补贴 总扣除 = 五险一金 + 其他扣除（单独）
						salary.setYear(String.valueOf(calendar.get(Calendar.YEAR)));
						salary.setMonth(String.valueOf(calendar.get(Calendar.MONTH)));
						salary.setSellmoneyGet("0");
						salary.setTotalWage(String.valueOf(Float.parseFloat(salary.getBasicWage())
								+ Float.parseFloat(deduction.getTrafficWage())));
						salary.setRealWage(String.valueOf(
								Float.parseFloat(salary.getTotalWage()) - Float.parseFloat(salary.getTotalReduce())
										- Float.parseFloat(deduction.getTrafficWage())));

					} else {
						// =======================================销售不为空=============================================
						salary.setYear(sell.getSellYear());
						salary.setMonth(sell.getSellMonth());
						// =======================总工资=“销售提成 + 基本工资 +
						// 交通补助”=======================
						salary.setSellmoneyGet(String.valueOf(Float.parseFloat(sell.getSellMoney())
								* (Float.parseFloat(wageCountWay.getPercent()) / 100.0)));
						salary.setTotalWage(String.valueOf(
								Float.parseFloat(salary.getBasicWage()) + Float.parseFloat(deduction.getTrafficWage())
										+ Float.parseFloat(salary.getSellmoneyGet())));
						// =======================实际工资=“总工资 - 总扣税 -
						// 交通补助”=======================
						salary.setRealWage(String.valueOf(
								Float.parseFloat(salary.getTotalWage()) - Float.parseFloat(salary.getTotalReduce())
										- Float.parseFloat(deduction.getTrafficWage())));
					}

					salService.updById(salary);
				}
				// =======================================考勤不为空=============================================
				else {
					salary.setYear(attendance.getYear());
					salary.setMonth(attendance.getMonth());
					Sell sell = new Sell();
					// 销售
					sell = sellService.findByEmpId(salary.getEmployeeId());
					// =======================================销售为空=============================================
					if (CommonUtils.isEmpty(sell)) {
						System.out.println("销售不存在！！");
						// 总工资 = 基本工资 + 交通补贴 总扣除 = 五险一金 + 其他扣除（单独）
						salary.setSellmoneyGet("0");
						salary.setTotalWage(String.valueOf(Float.parseFloat(salary.getBasicWage())
								+ Float.parseFloat(deduction.getTrafficWage())));
						salary.setRealWage(String.valueOf(
								Float.parseFloat(salary.getTotalWage()) - Float.parseFloat(salary.getTotalReduce())
										- Float.parseFloat(deduction.getTrafficWage())));
					}
					// =======================================销售不为空=============================================
					else {
						String other = "";
						// 销售提成
						if (attendance.getYear().equals(sell.getSellYear())
								&& attendance.getMonth().equals(sell.getSellMonth())) {
							// 加班工资
							salary.setOvertimeWage(String.valueOf(Float.parseFloat(attendance.getOverHour())
									* Float.parseFloat(wageCountWay.getOhMoneny()) / 100.0));
							// 销售提成
							salary.setSellmoneyGet(String.valueOf(Float.parseFloat(sell.getSellMoney())
									* Float.parseFloat(wageCountWay.getPercent()) / 100.0));
							// 其他扣除包括 （迟到、早退、旷工）
							other = String.valueOf(Float.parseFloat(attendance.getChidao())
									* Float.parseFloat(wageCountWay.getCdMoneny()) / 100.0
									+ Float.parseFloat(attendance.getZaotui())
											* Float.parseFloat(wageCountWay.getZtMoneny()) / 100.0
									+ Float.parseFloat(attendance.getKuangGong())
											* Float.parseFloat(wageCountWay.getKgMoneny()) / 100.0);
							// 总工资 = 加班 + 基本工资 + 销售提成 + 交通补贴 扣税中的总扣除 = 五险一金 +
							// 其他扣除（单独）- 交通补贴
							salary.setTotalWage(String.valueOf(
									Float.parseFloat(salary.getBasicWage()) + Float.parseFloat(salary.getOvertimeWage())
											+ Float.parseFloat(salary.getSellmoneyGet())
											+ Float.parseFloat(deduction.getTrafficWage())));
							// 实际工资 = 总工资 - 总扣除 - 交通补贴（多加了）
							salary.setRealWage(String.valueOf(Float.parseFloat(salary.getTotalWage())
									+ (-(Float.parseFloat(other))) + (-Float.parseFloat(deduction.getTrafficWage()))
									+ (-Float.parseFloat(deduction.getTotalReduce()))));
						} else {
							// 总工资 = 基本工资 + 交通补贴 总扣除 = 五险一金 + 其他扣除（单独）
							salary.setSellmoneyGet("0");
							salary.setTotalWage(String.valueOf(Float.parseFloat(salary.getBasicWage())
									+ Float.parseFloat(deduction.getTrafficWage())));
							salary.setRealWage(String.valueOf(
									Float.parseFloat(salary.getTotalWage()) - Float.parseFloat(salary.getTotalReduce())
											- Float.parseFloat(deduction.getTrafficWage())));
						}
					}
					salService.updById(salary);
				}
			}
		}
		return ;
	}

	public static void main(String args[]) {
		System.out.println("加载spring配置文件....");
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	}

}
