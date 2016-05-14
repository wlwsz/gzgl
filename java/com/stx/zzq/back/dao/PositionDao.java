package com.stx.zzq.back.dao;

import java.util.List;

import com.stx.zzq.entities.Position;

public interface PositionDao {

	/* 新添加职位 */
	void add(Position position);

	/* 查询全部职位 */
	List<Position> findAll();

	/* 根据id查询 */
	Position findById(String id);

	/* 根据id修改职位 */
	void updById(Position upPos);

	/* 删除特定的对象 */
	void delPos(Position delPos);

	/* 通过id进行删除 */
	int delById(String id);

	/* 搜索 */
	List<Position> searchByKey(String key, int code);
	List<Position> searchByKey(String key1, String key2, String key3);
	List<Position> searchByKey(String key1, int code1, String key2, int code2);

}
