package com.stx.zzq.entities;

import net.sf.json.JSONString;

import com.stx.zzq.base.BaseEntity;

/**
 * 主要是为了做控制权限 <br/>
 * 操作分类 <br/>
 * 增、删、改、查 <br/>
 * 根据等级进行相应的操作 <br/>
 * 
 * @author zhangzunqiao
 */
public class Operation extends BaseEntity {
	/* 数据添加权限 */
	private final String Add = "add";
	/* 删除 */
	private final String Delete = "delete";
	/* 修改 */
	private final String Update = "update";
	/* 查询 */
	private final String Query = "query";

	public String getAdd() {
		return Add;
	}

	public String getDelete() {
		return Delete;
	}

	public String getUpdate() {
		return Update;
	}

	public String getQuery() {
		return Query;
	}

}
