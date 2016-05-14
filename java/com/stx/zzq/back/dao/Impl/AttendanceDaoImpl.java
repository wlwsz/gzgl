package com.stx.zzq.back.dao.Impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.stx.zzq.back.dao.AttendanceDao;
import com.stx.zzq.base.BaseDao;
import com.stx.zzq.common.utils.ConstantsCode;
import com.stx.zzq.entities.Attendance;
import com.stx.zzq.entities.Position;

@Repository
public class AttendanceDaoImpl extends BaseDao implements AttendanceDao {

	/* 添加考勤记录 */
	@Override
	public void add(Attendance add) {
		// TODO Auto-generated method stub
		getSession().save(add);
	}

	/* 查询所有的考情记录 */
	@Override
	public List<Attendance> findAll() {
		// TODO Auto-generated method stub
		String hql = "From Attendance";
		List<Attendance> all = new ArrayList<Attendance>();
		all = getSession().createQuery(hql).list();
		return all;
	}

	/* 查询某个员工的考情记录 */
	@Override
	public Attendance findByEmpId(Attendance att) {
		// TODO Auto-generated method stub
		String hql = "From Attendance where employeeId = ?";
		Query query = getSession().createQuery(hql);
		query.setString(0, att.getEmployeeId());
		Attendance exsist = new Attendance();
		exsist = (Attendance) query.uniqueResult();
		return exsist;
	}

	/* 查询某条的考情记录 */
	@Override
	public Attendance findById(String id) {
		// TODO Auto-generated method stub
		String hql = "From Attendance where attendanceId = ?";
		Query query = getSession().createQuery(hql);
		query.setString(0, id);
		Attendance att = new Attendance();
		att = (Attendance) query.uniqueResult();
		return att;
	}

	/* 通过id修改考勤记录 */
	@Override
	public void updById(Attendance updById) {
		// TODO Auto-generated method stub
		getSession().update(updById);
	}

	/* 删除特定的考情记录 */
	@Override
	public int delById(Integer byId) {
		// TODO Auto-generated method stub
		String hql = "delete Attendance where attendanceId = ?";
		int i = 0;
		Query query = getSession().createQuery(hql);
		query.setInteger(0, byId);
		i = query.executeUpdate();
		return i;
	}

	/* 搜索 */
	@Override
	public List<Attendance> searchByKey(String key, int code) {
		List<Attendance> listAttendance = new ArrayList<Attendance>();
		String hql = "";
		if(code == ConstantsCode.CODE0) {
			hql = "from Attendance as att where att.employeeId like :key";
		}
		if(code == ConstantsCode.CODE1) {
			hql = "from Attendance as att where att.year like :key";
		}
		if(code == ConstantsCode.CODE2) {
			hql = "from Attendance as att where att.month like :key";
		}
		Query query = getSession().createQuery(hql);
		query.setString("key", "%" + key + "%");
		listAttendance = (List<Attendance>) query.list();
		return listAttendance;
	}

	@Override
	public List<Attendance> searchByKey(String id, String year, String month) {
		List<Attendance> listAttendance = new ArrayList<Attendance>();
		String hql = "from Attendance as att where att.employeeId like :key0 and att.year like :key1 and att.month like :key2";
		Query query = getSession().createQuery(hql);
		query.setString("key0", "%" + id + "%");
		query.setString("key1", year);
		query.setString("key2", month);
		listAttendance = (List<Attendance>) query.list();
		return listAttendance;
	}

	@Override
	public List<Attendance> searchByKey(String key1, int code1, String key2, int code2) {
		List<Attendance> listAttendance = new ArrayList<Attendance>();
		String hql = "";
		if(code1 == ConstantsCode.CODE0 && code2 == ConstantsCode.CODE1) {
			hql = "from Attendance as att where att.employeeId like :key1 and att.year like :key2";
		}
		if(code1 == ConstantsCode.CODE0 && code2 == ConstantsCode.CODE2) {
			hql = "from Attendance as att where att.employeeId like :key1 and att.month like :key2";
		}
		if(code1 == ConstantsCode.CODE1 && code2 == ConstantsCode.CODE2) {
			hql = "from Attendance as att where att.year like :key1 and att.month like :key2";
		}
		
		Query query = getSession().createQuery(hql);
		query.setString("key1", "%" + key1 + "%");
		query.setString("key2", "%" + key2 + "%");
		listAttendance = (List<Attendance>) query.list();
		return listAttendance;
	}

}
