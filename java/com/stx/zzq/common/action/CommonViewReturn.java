package com.stx.zzq.common.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.stx.zzq.base.BaseAction;

@Scope("prototype")
@Controller("common.CommonViewReturn")
public class CommonViewReturn extends BaseAction {

	// 获取后台主界面
	public String getMainView() {
		return "backMain";
	}

	// 获取后台员工编辑界面
	public String getEmployees() {
		return "employees";
	}

	// 获取后台职位编辑界面
	public String getDepartment() {
		return "department";
	}
	
	// 获取

}
