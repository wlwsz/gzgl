package com.stx.zzq.back.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stx.zzq.back.dao.AttendanceDao;
import com.stx.zzq.back.service.AttendanceService;
import com.stx.zzq.entities.Attendance;
import com.stx.zzq.entities.Position;

@Service
public class AttendanceServiceImpl implements AttendanceService {

	@Autowired
	private AttendanceDao attDao;

	/* 添加考勤记录 */
	@Override
	public void add(Attendance add) {
		// TODO Auto-generated method stub
		attDao.add(add);
	}

	/* 查询所有的考情记录 */
	@Override
	public List<Attendance> findAll() {
		// TODO Auto-generated method stub
		List<Attendance> all = new ArrayList<Attendance>();
		all = attDao.findAll();
		return all;
	}

	/* 查询某个员工的考情记录 */
	@Override
	public Attendance findByEmpId(Attendance att) {
		// TODO Auto-generated method stub
		Attendance empAtt = new Attendance();
		empAtt = attDao.findByEmpId(att);
		return empAtt;
	}
	/* 查询某个员工的考情记录 */
	@Override
	public Attendance findByEmpId(String empId) {
		// TODO Auto-generated method stub
		Attendance empAtt = new Attendance();
		empAtt = attDao.findByEmpId(empId);
		return empAtt;
	}

	/* 查询某条的考情记录 */
	@Override
	public Attendance findById(String id) {
		// TODO Auto-generated method stub
		Attendance att = new Attendance();
		att = attDao.findById(id);
		return att;
	}

	/* 通过id修改考勤记录 */
	@Override
	public void updById(Attendance updById) {
		// TODO Auto-generated method stub
		attDao.updById(updById);
	}

	/* 删除特定的考情记录 */
	@Override
	public int delById(String delById) {
		// TODO Auto-generated method stub
		Integer byId = Integer.parseInt(delById);
		int i = 0;
		i = attDao.delById(byId);
		return i;
	}

	/* 搜索 */
	@Override
	public List<Attendance> searchByKey(String key, int code) {
		List<Attendance> listAttendancen = new ArrayList<Attendance>();
		listAttendancen = attDao.searchByKey(key, code);
		return listAttendancen;
	}

	@Override
	public List<Attendance> searchByKey(String id, String year, String month) {
		List<Attendance> listAttendancen = new ArrayList<Attendance>();
		listAttendancen = attDao.searchByKey(id, year, month);
		return listAttendancen;
	}

	@Override
	public List<Attendance> searchByKey(String key1, int code1, String key2, int code2) {
		List<Attendance> listAttendancen = new ArrayList<Attendance>();
		listAttendancen = attDao.searchByKey(key1, code1, key2, code2);
		return listAttendancen;
	}
}
