package com.stx.zzq.back.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stx.zzq.back.dao.AdminDao;
import com.stx.zzq.back.service.AdminService;
import com.stx.zzq.common.utils.MD5Encode;
import com.stx.zzq.entities.Admin;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminDao adminDao;

	/* 新增管理员 */
	@Override
	public void add(Admin admin) {
		md5Password(admin);
		adminDao.add(admin);
	}

	/* 查询所有管理员信息 */
	@Override
	public List<Admin> findAll() {
		List<Admin> all = new ArrayList<Admin>();
		all = adminDao.findAll();
		return all;
	}

	/* 查询指定的对象 */
	@Override
	public Admin findByAdmin(Admin admin) {
		Admin exsistAdm = new Admin();
		md5Password(admin);
		exsistAdm = adminDao.findByAdmin(admin);
		return exsistAdm;
	}

	/* 通过名字查询对象 */
	@Override
	public Admin findByName(String name) {
		Admin exsistAdmin = new Admin();
		exsistAdmin = adminDao.findByName(name);
		return exsistAdmin;
	}

	/* 通过对象删除 */
	@Override
	public int delByAdmin(Admin delAdmin) {
		// TODO 先判断存不存在
		md5Password(delAdmin);
		int i = adminDao.delByAdmin(delAdmin);
		return i;
	}

	/* 通过姓名删除管理员 */
	@Override
	public int delByName(String name) {
		int i = adminDao.delByName(name);
		return i;
	}

	/* 修改管理员密码，用户名不能修改 */
	@Override
	public void updPassword(Admin updAdmin) {
		md5Password(updAdmin);
		adminDao.updPassword(updAdmin);
	}
	
	/* 改密码加密 */
	public void md5Password(Admin admin) {
		try {
			String password = MD5Encode.md5Encode(admin.getPassword());
			admin.setPassword(password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/* 修改头像 */
	public void updIcon(Admin iconAdmin) {
		adminDao.updIcon(iconAdmin);
	}

	/* 搜索功能，进行模糊查询 */
	@Override
	public List<Admin> searchByName(String search_name) {
		List<Admin> listAdmin = new ArrayList<Admin>();
		listAdmin = adminDao.searchByName(search_name);
		return listAdmin;
	}

}
