package com.stx.zzq.back.service;

import java.util.List;

import com.stx.zzq.entities.Attendance;
import com.stx.zzq.entities.Sell;

public interface SellService {

	/* 添加销售 */
	void add(Sell add);

	/* 查询全部销售 */
	List<Sell> findAll();
	
	Sell findByEmpId(String empId);

	/* 删除销售 */
	void delSell(Sell id);

	/* 修改销售 */
	void updSell(Sell updSell);
	
	/* 通过id来删除数据 */
	int delById(String id);

	/* 搜索 */
	List<Sell> searchByKey(String key, int code);
	List<Sell> searchByKey(String name, String year, String month);
	List<Sell> searchByKey(String key1, int code1, String key2, int code2);
	
}
