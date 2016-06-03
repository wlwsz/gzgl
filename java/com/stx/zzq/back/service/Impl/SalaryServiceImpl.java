package com.stx.zzq.back.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stx.zzq.back.dao.DeductionDao;
import com.stx.zzq.back.dao.EmployeeDao;
import com.stx.zzq.back.dao.PositionDao;
import com.stx.zzq.back.dao.SalaryDao;
import com.stx.zzq.back.service.SalaryService;
import com.stx.zzq.common.utils.CommonUtils;
import com.stx.zzq.entities.Deduction;
import com.stx.zzq.entities.Employee;
import com.stx.zzq.entities.Position;
import com.stx.zzq.entities.Salary;

@Service
public class SalaryServiceImpl implements SalaryService {

	@Autowired
	private SalaryDao salDao;
	@Autowired
	private EmployeeDao empDao;
	@Autowired
	private PositionDao posDao;
	@Autowired
	private DeductionDao dedDao;

	/* 添加工资 */
	@Override
	public void add(Salary add) {
		// TODO Auto-generated method stub
		// countWage(add);
		salDao.add(add);
	}

	/* 查询全部的工资 */
	@Override
	public List<Salary> findAll() {
		// TODO Auto-generated method stub
		List<Salary> all = new ArrayList<Salary>();
		all = salDao.findAll();
		return all;
	}

	/* 查询某个人工资 */
	@Override
	public Salary findByEmpId(Employee emp) {
		// TODO Auto-generated method stub
		Salary sal = new Salary();
		sal = salDao.findByEmpId(emp);
		return null;
	}

	/* 删除工资记录 */
	@Override
	public void delSal(Salary delSal) {
		// TODO Auto-generated method stub
		salDao.delSal(delSal);
	}

	/* 修改工资 */
	@Override
	public void updSal(Salary updSal) {
		// TODO Auto-generated method stub
		salDao.updSal(updSal);
	}

	/* 通过id 修改 */
	@Override
	public void updById(Salary updById) {
		// TODO Auto-generated method stub
		// countWage(updById);
		salDao.updById(updById);
	}

	// 统计工资方法
	public void countWage(Salary salary) {
		// 总工资
		String totalWage = "";
		// 总扣除
		String totalReduce = "";
		// 实际工资
		String realWage = "";
		// 计算总工资
		salary.setTotalWage("");
		Employee emp = new Employee();
		emp = empDao.findById(salary.getEmployeeId());
		Position pos = new Position();
		pos = posDao.findById(emp.getPositionId());
		Deduction deduc = new Deduction();
		deduc = dedDao.findByPosId(pos.getPositionId());
		if (CommonUtils.isEmpty(deduc)) {
			System.out.println("不扣税");
		} else {
			totalReduce = deduc.getTotalReduce();
		}
		salary.setTotalReduce(totalReduce);
		realWage = String.valueOf(Float.parseFloat(totalWage) - Float.parseFloat(totalReduce));
		salary.setRealWage(realWage);
	}

	// 通过id删除
	@Override
	public int delById(int id) {
		// TODO Auto-generated method stub
		int i = 0;
		i = salDao.delById(id);
		return i;
	}
	
	@Override
	public List<Salary> findByEmpId(String empId) {
		List<Salary> salaryAll = new ArrayList<Salary>();
		salaryAll = salDao.findByEmpId(empId);
		return salaryAll;
	}

}
