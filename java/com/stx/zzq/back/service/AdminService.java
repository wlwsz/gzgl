package com.stx.zzq.back.service;

import java.util.List;

import com.stx.zzq.entities.Admin;

public interface AdminService {

	/* 添加管理员 */
	public void add(Admin admin);

	/* 查询所有 */
	public List<Admin> findAll();

	/* 查询指定的对象 */
	public Admin findByAdmin(Admin admin);

	/* 通过名字查询对象 */
	public Admin findByName(String name);
	
	/* 通过名字搜索 */
	public List<Admin> searchByName(String search_name);

	/* 通过对象删除 */
	public int delByAdmin(Admin delAdmin);

	/* 通过姓名删除管理员 */
	public int delByName(String name);

	/* 修改管理员密码，用户名不能修改 */
	public void updPassword(Admin updAdmin);
	
	/* 修改头像 */
	public void updIcon(Admin iconAdmin);

}
