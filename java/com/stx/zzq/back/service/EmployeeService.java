package com.stx.zzq.back.service;

import java.util.List;

import com.stx.zzq.entities.Employee;

public interface EmployeeService {

	/* 新增员工 */
	void add(Employee addEmp);

	/* 查询全部员工 */
	List<Employee> findAll();

	/* 通过id查询员工 */
	Employee findById(String id);

	Employee findByName(String username);

	/* 修改员工信息员工 */
	void updEmp(Employee updEmp);

	/* 通过id删除员工 */
	void delEmp(Employee delEmp);

	/* 搜索功能 */
	List<Employee> searchByKey(String search_key, int code);

	List<Employee> searchByKey(String search_key1, String search_key2);

}
