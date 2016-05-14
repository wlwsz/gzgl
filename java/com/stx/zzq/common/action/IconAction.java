package com.stx.zzq.common.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.util.Streams;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;
import com.stx.zzq.back.service.AdminService;
import com.stx.zzq.base.BaseAction;
import com.stx.zzq.common.result.Result;
import com.stx.zzq.common.service.IconService;
import com.stx.zzq.common.utils.CommonUtils;
import com.stx.zzq.entities.Admin;


@Scope("prototype")
@Controller("common.IconAction")
public class IconAction extends BaseAction {
	// 上传文件
	private File avatar1;
	private File avatar2;
	private File avatar3;
	
	@Autowired
	private AdminService adminService;

	@Autowired
	private IconService iconService;

	/* 显示头像上传页面 */
	public String initView() {
		return SUCCESS;
	}

	/* 此功能上传头像 */
	public String saveIcon() {

		HttpServletRequest request = getRequest();
		Admin iconAdmin = (Admin) request.getSession().getAttribute("user_session");
		
		List<File> fileList = new ArrayList<File>();
		fileList.add(avatar1);
		fileList.add(avatar2);
		fileList.add(avatar3);

		Result result = new Result();
		result.setSuccess(false);
		result.setMsg("Failure!");

		String contentType = request.getContentType();
		if (contentType.indexOf("multipart/form-data") >= 0) {
			// 保存150*150 80*80 50*50的图片的路径
			List<String> fileNameList = new ArrayList<String>();
			try {
				// 定义一个变量用以储存当前头像的序号
				int avatarNumber = 1;
				BufferedInputStream inputStream = null;
				BufferedOutputStream outputStream = null;
				// 上传到个人文件夹下
				String imagePath = ServletActionContext.getServletContext()
						.getRealPath("/upload");
				imagePath = imagePath + "\\" +iconAdmin.getUsername();
				File personPath = new File(imagePath);
				if(!personPath.exists()) {
					personPath.mkdirs();
				}
				// 上传到制定的目录中
				String virtualPath1 = ServletActionContext.getServletContext()
						.getRealPath("/");

				for (File file : fileList) {
					String virtualPath = "/upload/"	+ CommonUtils.getUploadFileName() + avatarNumber
							+ ".jpg";
					// 此处执行保存操作
					File temp = new File(virtualPath1 + virtualPath.replace("/", "\\"));

					inputStream = new BufferedInputStream(new FileInputStream(file));
					byte[] bytes = new byte[inputStream.available()];
					outputStream = new BufferedOutputStream(
							new FileOutputStream(temp));
					Streams.copy(inputStream, outputStream, true);
					avatarNumber++;

					if (inputStream != null) {
						inputStream.close();
					}
					if (outputStream != null) {
						outputStream.flush();
						fileNameList.add(virtualPath);
						outputStream.close();
					}
				}
				result.setSuccess(true);
				result.setMsg("Success");
				result.setAvatarUrls(fileNameList);
				
				// 存储头像地址到数据库
				iconAdmin.setIconPath(fileNameList.get(0));
				
				adminService.updIcon(iconAdmin);
				
				ServletActionContext.getResponse().getWriter()
						.println(JSON.toJSONString(result));
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		/* JSONArray json = JSONArray.fromObject(result); */
		System.out.println(JSON.toJSONString(result));

		return null;
	}

	public File getAvatar1() {
		return avatar1;
	}

	public void setAvatar1(File avatar1) {
		this.avatar1 = avatar1;
	}

	public File getAvatar2() {
		return avatar2;
	}

	public void setAvatar2(File avatar2) {
		this.avatar2 = avatar2;
	}

	public File getAvatar3() {
		return avatar3;
	}

	public void setAvatar3(File avatar3) {
		this.avatar3 = avatar3;
	}

}
