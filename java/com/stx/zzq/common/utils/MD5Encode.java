package com.stx.zzq.common.utils;

import java.security.MessageDigest;

/**
 * md5加密工具
 * 
 * @author zzq_eason
 */
public class MD5Encode {
	/***
	 * MD5加密 生成32位md5码
	 * 
	 * @param 待加密字符串
	 * @return 返回32位md5码
	 */
	public static String md5Encode(String inStr) throws Exception {
		MessageDigest md5 = null;  // 消息编码
		try {
			md5 = MessageDigest.getInstance("MD5"); // 获取MD5的编码
		} catch (Exception e) {
			e.printStackTrace();  // 异常打印
			return ""; // 结束方法
		}

		byte[] byteArray = inStr.getBytes("UTF-8");	//源字符串编程字节数组
		byte[] md5Bytes = md5.digest(byteArray); // 使用MD5进行编码
		StringBuffer hexValue = new StringBuffer(); // 存放编码结果
		for (int i = 0; i < md5Bytes.length; i++) {
			int val = ((int) md5Bytes[i]) & 0xff; // 逻辑与运算（进行32位处理）
			if (val < 16) {
				hexValue.append("0");  // 编码拼接
			}
			hexValue.append(Integer.toHexString(val)); 
		}
		return hexValue.toString(); // 返回编码结果
	}

	/**
	 * 测试主函数
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		// 测试MD5加密，MD5是不可逆置的
		System.out.println("----------MD5加密，MD5是不可逆置的------------");
		String str2 = "admin";
		System.out.println("原始：" + str2);
		System.out.println("MD5后：" + md5Encode(str2));
		System.out.println(md5Encode(str2).length());
	}

	/*
	 * public static byte[] md5encode(byte[] input) { byte[] digestedValue =
	 * null; try { MessageDigest md = MessageDigest.getInstance("MD5");
	 * md.update(input); digestedValue = md.digest(); } catch (Exception e) {
	 * e.printStackTrace(); } return digestedValue; }
	 */
}
