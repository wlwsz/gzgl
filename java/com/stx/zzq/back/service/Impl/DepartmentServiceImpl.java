package com.stx.zzq.back.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stx.zzq.back.dao.DepartmentDao;
import com.stx.zzq.back.service.DepartmentService;
import com.stx.zzq.entities.Department;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private DepartmentDao departDao;

	/* 添加部门 */
	@Override
	public void add(Department depart) {
		departDao.add(depart);
	}

	/* 查询所有部门 */
	@Override
	public List<Department> findAll() {
		List<Department> all = new ArrayList<Department>();
		all = departDao.findAll();
		return all;
	}

	/* 通过id查询部门 */
	@Override
	public Department findById(String id) {
		Department depart = new Department();
		depart = departDao.findById(id);
		// TODO进行一系列判断
		return depart;
	}

	/* 通过实体类查询部门 */
	@Override
	public Department findByDep(Department departDep) {
		// TODO 一系列的判断条件
		Department depart_Dep = new Department();
		depart_Dep = departDao.findByDep(departDep);
		return depart_Dep;
	}

	/* 修改部门信息 */
	@Override
	public void updDep(Department updDep) {
		// TODO 要做一系列的判断
		departDao.updDep(updDep);
	}

	/* 通过Id修改部门信息 */
	@Override
	public int updById(Department updDepId) {
		// TODO
		int i = departDao.updById(updDepId);
		return i;
	}

	/* 删除部门信息 */
	@Override
	public void delDep(Department delDep) {
		// TODO
		departDao.delDep(delDep);
	}

	/* 通过id编号删除 */
	@Override
	public int delById(String id) {
		// TODO
		int i = departDao.delById(id);
		return i;
	}

	/* 搜索 */
	@Override
	public List<Department> searchByKey(String search_key, int code) {
		List<Department> listDepartment = new ArrayList<Department>();
		listDepartment = departDao.searchByKey(search_key, code);
		return listDepartment;
	}

	@Override
	public List<Department> searchByKey(String search_key1, String search_key2) {
		List<Department> listDepartment = new ArrayList<Department>();
		listDepartment = departDao.searchByKey(search_key1, search_key2);
		return listDepartment;
	}

}
