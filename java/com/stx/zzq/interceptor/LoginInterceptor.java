package com.stx.zzq.interceptor;

import java.util.Map;

import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.stx.zzq.entities.Admin;

/**
 * 此过滤器实现未登录不能访问<br>
 * 因为第一次登录没有session <br>
 * 所以首先通过对URL进行拦截最佳 <br>
 * 如果是访问登录URL，则不需要多余的处理 如果不是，则进行session判断是否登录过 <br>
 * 没有则进行登录，有则放其通过
 * 
 * @author zhangzunqiao
 */
@Controller("back.LoginInterceptor")
public class LoginInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		// 取得请求相关的ActionContext实例
		ActionContext ctx = invocation.getInvocationContext();
		// 对访问URL进行拦截
		String namespace = invocation.getProxy().getNamespace();
		String actionName = invocation.getProxy().getActionName();
		if ("/back".equals(namespace) && "adm_login".equals(actionName)) {
			return invocation.invoke();
		} 
		if(actionName.contains("adm_add")) {
			return invocation.invoke();
		} else {
			// 否则进行session判断
			Map session = ctx.getSession();
			Admin user = (Admin) session.get("user_session");
			System.out.println(user);
			if (user != null) {
				return invocation.invoke();
			}
			ctx.put("tip", "你还没有登录");
			return Action.LOGIN;
		}

	}

}
