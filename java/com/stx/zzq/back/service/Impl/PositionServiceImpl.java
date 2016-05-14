package com.stx.zzq.back.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stx.zzq.back.dao.PositionDao;
import com.stx.zzq.back.service.PositionService;
import com.stx.zzq.entities.Position;

@Service
public class PositionServiceImpl implements PositionService {

	@Autowired
	private PositionDao positionDao;
	
	/* 新添加职位 */
	@Override
	public void add(Position position) {
		// TODO 判断条件
		positionDao.add(position);
		
	}

	/* 查询全部职位 */
	@Override
	public List<Position> findAll() {
		// TODO Auto-generated method stub
		List<Position> all = new ArrayList<Position>();
		all = positionDao.findAll();
		return all;
	}

	/* 根据id查询 */
	@Override
	public Position findById(String id) {
		// TODO
		Position pos = new Position();
		pos = positionDao.findById(id);
		return pos;
	}

	/* 根据id修改职位 */
	@Override
	public void updById(Position upPos) {
		// TODO 
		positionDao.updById(upPos);
	}

	/* 删除特定的对象 */
	@Override
	public void delPos(Position delPos) {
		// TODO Auto-generated method stub
		positionDao.delPos(delPos);
	}

	/* 通过id进行删除 */
	@Override
	public int delById(String id) {
		// TODO Auto-generated method stub
		int i = positionDao.delById(id);
		
		return i;
	}

	// 搜索功能
	@Override
	public List<Position> searchByKey(String key, int code) {
		List<Position> listPosition = new ArrayList<Position>();
		listPosition = positionDao.searchByKey(key, code);
		return listPosition;
	}

	@Override
	public List<Position> searchByKey(String key1, String key2, String key3) {
		List<Position> listPosition = new ArrayList<Position>();
		listPosition = positionDao.searchByKey(key1, key2, key3);
		return listPosition;
	}

	@Override
	public List<Position> searchByKey(String key1, int code1, String key2, int code2) {
		List<Position> listPosition = new ArrayList<Position>();
		listPosition = positionDao.searchByKey(key1, code1, key2, code2);
		return listPosition;
	}

}
