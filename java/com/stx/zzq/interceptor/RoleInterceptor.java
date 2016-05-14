package com.stx.zzq.interceptor;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

//@Controller
public class RoleInterceptor extends AbstractInterceptor {

	// @Autowired
	// private AdminService adminService;
	// @Autowired
	// private EmployeeService employeeService;

	@Override
	public String intercept(ActionInvocation ai) throws Exception {
		Map<String, Object> session = ai.getInvocationContext().getSession();
		String role = (String) session.get("role");
		if ("1".equals(role)) {

		}
		if ("2".equals(role)) {

		}
		return null;
	}

}
