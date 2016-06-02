package com.stx.zzq.common.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSONArray;
import com.stx.zzq.base.BaseAction;
import com.stx.zzq.excel.entity.Attendance;
import com.stx.zzq.excel.entity.Employee;
import com.stx.zzq.excel.entity.Position;
import com.stx.zzq.excel.entity.Salary;
import com.stx.zzq.excel.entity.Sell;

@Scope("prototype")
@Controller("common.ExportExcelAction")
public class ExportExcelAction extends BaseAction {

	private String json;
	// 第一步，创建一个webbook，对应一个Excel文件
	private HSSFWorkbook wb = new HSSFWorkbook();
	private static FileOutputStream fout = null;
	private String excelName;
	private String code;

	public String exportExcel() {
		String result = "{\"success\":\"false\",\"errorMsg\":\"导出失败\"}";
		boolean flag = false;
		// 将json数据转化成相应格式的list集合
		// 考勤记录
		if ("0".equals(code)) {
			List<Attendance> listAtt = JSONArray.parseArray(json, Attendance.class);
			excelName = "考勤记录";
			flag = exportAttendance(listAtt);
		}
		// 职位
		if ("1".equals(code)) {
			List<Position> listPos = JSONArray.parseArray(json, Position.class);
			excelName = "职位信息";
			flag = exportPosition(listPos);
		}
		// 员工
		if ("2".equals(code)) {
			List<Employee> listEmp = JSONArray.parseArray(json, Employee.class);
			excelName = "员工信息";
			flag = exportEmployee(listEmp);
		}
		// 销售
		if ("3".equals(code)) {
			List<Sell> listSell = JSONArray.parseArray(json, Sell.class);
			excelName = "销售信息";
			flag = exportSell(listSell);
		}
		// 工资列表
		if ("4".equals(code)) {
			List<Salary> listSalary = JSONArray.parseArray(json, Salary.class);
			excelName = "工资记录";
			flag = exportSalary(listSalary);
		}
		if (flag == false) {
			result = "{\"success\":\"false\",\"errorMsg\":\"导出失败\"}";
		} else {
			result = "{\"success\":\"true\",\"msg\":\"导出成功\"}";
		}
		responseWrite(result);
		return null;
	}
	
	/**
	 * 工资列表
	 * @param listSalary
	 * @return
	 */
	public boolean exportSalary(List<Salary> listSalary) {
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet("工资列表");
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		HSSFRow row = sheet.createRow(0);
		// 第四步，创建单元格，并设置值表头 设置表头居中
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

		HSSFCell cell = row.createCell(0);
		cell.setCellValue("工资编号");
		cell.setCellStyle(style);
		cell = row.createCell(1);
		cell.setCellValue("员工编号");
		cell.setCellStyle(style);
		cell = row.createCell(2);
		cell.setCellValue("员工姓名");
		cell.setCellStyle(style);
		cell = row.createCell(3);
		cell.setCellValue("基本工资");
		cell.setCellStyle(style);
		cell = row.createCell(4);
		cell.setCellValue("加班工资");
		cell.setCellStyle(style);
		cell = row.createCell(5);
		cell.setCellValue("提成");
		cell.setCellStyle(style);
		cell = row.createCell(6);
		cell.setCellValue("总工资");
		cell.setCellStyle(style);
		cell = row.createCell(7);
		cell.setCellValue("扣税");
		cell.setCellStyle(style);
		cell = row.createCell(8);
		cell.setCellValue("实际工资");
		cell.setCellStyle(style);
		cell = row.createCell(9);
		cell.setCellValue("月份");
		cell.setCellStyle(style);
		cell = row.createCell(10);
		cell.setCellValue("编辑时间");
		cell.setCellStyle(style);
		cell = row.createCell(11);
		cell.setCellValue("描述");
		cell.setCellStyle(style);

		for (int i = 0; i < listSalary.size(); i++) {
			row = sheet.createRow(i + 1);
			Salary salary = listSalary.get(i);
			// 第四步，创建单元格，并设置值
			row.createCell(0).setCellValue(salary.getSalaryId());
			row.createCell(1).setCellValue(salary.getEmployeeId());
			row.createCell(2).setCellValue(salary.getEmployeeName());
			row.createCell(3).setCellValue(salary.getBasicWage());
			row.createCell(4).setCellValue(salary.getOvertimeWage());
			row.createCell(5).setCellValue(salary.getSellmoneyGet());
			row.createCell(6).setCellValue(salary.getTotalWage());
			row.createCell(7).setCellValue(salary.getTotalReduce());
			row.createCell(8).setCellValue(salary.getRealWage());
			row.createCell(9).setCellValue(salary.getMonth());
			row.createCell(10).setCellValue(salary.getEditTime());
			row.createCell(11).setCellValue(salary.getMemo());
		}
		return writeOutExcel();
	}

