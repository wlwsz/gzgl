package com.stx.zzq.entities;

import com.stx.zzq.base.BaseEntity;

/**
 * 管理员
 * @author zhangzunqiao
 */
public class Admin extends BaseEntity {

	/** 管理员用户名 */
	private String username;
	/** 登录密码 */
	private String password;
	/** 头像路径 */
	private String iconPath;

	public String getUsername() {
		return (String) get("username");
	}

	public void setUsername(String username) {
		put("username", username);
	}

	public String getPassword() {
		return (String) get("password");
	}

	public void setPassword(String password) {
		put("password", password);
	}

	public String getIconPath() {
		return (String) get("iconPath");
	}

	public void setIconPath(String iconPath) {
		put("iconPath", iconPath);
	}

	/** toString */
	@Override
	public String toString() {
		return "username=" + getUsername() + ", password=" + getPassword()
				+ ", iconPath=" + getIconPath();
	}

}
