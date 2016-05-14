package com.stx.zzq.back.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stx.zzq.back.dao.EmployeeDao;
import com.stx.zzq.back.service.EmployeeService;
import com.stx.zzq.entities.Department;
import com.stx.zzq.entities.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDao empDao;

	/* 新增员工 */
	@Override
	public void add(Employee addEmp) {
		// TODO Auto-generated method stub
		empDao.add(addEmp);
	}

	/* 查询全部员工 */
	@Override
	public List<Employee> findAll() {
		// TODO Auto-generated method stub
		List<Employee> all = new ArrayList<Employee>();
		all = empDao.findAll();
		return all;
	}

	/* 通过id查询员工 */
	@Override
	public Employee findById(String id) {
		// TODO Auto-generated method stub
		Employee emp = new Employee();
		emp = empDao.findById(id);
		return emp;
	}

	/* 修改员工信息员工 */
	@Override
	public void updEmp(Employee updEmp) {
		// TODO Auto-generated method stub
		empDao.updEmp(updEmp);
	}

	/* 通过id删除员工 */
	@Override
	public void delEmp(Employee delEmp) {
		// TODO Auto-generated method stub
		empDao.delEmp(delEmp);
	}

	// 搜索
	@Override
	public List<Employee> searchByKey(String search_key, int code) {
		List<Employee> listEmployee = new ArrayList<Employee>();
		listEmployee = empDao.searchByKey(search_key, code);
		return listEmployee;
	}

	@Override
	public List<Employee> searchByKey(String search_key1, String search_key2) {
		List<Employee> listEmployee = new ArrayList<Employee>();
		listEmployee = empDao.searchByKey(search_key1, search_key2);
		return listEmployee;
	}

}