	/**
	 * 销售
	 * 
	 * @param attList
	 * @return
	 */
	private boolean exportSell(List<Sell> sellList) {
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet("职位信息");
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		HSSFRow row = sheet.createRow(0);
		// 第四步，创建单元格，并设置值表头 设置表头居中
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

		HSSFCell cell = row.createCell(0);
		cell.setCellValue("销售编号");
		cell.setCellStyle(style);
		cell = row.createCell(1);
		cell.setCellValue("销售人");
		cell.setCellStyle(style);
		cell = row.createCell(2);
		cell.setCellValue("销售金额(元)");
		cell.setCellStyle(style);
		cell = row.createCell(3);
		cell.setCellValue("销售年份");
		cell.setCellStyle(style);
		cell = row.createCell(3);
		cell.setCellValue("销售月份");
		cell.setCellStyle(style);
		cell = row.createCell(4);
		cell.setCellValue("评价");
		cell.setCellStyle(style);

		for (int i = 0; i < sellList.size(); i++) {
			row = sheet.createRow(i + 1);
			Sell sell = sellList.get(i);
			// 第四步，创建单元格，并设置值
			row.createCell(0).setCellValue(sell.getSellId());
			row.createCell(1).setCellValue(sell.getEmployeeName());
			row.createCell(2).setCellValue(sell.getSellMoney());
			row.createCell(3).setCellValue(sell.getSellYear());
			row.createCell(3).setCellValue(sell.getSellMonth());
			row.createCell(4).setCellValue(sell.getMemo());
		}
		return writeOutExcel();
	}

	/**
	 * 设置员工的excel格式
	 * 
	 * @param attList
	 * @return
	 */
	public boolean exportEmployee(List<Employee> empList) {
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet("员工信息");
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		HSSFRow row = sheet.createRow(0);
		// 第四步，创建单元格，并设置值表头 设置表头居中
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

		HSSFCell cell = row.createCell(0);
		cell.setCellValue("员工编号");
		cell.setCellStyle(style);
		cell = row.createCell(1);
		cell.setCellValue("姓名");
		cell.setCellStyle(style);
		cell = row.createCell(2);
		cell.setCellValue("性别");
		cell.setCellStyle(style);
		cell = row.createCell(3);
		cell.setCellValue("手机号");
		cell.setCellStyle(style);
		cell = row.createCell(4);
		cell.setCellValue("邮箱");
		cell.setCellStyle(style);
		cell = row.createCell(5);
		cell.setCellValue("身份证号");
		cell.setCellStyle(style);
		cell = row.createCell(6);
		cell.setCellValue("生日");
		cell.setCellStyle(style);
		cell = row.createCell(7);
		cell.setCellValue("职位名称");
		cell.setCellStyle(style);
		cell = row.createCell(8);
		cell.setCellValue("学历");
		cell.setCellStyle(style);
		cell = row.createCell(9);
		cell.setCellValue("编辑时间");
		cell.setCellStyle(style);
		cell = row.createCell(10);
		cell.setCellValue("描述");
		cell.setCellStyle(style);

		for (int i = 0; i < empList.size(); i++) {
			row = sheet.createRow(i + 1);
			Employee employee = empList.get(i);
			// 第四步，创建单元格，并设置值
			row.createCell(0).setCellValue(employee.getEmployeeId());
			row.createCell(1).setCellValue(employee.getName());
			row.createCell(2).setCellValue(employee.getSex());
			row.createCell(3).setCellValue(employee.getTelephone());
			row.createCell(4).setCellValue(employee.getEmail());
			row.createCell(5).setCellValue(employee.getCardNumber());
			row.createCell(6).setCellValue(employee.getBirthday());
			row.createCell(7).setCellValue(employee.getPositionName());
			row.createCell(8).setCellValue(employee.getSchoolRecord());
			row.createCell(9).setCellValue(employee.getEditTime());
			row.createCell(10).setCellValue(employee.getMemo());
		}
		return writeOutExcel();
	}

