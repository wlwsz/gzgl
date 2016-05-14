package com.stx.zzq.back.dao;

import java.util.List;

import com.stx.zzq.entities.Deduction;

public interface DeductionDao {

	// 增加
	void add(Deduction addDeduction);

	// 查询全部
	List<Deduction> findAll();
	
	// id查询
	Deduction findById(int id);

	// 修改
	void updById(Deduction updById);

	// 通过id删除
	int delById(Integer id);

	// 通过positionId删除
	Deduction findByPosId(String posId);
	
	// 搜索
	List<Deduction> searchByName(String search_name);

}
