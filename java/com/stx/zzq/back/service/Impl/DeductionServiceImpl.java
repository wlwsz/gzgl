package com.stx.zzq.back.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stx.zzq.back.dao.DeductionDao;
import com.stx.zzq.back.service.AttendanceService;
import com.stx.zzq.back.service.DeductionService;
import com.stx.zzq.entities.Deduction;

@Service
public class DeductionServiceImpl implements DeductionService {

	@Autowired
	private DeductionDao deductionDao;
	@Autowired
	private AttendanceService attendService;

	// 增加
	@Override
	public void add(Deduction addDeduction) {
		// TODO Auto-generated method stub
		deductionDao.add(addDeduction);
	}

	// 查询全部
	@Override
	public List<Deduction> findAll() {
		// TODO Auto-generated method stub
		List<Deduction> all = new ArrayList<Deduction>();
		all = deductionDao.findAll();

		return all;
	}

	// 修改
	@Override
	public void updById(Deduction updById) {
		deductionDao.updById(updById);
	}

	// 通过id删除
	@Override
	public int delById(Integer id) {
		// TODO Auto-generated method stub
		int i = 0;
		i = deductionDao.delById(id);
		
		return i;
	}

	// 通过positionId查找
	@Override
	public Deduction findByPosId(String posId) {
		// TODO Auto-generated method stub
		Deduction dedu = new Deduction();
		dedu = deductionDao.findByPosId(posId);
		return dedu;
	}

	// 通过id查询
	@Override
	public Deduction findById(int id) {
		Deduction findById = new Deduction();
		findById = deductionDao.findById(id);
		return findById;
	}

	// 搜索
	@Override
	public List<Deduction> searchByName(String search_name) {
		List<Deduction> listDeduction = new ArrayList<Deduction>();
		listDeduction = deductionDao.searchByName(search_name);
		
		return listDeduction;
	}

}
