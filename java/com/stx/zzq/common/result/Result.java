package com.stx.zzq.common.result;

import java.util.ArrayList;
import java.util.List;

public class Result {
	/**
	 * 表示图片是否已上传成功。
	 */
	private Boolean success;
	private String userid;
	private String username;
	/**
	 * 自定义的附加消息。
	 */
	private String msg;
	/**
	 * 表示原始图片的保存地址。
	 */
	private String sourceUrl;
	/**
	 * 表示所有头像图片的保存地址，该变量为一个数组。
	 */
	private List avatarUrls = new ArrayList();

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getSourceUrl() {
		return sourceUrl;
	}

	public void setSourceUrl(String sourceUrl) {
		this.sourceUrl = sourceUrl;
	}

	public List getAvatarUrls() {
		return avatarUrls;
	}

	public void setAvatarUrls(List avatarUrls) {
		this.avatarUrls = avatarUrls;
	}

}
