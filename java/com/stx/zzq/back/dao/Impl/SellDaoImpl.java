package com.stx.zzq.back.dao.Impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.stx.zzq.back.dao.SellDao;
import com.stx.zzq.base.BaseDao;
import com.stx.zzq.common.utils.ConstantsCode;
import com.stx.zzq.entities.Attendance;
import com.stx.zzq.entities.Sell;

@Repository
public class SellDaoImpl extends BaseDao implements SellDao {

	/* 添加销售 */
	@Override
	public void add(Sell add) {
		// TODO Auto-generated method stub
		getSession().save(add);
	}

	/* 查询全部销售 */
	@Override
	public List<Sell> findAll() {
		// TODO Auto-generated method stub
		List<Sell> all = new ArrayList<Sell>();
		all = getSession().createQuery("From Sell").list();
		return all;
	}

	/* 删除销售 */
	@Override
	public void delSell(Sell delSell) {
		// TODO Auto-generated method stub
		getSession().delete(delSell);
	}

	/* 修改销售 */
	@Override
	public void updSell(Sell updSell) {
		// TODO Auto-generated method stub
		getSession().update(updSell);
	}

	/* 通过id来删除数据 */
	@Override
	public int delById(String id) {
		// TODO Auto-generated method stub
		String hql = "delete Sell where sellId = ?";
		Query query = getSession().createQuery(hql);
		query.setString(0, id);
		int i = 0;
		i = query.executeUpdate();
		return i;
	}

	// 搜索
	@Override
	public List<Sell> searchByKey(String key, int code) {
		List<Sell> listSell = new ArrayList<Sell>();
		String hql = "";
		if(code == ConstantsCode.CODE0) {
			hql = "from Sell as sell where sell.employeeName like :key";
		}
		if(code == ConstantsCode.CODE1) {
			hql = "from Sell as sell where sell.sellYear like :key";
		}
		if(code == ConstantsCode.CODE2) {
			hql = "from Sell as sell where sell.sellMonth like :key";
		}
		Query query = getSession().createQuery(hql);
		query.setString("key", "%" + key + "%");
		listSell = (List<Sell>) query.list();
		return listSell;
	}

	@Override
	public List<Sell> searchByKey(String name, String year, String month) {
		List<Sell> listSell = new ArrayList<Sell>();
		String hql = "from Sell as sell where sell.employeeName like :key0 and sell.sellYear like :key1 and sell.sellMonth like :key2";
		Query query = getSession().createQuery(hql);
		query.setString("key0", "%" + name + "%");
		query.setString("key1", year);
		query.setString("key2", month);
		listSell = (List<Sell>) query.list();
		return listSell;
	}

	@Override
	public List<Sell> searchByKey(String key1, int code1, String key2, int code2) {
		List<Sell> listSell = new ArrayList<Sell>();
		String hql = "";
		if(code1 == ConstantsCode.CODE0 && code2 == ConstantsCode.CODE1) {
			hql = "from Sell as sell where sell.employeeName like :key1 and sell.sellYear like :key2";
		}
		if(code1 == ConstantsCode.CODE0 && code2 == ConstantsCode.CODE2) {
			hql = "from Sell as sell where sell.employeeName like :key1 and sell.sellMonth like :key2";
		}
		if(code1 == ConstantsCode.CODE1 && code2 == ConstantsCode.CODE2) {
			hql = "from Sell as sell where sell.sellYear like :key1 and sell.sellMonth like :key2";
		}
		
		Query query = getSession().createQuery(hql);
		query.setString("key1", "%" + key1 + "%");
		query.setString("key2", "%" + key2 + "%");
		listSell = (List<Sell>) query.list();
		return listSell;
	}

	@Override
	public Sell findByEmpId(String empId) {
		// TODO Auto-generated method stub
		String hql = "from Sell sell where sell.employeeId = ?";
		Query query = getSession().createQuery(hql);
		query.setString(0, empId);
		Sell sell = (Sell) query.uniqueResult();
		return sell;
	}

}
