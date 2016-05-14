package com.stx.zzq.back.dao.Impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.stx.zzq.back.dao.SalaryDao;
import com.stx.zzq.base.BaseDao;
import com.stx.zzq.entities.Employee;
import com.stx.zzq.entities.Salary;

@Repository
public class SalaryDaoImpl extends BaseDao implements SalaryDao {

	/* 添加工资 */
	@Override
	public void add(Salary add) {
		// TODO Auto-generated method stub
		getSession().save(add);
	}

	/* 查询全部的工资 */
	@Override
	public List<Salary> findAll() {
		// TODO Auto-generated method stub
		List<Salary> all = new ArrayList<Salary>();
		String hql = "From Salary";
		all = getSession().createQuery(hql).list();
		return all;
	}

	/* 查询某个人工资 */
	@Override
	public Salary findByEmpId(Employee emp) {
		// TODO Auto-generated method stub
		Salary sal = new Salary();
		String hql = "From Salary where employeeId = ?";
		Query query = getSession().createQuery(hql);
		query.setString(0, emp.getEmployeeId());
		sal = (Salary) query.uniqueResult();
		return sal;
	}

	/* 删除工资记录 */
	@Override
	public void delSal(Salary delSal) {
		// TODO Auto-generated method stub
		getSession().delete(delSal);
	}

	/* 修改工资 */
	@Override
	public void updSal(Salary updSal) {
		// TODO Auto-generated method stub
		getSession().update(updSal);
	}

	/* 通过id 修改 */
	@Override
	public void updById(Salary updById) {
		// TODO Auto-generated method stub
		getSession().update(updById);
	}

	// 通过id删除
	@Override
	public int delById(int id) {
		// TODO Auto-generated method stub
		int i = 0;
		String hql = "delete Salary where salaryId = ?";
		Query query = getSession().createQuery(hql);
		query.setInteger(0, id);
		i = query.executeUpdate();
		return i;
	}

}
