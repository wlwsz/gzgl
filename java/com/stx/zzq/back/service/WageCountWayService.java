package com.stx.zzq.back.service;

import java.util.List;

import com.stx.zzq.entities.Admin;
import com.stx.zzq.entities.WageCountWay;

public interface WageCountWayService {

	// 添加
	void add(WageCountWay wageCountWay);

	// 查询全部
	List<WageCountWay> findAll();

	// 根据id查询
	WageCountWay findById(String id);
	
	/* 通过名字搜索 */
	public List<WageCountWay> searchByName(String search_name);

	// 根据id修改
	void updById(WageCountWay wageCountWay);

	// 用于异步请求
	WageCountWay findByPosId(String positionId);

}
