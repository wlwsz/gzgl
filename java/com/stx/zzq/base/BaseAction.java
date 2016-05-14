package com.stx.zzq.base;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.stx.zzq.common.utils.CommonUtils;
import com.stx.zzq.common.utils.ConstantsCode;

public class BaseAction extends ActionSupport {

	/* 访问请求等 */
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected HttpSession session;
	
	public BaseAction() {
		this.request = ServletActionContext.getRequest();
		this.response = ServletActionContext.getResponse();
		this.session = this.request.getSession();
		init();
	}

	/* 设置编码格式 */
	private void init() {
		try {
			this.request.setCharacterEncoding(ConstantsCode.ENCODE);
			this.response.setCharacterEncoding(ConstantsCode.ENCODE);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} finally {
			this.session.setMaxInactiveInterval(10000);
		}
	}

	/* 获取request对象 */
	public HttpServletRequest getRequest() {
		return this.request;
	}

	/* 获取response对象 */
	public HttpServletResponse getResponse() {
		return this.response;
	}

	/* 获取session对象 */
	public HttpSession getSession() {
		return getRequest().getSession();
	}
	
	/* 将json对象写入response对象之中 */
	protected void writeJsonToResponse(Object obj, HttpServletResponse response) {
		JSONArray jsonArray = CommonUtils.getMlToJson(obj);
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.append(jsonArray.toString());
			System.out.println(jsonArray.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

}
