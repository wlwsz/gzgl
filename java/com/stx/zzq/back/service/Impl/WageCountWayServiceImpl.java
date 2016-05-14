package com.stx.zzq.back.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stx.zzq.back.dao.WageCountWayDao;
import com.stx.zzq.back.service.WageCountWayService;
import com.stx.zzq.entities.Admin;
import com.stx.zzq.entities.WageCountWay;

@Service
public class WageCountWayServiceImpl implements WageCountWayService {

	@Autowired
	private WageCountWayDao wageCountWayDao;

	// 添加对象
	@Override
	public void add(WageCountWay wageCountWay) {
		wageCountWayDao.add(wageCountWay);
	}

	// 查询全部
	@Override
	public List<WageCountWay> findAll() {
		List<WageCountWay> allWageCountWay = new ArrayList<WageCountWay>();
		allWageCountWay = wageCountWayDao.findAll();
		return allWageCountWay;
	}
	
	// id查询
	@Override
	public WageCountWay findById(String id) {
		WageCountWay wageCountWay = new WageCountWay();
		wageCountWay = wageCountWayDao.findById(id);
		
		return wageCountWay;
	}
	
	/* 搜索功能，进行模糊查询 */
	@Override
	public List<WageCountWay> searchByName(String search_name) {
		List<WageCountWay> listWageCountWay = new ArrayList<WageCountWay>();
		listWageCountWay = wageCountWayDao.searchByName(search_name);
		return listWageCountWay;
	}
	
	// 通过id修改
	@Override
	public void updById(WageCountWay wageCountWay) {
		wageCountWayDao.updById(wageCountWay);
	}

	/*
	 * 用于异步请求(non-Javadoc)
	 * 
	 * @see com.stx.zzq.back.service.WageCountWayService#findByPosId(java.lang.
	 * String)
	 */
	@Override
	public WageCountWay findByPosId(String positionId) {
		WageCountWay wageCountWay = wageCountWayDao.findByPosId(positionId);
		return wageCountWay;
	}

}
