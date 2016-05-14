package com.stx.zzq.back.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.stx.zzq.back.service.AdminService;
import com.stx.zzq.base.BaseAction;
import com.stx.zzq.common.action.RandomValidateCode;
import com.stx.zzq.common.utils.CommonUtils;
import com.stx.zzq.common.utils.ConstantsCode;
import com.stx.zzq.common.utils.MD5Encode;
import com.stx.zzq.entities.Admin;

@Scope("prototype")
@Controller("back.AdminAction")
public class AdminAction extends BaseAction {
	/**	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private AdminService adminService;

	/* 此处是设置页数和显示的条数 */
	private Integer rows;
	private Integer page;

	/* private HttpServletRequest request = getRequest(); */

	public String login() {
		Admin loginAdmin = new Admin();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if (CommonUtils.isEmpty(username)) {
			request.setAttribute("error", "用户名不能为空");
			return "login";
		}
		loginAdmin = adminService.findByName(username);
		if (CommonUtils.isEmpty(loginAdmin)) {
			request.setAttribute("error", "用户不存在，请先注册");
			return "login";
		}
		if (CommonUtils.isEmpty(password)) {
			request.setAttribute("error", "密码不能为空");
			return "register";
		}
		String encodePassword = getEncodePassword(password);
		if (!loginAdmin.getPassword().equals(encodePassword)) {
			request.setAttribute("error", "密码输入有误");
			return "login";
		}
		request.getSession().setAttribute("user_session", loginAdmin);
		return "loginSuccess";
	}

	public String register() {
		Admin admin = new Admin();
		HttpSession session = request.getSession();
		String verifycode = (String) session.getAttribute(RandomValidateCode.RANDOMCODEKEY);
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		// 判断用户名、验证码
		if (CommonUtils.isEmpty(username)) {
			request.setAttribute("error", "用户名不能为空");
			return "register";
		}
		admin = adminService.findByName(username);
		if (!CommonUtils.isEmpty(admin)) {
			request.setAttribute("error", "用户存在，请重新输入用户名");
			return "register";
		}
		if (!CommonUtils.isEmpty(verifycode)) {
			// 忽略大小写
			if (!verifycode.equalsIgnoreCase(request.getParameter("verifycode"))) {
				admin.put("error", "验证码不正确");
				writeJsonToResponse(admin, response);
				return "register";
			}

			admin = new Admin();
			admin.setUsername(request.getParameter("username"));
			admin.setPassword(request.getParameter("password"));
			admin.setIconPath("/bootstrap/images/01.jpg");
			adminService.add(admin);
			admin.put("success", "注册成功");
			request.setAttribute("admin", admin);
			return "login";
		} else {
			request.setAttribute("error", "验证码输入有误！");
			return "register";
		}
	}

	// ===========系统进行添加===============
	/* 新增管理员 */
	public String add() {
		Admin admin = new Admin();
		admin.setUsername(request.getParameter("username"));
		admin.setPassword(request.getParameter("password"));
		// 设置默认头像
		admin.setIconPath("/bootstrap/images/01.jpg");
		/*
		 * 此处将所有元素直接放入对应的实体类中，之后进行改造 Map map = getRequest().getParameterMap();
		 * admin.putAll(map); admin.get("username");
		 * System.out.println(admin.get("password"));
		 */
		adminService.add(admin);
		admin.put("success", "chenggong");
		writeJsonToResponse(admin, response);
		// request.setAttribute("admin", admin);
		request.setAttribute("admin", admin);
		return "add";
	}

	/* 查询所有的管理员信息 */
	public String findAll() {
		List<Admin> allAdmin = new ArrayList<Admin>();
		allAdmin = adminService.findAll();
		writeJsonToResponse(allAdmin, response);
		return "findAll";
	}

	/* 通过对象查找是否存在 */
	public String findByAdmin() {
		Admin exsistAdmin = new Admin();
		exsistAdmin.setUsername(request.getParameter("username"));
		exsistAdmin.setPassword(request.getParameter("password"));
		exsistAdmin = adminService.findByAdmin(exsistAdmin);
		if (CommonUtils.isEmpty(exsistAdmin)) {
			request.setAttribute("errorMsg", "用户不存在");
			return "error";
		}
		writeJsonToResponse(exsistAdmin, response);
		request.setAttribute("admin", exsistAdmin);
		return "findByAdmin";
	}

	/* 通过管理员名称查询 */
	public String findByName() {
		Admin exsist = new Admin();
		String name = request.getParameter("username");
		exsist = adminService.findByName(name);
		System.out.println(exsist);
		writeJsonToResponse(exsist, response);
		request.setAttribute("exsistOne", exsist);
		return "findByName";
	}
	/* 搜索功能，进行模糊查询 */
	public String searchByName() {
		String search_name = request.getParameter("username");
		if(search_name.getBytes().length != search_name.length()) {
			try {
				search_name = new String(search_name.getBytes("iso-8859-1"), ConstantsCode.ENCODE);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		// 返回的结果
		List<Admin> allAdmin = new ArrayList<Admin>();
		// 空则查询全部
		if(CommonUtils.isEmpty(search_name)) {
			allAdmin = adminService.findAll();
		} else {
			allAdmin = adminService.searchByName(search_name);
		}
		writeJsonToResponse(allAdmin, response);
		return "searchByName";
	}

	/* 通过对象删除 */
	public String delByAdmin() {
		Admin delAdmin = new Admin();
		delAdmin.setUsername(request.getParameter("username"));
		delAdmin.setPassword(request.getParameter("password"));
		int i = adminService.delByAdmin(delAdmin);
		if (i == 0) {
			delAdmin.put("errorMsg", "删除失败");
		} else {
			delAdmin.put("success", "删除成功");
		}
		writeJsonToResponse(delAdmin, response);
		return "delByAdmin";
	}

	/* 通过姓名删除管理员 */
	public String delByName() {
		Admin delAdmin = new Admin();
		String name = request.getParameter("username");
		int i = adminService.delByName(name);
		if (i == 0) {
			delAdmin.put("errorMsg", "删除失败");
		} else {
			delAdmin.put("success", "删除成功");
		}
		writeJsonToResponse(delAdmin, response);
		return "delByName";
	}

	/* 修改管理员密码，用户名不能修改 */
	public String updPassword() {
		Admin updAdmin = new Admin();
		updAdmin.setUsername(request.getParameter("username"));
		updAdmin.setPassword(request.getParameter("password"));
		adminService.updPassword(updAdmin);
		updAdmin.put("msg", "修改成功,请重新登录");
		writeJsonToResponse(updAdmin, response);
		return "updPassword";
	}
	
	public String updPasswordAjax() {
		Admin updAdmin = new Admin();
		updAdmin.setUsername(request.getParameter("username"));
		updAdmin.setPassword(request.getParameter("password"));
		adminService.updPassword(updAdmin);
		updAdmin.put("error", "修改成功,请重新登录");
		writeJsonToResponse(updAdmin, response);
		return "updPasswordAjax";
	}

	public String getPersonalView() {
		return "getPersonalView";
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	// 给密码加密
	private String getEncodePassword(String password) {
		String encodePassword = null;
		try {
			encodePassword = MD5Encode.md5Encode(password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return encodePassword;
	}

}
