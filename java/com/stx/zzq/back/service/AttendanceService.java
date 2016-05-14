package com.stx.zzq.back.service;

import java.util.List;

import com.stx.zzq.entities.Attendance;
import com.stx.zzq.entities.Position;

public interface AttendanceService {

	/* 添加考勤记录 */
	void add(Attendance add);

	/* 查询所有的考情记录 */
	List<Attendance> findAll();

	/* 查询某个员工的考情记录 */
	Attendance findByEmpId(Attendance att);

	/* 查询某条的考情记录 */
	Attendance findById(String id);

	/* 通过id修改考勤记录 */
	void updById(Attendance updById);

	/* 删除特定的考情记录 */
	int delById(String delById);
	
	/* 搜索 */
	List<Attendance> searchByKey(String key, int code);
	List<Attendance> searchByKey(String id, String year, String month);
	List<Attendance> searchByKey(String key1, int code1, String key2, int code2);

}
