package com.stx.zzq.back.dao.Impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.stx.zzq.back.dao.AdminDao;
import com.stx.zzq.base.BaseDao;
import com.stx.zzq.entities.Admin;

@Repository
public class AdminDaoImpl extends BaseDao implements AdminDao {

	/* 新增管理员 */
	@Override
	public void add(Admin admin) {
		getSession().save(admin);
	}

	/* 查询所有管理员信息 */
	@Override
	public List<Admin> findAll() {
		String hql = "From Admin ad";
		List<Admin> all = getSession().createQuery(hql).list();
		return all;
	}

	/* 通过对象查询 */
	@Override
	public Admin findByAdmin(Admin admin) {
		String hql = "From Admin adm where adm.username = ? and adm.password = ?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, admin.getUsername());
		query.setParameter(1, admin.getPassword());
		Admin exists = (Admin) query.uniqueResult();
		System.out.println(exists);
		return exists;
	}

	/* 通过名字查询对象 */
	@Override
	public Admin findByName(String name) {
		String hql = "From Admin ad where ad.username = ?";
		Admin exsist = new Admin();
		Query query = getSession().createQuery(hql);
		query.setString(0, name);
		exsist = (Admin) query.uniqueResult();
		System.out.println(exsist);
		return exsist;
	}

	/* 通过对象删除 */
	@Override
	public int delByAdmin(Admin delAdmin) {
		String hql = "delete Admin as ad where ad.username = ? and ad.password = ?";
		Query query = getSession().createQuery(hql);
		query.setString(0, delAdmin.getUsername());
		query.setString(1, delAdmin.getPassword());
		int i = query.executeUpdate();
		System.out.println(i);
		return i;
	}

	/* 通过姓名删除管理员 */
	@Override
	public int delByName(String name) {
		String hql = "delete Admin as ad where ad.username = ?";
		Query query = getSession().createQuery(hql);
		query.setString(0, name);
		int i = query.executeUpdate();
		System.out.println(i);
		return i;
	}

	/* 修改管理员密码，用户名不能修改 */
	@Override
	public void updPassword(Admin updAdmin) {
		String hql = "update Admin admin set admin.password=? where admin.username=?";
		Query query = getSession().createQuery(hql);
		query.setString(0, updAdmin.getPassword());
		query.setString(1, updAdmin.getUsername());
		int i = query.executeUpdate();
		System.out.println(updAdmin);
	}

	/* 修改头像 */
	public void updIcon(Admin iconAdmin) {
		getSession().update(iconAdmin);
		System.out.println();
	}

	/* 搜索功能，进行模糊查询 */
	@Override
	public List<Admin> searchByName(String search_name) {
		String hql = "from Admin as admin where admin.username like :name";
		List<Admin> listAdmin = new ArrayList<Admin>();
		Query query = getSession().createQuery(hql);
		query.setString("name", "%" + search_name + "%");
		listAdmin = (List<Admin>) query.list();
		return listAdmin;
	}

}
