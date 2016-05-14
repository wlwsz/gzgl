package com.stx.zzq.back.dao.Impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.stx.zzq.back.dao.PositionDao;
import com.stx.zzq.base.BaseDao;
import com.stx.zzq.common.utils.ConstantsCode;
import com.stx.zzq.entities.Department;
import com.stx.zzq.entities.Position;

@Repository
public class PositionDaoImpl extends BaseDao implements PositionDao {

	/* 新添加职位 */
	@Override
	public void add(Position position) {
//		getHibernateTemplate().save(position);
		getSession().save(position);
	}

	/* 查询全部职位 */
	@Override
	public List<Position> findAll() {
		// TODO 
		String hql = "From Position";
		List<Position> all = new ArrayList<Position>();
		all = getSession().createQuery(hql).list();
		return all;
	}

	/* 根据id查询 */
	@Override
	public Position findById(String id) {
		// TODO 
		Position pos = new Position();
		String hql = "From Position where positionId = ?";
		Query query = getSession().createQuery(hql);
		query.setString(0, id);
		pos = (Position) query.uniqueResult();
		return pos;
	}

	/* 根据id修改职位 */
	@Override
	public void updById(Position upPos) {
		// TODO 
		getSession().update(upPos);
	}

	/* 删除特定的对象 */
	@Override
	public void delPos(Position delPos) {
		// TODO Auto-generated method stub
		getSession().delete(delPos);
	}

	/* 通过id进行删除 */
	@Override
	public int delById(String id) {
		// TODO Auto-generated method stub
		String hql = "delete Position where positionId = ?";
		Query query = getSession().createQuery(hql);
		query.setString(0, id);
		int i = query.executeUpdate();
		return i;
	}

	// 搜索功能
	@Override
	public List<Position> searchByKey(String key, int code) {
		List<Position> listPosition = new ArrayList<Position>();
		String hql = "";
		if(code == ConstantsCode.CODE0) {
			hql = "from Position as pos where pos.positionId like :key";
		}
		if(code == ConstantsCode.CODE1) {
			hql = "from Position as pos where pos.positionName like :key";
		}
		if(code == ConstantsCode.CODE2) {
			hql = "from Position as pos where pos.departmentName like :key";
		}
		Query query = getSession().createQuery(hql);
		query.setString("key", "%" + key + "%");
		listPosition = (List<Position>) query.list();
		return listPosition;
	}

	@Override
	public List<Position> searchByKey(String key0, String key1, String key2) {
		String hql = "from Position as pos where pos.positionId like :key0 and pos.positionName like :key1 and pos.departmentName like :key2";
		List<Position> listPosition = new ArrayList<Position>();
		Query query = getSession().createQuery(hql);
		query.setString("key0", "%" + key0 + "%");
		query.setString("key1", "%" + key1 + "%");
		query.setString("key2", "%" + key2 + "%");
		listPosition = (List<Position>) query.list();
		return listPosition;
	}

	@Override
	public List<Position> searchByKey(String key1, int code1, String key2, int code2) {
		List<Position> listPosition = new ArrayList<Position>();
		String hql = null;
		if(code1 == ConstantsCode.CODE0 && code2 == ConstantsCode.CODE1) {
			hql = "from Position as pos where pos.positionId like :key1 and pos.positionName like :key2";
		}
		if(code1 == ConstantsCode.CODE0 && code2 == ConstantsCode.CODE2) {
			hql = "from Position as pos where pos.positionId like :key1 and pos.departmentName like :key2";
		}
		if(code1 == ConstantsCode.CODE1 && code2 == ConstantsCode.CODE2) {
			hql = "from Position as pos where pos.positionName like :key1 and pos.departmentName like :key2";
		}
		
		Query query = getSession().createQuery(hql);
		query.setString("key1", "%" + key1 + "%");
		query.setString("key2", "%" + key2 + "%");
		listPosition = (List<Position>) query.list();
		return listPosition;
	}

}
