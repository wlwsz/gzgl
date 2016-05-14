package com.stx.zzq.back.dao.Impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.stx.zzq.back.dao.EmployeeDao;
import com.stx.zzq.base.BaseDao;
import com.stx.zzq.common.utils.ConstantsCode;
import com.stx.zzq.entities.Department;
import com.stx.zzq.entities.Employee;

@Repository
public class EmployeeDaoImpl extends BaseDao implements EmployeeDao {

	/* 新增员工 */
	@Override
	public void add(Employee addEmp) {
		// TODO Auto-generated method stub
		getSession().save(addEmp);
	}

	/* 查询全部员工 */
	@Override
	public List<Employee> findAll() {
		// TODO Auto-generated method stub
		List<Employee> all = new ArrayList<Employee>();
		String hql = "From Employee";
		all = getSession().createQuery(hql).list();
		return all;
	}

	/* 通过id查询员工 */
	@Override
	public Employee findById(String id) {
		// TODO Auto-generated method stub
		String hql = "From Employee where employeeId = ?";
		Query query = getSession().createQuery(hql);
		query.setString(0, id);
		Employee emp = new Employee();
		emp = (Employee) query.uniqueResult();
		return emp;
	}

	/* 修改员工信息员工 */
	@Override
	public void updEmp(Employee updEmp) {
		// TODO Auto-generated method stub
		getSession().update(updEmp);
	}

	/* 通过id删除员工 */
	@Override
	public void delEmp(Employee delEmp) {
		// TODO Auto-generated method stub
		getSession().delete(delEmp);
	}

	// 搜索功能
	@Override
	public List<Employee> searchByKey(String search_key, int code) {
		String hql = null;
		if (code == ConstantsCode.CODE0) {
			hql = "from Employee as emp where emp.employeeId like :key";
		} 
		if(code == ConstantsCode.CODE1){
			hql = "from Employee as emp where emp.name like :key";
		}
		List<Employee> listEmployee = new ArrayList<Employee>();
		Query query = getSession().createQuery(hql);
		query.setString("key", "%" + search_key + "%");
		listEmployee = (List<Employee>) query.list();
		return listEmployee;
	}
	@Override
	public List<Employee> searchByKey(String search_key1, String search_key2) {
		String hql = "from Employee as emp where emp.employeeId like :key1 and emp.name like :key2";
		List<Employee> listEmployee = new ArrayList<Employee>();
		Query query = getSession().createQuery(hql);
		query.setString("key1", "%" + search_key1 + "%");
		query.setString("key2", "%" + search_key2 + "%");
		listEmployee = (List<Employee>) query.list();
		return listEmployee;
	}

}
