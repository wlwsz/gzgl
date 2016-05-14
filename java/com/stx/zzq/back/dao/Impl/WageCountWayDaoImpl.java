package com.stx.zzq.back.dao.Impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.stx.zzq.back.dao.WageCountWayDao;
import com.stx.zzq.base.BaseDao;
import com.stx.zzq.entities.Admin;
import com.stx.zzq.entities.WageCountWay;

@Repository
public class WageCountWayDaoImpl extends BaseDao implements WageCountWayDao {

	// 添加
	@Override
	public void add(WageCountWay wageCountWay) {
		getSession().save(wageCountWay);
	}

	// 查询全部
	@Override
	public List<WageCountWay> findAll() {
		String hql = "from WageCountWay";
		List<WageCountWay> allWageCountWay = new ArrayList<WageCountWay>();
		allWageCountWay = getSession().createQuery(hql).list();

		return allWageCountWay;
	}
	// 通过id查询
	@Override
	public WageCountWay findById(String id) {
		String hql = "from WageCountWay way where way.id = ?";
		WageCountWay wageCountWay = new WageCountWay();
		Query query = getSession().createQuery(hql);
		query.setString(0, id);
		wageCountWay = (WageCountWay) query.uniqueResult();
		
		return wageCountWay;
	}
	
	// 修改
	@Override
	public void updById(WageCountWay wageCountWay) {
		getSession().update(wageCountWay);
	}

	// 用于异步请求
	@Override
	public WageCountWay findByPosId(String positionId) {
		String hql = "from WageCountWay wage where wage.positionId = ?";
		Query query = getSession().createQuery(hql);
		query.setString(0, positionId);
		WageCountWay wageCountWay = (WageCountWay) query.uniqueResult();
		return wageCountWay;
	}

	@Override
	public List<WageCountWay> searchByName(String search_name) {
		String hql = "from WageCountWay as wage where wage.positionName like :name";
		List<WageCountWay> listWageCountWay = new ArrayList<WageCountWay>();
		Query query = getSession().createQuery(hql);
		query.setString("name", "%" + search_name + "%");
		listWageCountWay = (List<WageCountWay>) query.list();
		return listWageCountWay;
	}

}
