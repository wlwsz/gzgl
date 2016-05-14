package com.stx.zzq.back.dao.Impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.stx.zzq.back.dao.DepartmentDao;
import com.stx.zzq.base.BaseDao;
import com.stx.zzq.common.utils.ConstantsCode;
import com.stx.zzq.entities.Admin;
import com.stx.zzq.entities.Department;

@Repository
public class DepartmentDaoImpl extends BaseDao implements DepartmentDao {

	/* 添加部门 */
	@Override
	public void add(Department depart) {
		getSession().save(depart);
	}

	/* 查询所有部门 */
	@Override
	public List<Department> findAll() {
		List<Department> all = new ArrayList<Department>();
		String hql = "From Department";
		all = getSession().createQuery(hql).list();
		System.out.println(all);
		return all;
	}

	/* 通过id查询部门 */
	@Override
	public Department findById(String id) {
		String hql = "From Department dep where dep.departmentId = ?";
		Query query = getSession().createQuery(hql);
		query.setString(0, id);
		Department depart = (Department) query.uniqueResult();
		return depart;
	}

	/* 通过实体类查询部门 */
	@Override
	public Department findByDep(Department departDep) {
		String hql = "From Department dep where dep.departmentId = ? and dep.departmentName = ?";
		Query query = getSession().createQuery(hql);
		query.setString(0, departDep.getDepartmentId());
		query.setString(1, departDep.getDepartmentName());
		Department dep = new Department();
		dep = (Department) query.uniqueResult();
		return dep;
	}

	/* 修改部门信息 */
	@Override
	public void updDep(Department updDep) {
		getSession().update(updDep);
	}

	/* 通过Id修改部门信息 */
	@Override
	public int updById(Department updDepId) {
		// TODO
		String hql = "update Department set departmentName = ? , memo = ? where departmentId = ?";
		Query query = getSession().createQuery(hql);
		query.setString(0, updDepId.getDepartmentName());
		query.setString(1, updDepId.getMemo());
		query.setString(2, updDepId.getDepartmentId());
		
		int i = query.executeUpdate();
		return i;
	}

	/* 删除部门信息 */
	@Override
	public void delDep(Department delDep) {
		getSession().delete(delDep);
	}

	/* 通过id编号删除 */
	@Override
	public int delById(String id) {
		// TODO
		String hql = "delete Department where departmentId = ?";
		Query query = getSession().createQuery(hql);
		query.setString(0, id);
		int i = query.executeUpdate();
		return i;
	}

	/* 搜索功能 */
	@Override
	public List<Department> searchByKey(String search_key, int code) {
		String hql = null;
		if (code == ConstantsCode.CODE0) {
			hql = "from Department as dep where dep.departmentId like :key";
		} 
		if(code == ConstantsCode.CODE1){
			hql = "from Department as dep where dep.departmentName like :key";
		}
		List<Department> listDepartment = new ArrayList<Department>();
		Query query = getSession().createQuery(hql);
		query.setString("key", "%" + search_key + "%");
		listDepartment = (List<Department>) query.list();
		return listDepartment;
	}

	@Override
	public List<Department> searchByKey(String search_key1, String search_key2) {
		String hql = "from Department as dep where dep.departmentId like :key1 and dep.departmentName like :key2";
		List<Department> listDepartment = new ArrayList<Department>();
		Query query = getSession().createQuery(hql);
		query.setString("key1", "%" + search_key1 + "%");
		query.setString("key2", "%" + search_key2 + "%");
		listDepartment = (List<Department>) query.list();
		return listDepartment;
	}

}
