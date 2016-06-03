package com.stx.zzq.back.dao.Impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.stx.zzq.back.dao.DeductionDao;
import com.stx.zzq.base.BaseDao;
import com.stx.zzq.entities.Deduction;
import com.stx.zzq.entities.Position;

@Repository
public class DeductionDaoImpl extends BaseDao implements DeductionDao {

	// 增加
	@Override
	public void add(Deduction addDeduction) {
		// TODO Auto-generated method stub
		getSession().save(addDeduction);
	}

	// 查询全部
	@Override
	public List<Deduction> findAll() {
		// TODO Auto-generated method stub
		String hql = "From Deduction";
		List<Deduction> all = new ArrayList<Deduction>();
		all = getSession().createQuery(hql).list();
		return all;
	}

	// 修改
	@Override
	public void updById(Deduction updById) {
		System.out.println(updById);
		getSession().update(updById);
	}

	// 通过id删除
	@Override
	public int delById(Integer id) {
		// TODO Auto-generated method stub
		int i = 0;
		String hql = "delete Deduction where deductionId = ?";
		Query query = getSession().createQuery(hql);
		query.setInteger(0, id);
		i = query.executeUpdate();
		return i;
	}

	// 通过positionId
	@Override
	public Deduction findByPosId(String posId) {
		// TODO Auto-generated method stub
		Deduction dedu = new Deduction();
		String hql = "From Deduction deduc where deduc.positionId = ?";
		Query query = getSession().createQuery(hql);
		query.setString(0, posId);
		dedu = (Deduction) query.uniqueResult();
		return dedu;
	}

	// id查询
	@Override
	public Deduction findById(int id) {
		Deduction findById = new Deduction();
		String hql = "From Deduction where deductionId = ?";
		Query query = getSession().createQuery(hql);
		query.setInteger(0, id);
		findById = (Deduction) query.uniqueResult();
		return findById;
	}

	// 搜索
	@Override
	public List<Deduction> searchByName(String search_name) {
		List<Deduction> listDeduction = new ArrayList<Deduction>();
		String hql = "From Deduction ded where ded.positionName like :key";
		Query query = getSession().createQuery(hql);
		query.setString("key", "%" + search_name + "%");
		listDeduction = query.list();

		return listDeduction;
	}

	// 透过positionId查询数据
	@Override
	public List<Deduction> findByPosId(Position position) {
		List<Deduction> allDeduc = new ArrayList<Deduction>();
		String hql = "from Deduction deduc where deduc.positionId = ?";
		Query query = getSession().createQuery(hql);
		query.setString(0, position.getPositionId());
		allDeduc = query.list();
		
		return allDeduc;
	}

}