	/**
	 * 设置职位的excel格式
	 * 
	 * @param attList
	 * @return
	 */
	private boolean exportPosition(List<Position> posList) {
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet("职位信息");
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		HSSFRow row = sheet.createRow(0);
		// 第四步，创建单元格，并设置值表头 设置表头居中
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

		HSSFCell cell = row.createCell(0);
		cell.setCellValue("职位编号");
		cell.setCellStyle(style);
		cell = row.createCell(1);
		cell.setCellValue("职位名称");
		cell.setCellStyle(style);
		cell = row.createCell(2);
		cell.setCellValue("基本工资");
		cell.setCellStyle(style);
		cell = row.createCell(3);
		cell.setCellValue("所属部门");
		cell.setCellStyle(style);
		cell = row.createCell(4);
		cell.setCellValue("描述");
		cell.setCellStyle(style);

		for (int i = 0; i < posList.size(); i++) {
			row = sheet.createRow(i + 1);
			Position posit = posList.get(i);
			// 第四步，创建单元格，并设置值
			row.createCell(0).setCellValue(posit.getPositionId());
			row.createCell(1).setCellValue(posit.getPositionName());
			row.createCell(2).setCellValue(posit.getBasicWage());
			row.createCell(3).setCellValue(posit.getDepartmentName());
			row.createCell(4).setCellValue(posit.getMemo());
		}
		return writeOutExcel();
	}

	/**
	 * 设置考勤记录的excel格式
	 * 
	 * @param attList
	 * @return
	 */
	private boolean exportAttendance(List<Attendance> attList) {
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet("考勤记录");
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		HSSFRow row = sheet.createRow(0);
		// 第四步，创建单元格，并设置值表头 设置表头居中
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
		// 设置好excel表的列字段名
		HSSFCell cell = row.createCell(0);
		cell.setCellValue("员工编号");
		cell.setCellStyle(style);
		cell = row.createCell(1);
		cell.setCellValue("姓名");
		cell.setCellStyle(style);
		cell = row.createCell(2);
		cell.setCellValue("考勤年份");
		cell.setCellStyle(style);
		cell = row.createCell(3);
		cell.setCellValue("考勤月份");
		cell.setCellStyle(style);
		cell = row.createCell(4);
		cell.setCellValue("加班");
		cell.setCellStyle(style);
		cell = row.createCell(5);
		cell.setCellValue("迟到");
		cell.setCellStyle(style);
		cell = row.createCell(6);
		cell.setCellValue("早退");
		cell.setCellStyle(style);
		cell = row.createCell(7);
		cell.setCellValue("旷工");
		cell.setCellStyle(style);
		cell = row.createCell(8);
		cell.setCellValue("描述");
		cell.setCellStyle(style);

		for (int i = 0; i < attList.size(); i++) {
			row = sheet.createRow(i + 1);
			Attendance attend = attList.get(i);
			// 第四步，创建单元格，并设置值
			row.createCell(0).setCellValue(attend.getEmployeeId());
			row.createCell(1).setCellValue(attend.getEmployeeName());
			row.createCell(2).setCellValue(attend.getYear());
			row.createCell(3).setCellValue(attend.getMonth());
			row.createCell(4).setCellValue(attend.getOverHour());
			row.createCell(5).setCellValue(attend.getChidao());
			row.createCell(6).setCellValue(attend.getZaotui());
			row.createCell(7).setCellValue(attend.getKuangGong());
			row.createCell(8).setCellValue(attend.getMemo());
		}
		return writeOutExcel();
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

	/**
	 * 真正的写出文件
	 * 
	 * @return
	 */
	private boolean writeOutExcel() {
		// 第六步，将文件存到指定位置
		try {
			File excelFile = new File("F:/工资管理系统excel表/");
			if (!excelFile.exists()) {
				excelFile.mkdirs();
			}
			fout = new FileOutputStream("F:/工资管理系统excel表/" + excelName + ".xls");
			wb.write(fout);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if (null != fout) {
				try {
					fout.close();
				} catch (IOException e) {
					e.printStackTrace();
					return false;
				}
			}
		}
		return true;
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
