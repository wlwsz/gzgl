<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>头像上传</title>
<script type="text/javascript" src="<%=path %>/fullAvatarEditor-plugin/swfobject.js"></script>
<script type="text/javascript" src="<%=path %>/fullAvatarEditor-plugin/fullAvatarEditor.js"></script>
</head>
<body>
	<div style="width: 800px; margin: 0 auto;">
		<div>
			<p id="swfContainer">
				本组件需要安装Flash Player后才可使用，请从 <a
					href="http://www.adobe.com/go/getflashplayer">这里</a> 下载安装。
			</p>
		</div>
		<!-- <button type="button" id="upload">自定义上传按钮</button> -->
	</div>
	<script type="text/javascript">
		swfobject.addDomLoadEvent(function() {
			var swf = new fullAvatarEditor("fullAvatarEditor-plugin/fullAvatarEditor.swf",
					"fullAvatarEditor-plugin/expressInstall.swf", "swfContainer", {
						id : "swf",
						/** imageAction_saveImage.action 
						 *	icon_saveIcon.action 
						 **/
						upload_url : "back/icon_saveIcon.action",
						method : "post",
						src_upload : 0, //是否上传原图片的选项，有以下值：0-不上传；1-上传；2-显示复选框由用户选择
						browse_tip : '仅支持JPG、JPEG、GIF、PNG格式的图片文件\n文件不能大于200KB',
						src_size : '200KB',
						src_size_over_limit : '文件大小（{0}）超出限制（200KB）\n请重新上传',
						avatar_sizes : '120*120|80*80|50*50',
						avatar_sizes_desc : '120*120像素|80*80像素|50*50像素',
						avatar_field_names : 'avatar1|avatar2|avatar3'
					}, function(msg) {
						switch (msg.code) {
						case 1:
							/* alert("页面成功加载了组件！"); */
							break;
						case 2:
							/* alert("已成功加载图片到编辑面板。"); */
							break;
						case 3:
							if (msg.type == 0) {
								alert("摄像头已准备就绪且用户已允许使用。");
							} else if (msg.type == 1) {
								alert("摄像头已准备就绪但用户未允许使用！");
							} else {
								alert("摄像头被占用！");
							}
							break;
						case 5:
							if (msg.type == 0) {
								/* if (msg.content.sourceUrl) {
									alert("原图片已成功保存至服务器，url为：\n"
											+ msg.content.sourceUrl);
								} */
								/* alert("头像已成功保存至服务器，url为：\n"
										+ msg.content.avatarUrls.join("\n")); */
								location.reload();
							}
							break;
						}
					});
			/* document.getElementById("upload").onclick = function() {
				swf.call("upload");
			}; */
		});
	</script>
</body>
</html>