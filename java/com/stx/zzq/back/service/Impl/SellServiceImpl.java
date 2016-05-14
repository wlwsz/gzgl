package com.stx.zzq.back.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stx.zzq.back.dao.SellDao;
import com.stx.zzq.back.service.SellService;
import com.stx.zzq.entities.Attendance;
import com.stx.zzq.entities.Sell;

@Service
public class SellServiceImpl implements SellService {
	
	@Autowired
	private SellDao sellDao;

	/* 添加销售 */
	@Override
	public void add(Sell add) {
		// TODO Auto-generated method stub
		sellDao.add(add);
		
	}

	/* 查询全部销售 */
	@Override
	public List<Sell> findAll() {
		// TODO Auto-generated method stub
		List<Sell> all = new ArrayList<Sell>();
		all = sellDao.findAll();
		return all;
	}

	/* 删除销售 */
	@Override
	public void delSell(Sell delSell) {
		// TODO Auto-generated method stub
		sellDao.delSell(delSell);
	}

	/* 修改销售 */
	@Override
	public void updSell(Sell updSell) {
		// TODO Auto-generated method stub
		sellDao.updSell(updSell);
	}

	/* 通过id来删除数据 */
	@Override
	public int delById(String id) {
		// TODO Auto-generated method stub
		int i = 0;
		i = sellDao.delById(id);
		return i;
	}

	//搜索
	@Override
	public List<Sell> searchByKey(String key, int code) {
		List<Sell> listSell = new ArrayList<Sell>();
		listSell = sellDao.searchByKey(key, code);
		return listSell;
	}

	@Override
	public List<Sell> searchByKey(String name, String year, String month) {
		List<Sell> listSell = new ArrayList<Sell>();
		listSell = sellDao.searchByKey(name, year, month);
		return listSell;
	}

	@Override
	public List<Sell> searchByKey(String key1, int code1, String key2, int code2) {
		List<Sell> listSell = new ArrayList<Sell>();
		listSell = sellDao.searchByKey(key1, code1, key2, code2);
		return listSell;
	}

	@Override
	public Sell findByEmpId(String empId) {
		// TODO Auto-generated method stub
		Sell sell = new Sell();
		sell = sellDao.findByEmpId(empId);
		return sell;
	}
}
