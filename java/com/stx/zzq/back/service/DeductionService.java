package com.stx.zzq.back.service;

import java.util.List;

import com.stx.zzq.entities.Deduction;

public interface DeductionService {

	// 增加
	void add(Deduction addDeduction);

	// 查询全部
	List<Deduction> findAll();
	
	// 通过id
	Deduction findById(int id);

	// 修改
	void updById(Deduction updById);

	// 删除
	int delById(Integer id);

	// 通过positionId查找
	Deduction findByPosId(String posId);
	
	// 搜索
	List<Deduction> searchByName(String search_name);

}
