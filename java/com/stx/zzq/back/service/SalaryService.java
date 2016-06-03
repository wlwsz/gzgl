package com.stx.zzq.back.service;

import java.util.List;

import com.stx.zzq.entities.Employee;
import com.stx.zzq.entities.Salary;

public interface SalaryService {

	/* 添加工资 */
	void add(Salary add);

	/* 查询全部的工资 */
	List<Salary> findAll();
	
	/* 查询某个人工资 */
	List<Salary> findByEmpId(String empId);

	/* 查询某个人工资 */
	Salary findByEmpId(Employee emp);

	/* 删除工资记录 */
	void delSal(Salary delSal);

	/* 修改工资 */
	void updSal(Salary updSal);

	/* 通过id 修改 */
	void updById(Salary updById);

	// 通过id删除
	int delById(int id);

}
