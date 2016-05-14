package com.stx.zzq.back.service;

import java.util.List;

import com.stx.zzq.entities.Department;

public interface DepartmentService {

	/* 添加部门 */
	void add(Department depart);

	/* 查询所有部门 */
	List<Department> findAll();

	/* 通过id查询部门 */
	Department findById(String id);

	/* 通过实体类查询部门 */
	Department findByDep(Department departDep);

	/* 搜索功能 */
	List<Department> searchByKey(String search_key, int code);
	List<Department> searchByKey(String search_key1, String search_key2);
	
	/* 修改部门信息 */
	void updDep(Department updDep);

	/* 通过Id修改部门信息 */
	int updById(Department updDepId);

	/* 删除部门信息 */
	void delDep(Department delDep);

	/* 通过id编号删除 */
	int delById(String id);

}
