package com.stx.zzq.common.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONArray;

import org.junit.Test;

import com.stx.zzq.entities.Admin;

/**
 * 工具类
 * 
 * @author zzq_eason
 *
 */
public class CommonUtils {

	/**
	 * 转化为Integer类型
	 */

	/**
	 * 是否为空
	 */
	public static boolean isEmpty(Object obj) {
		if (obj == null) {
			return true;
		}
		if (obj instanceof String) {
			if (((String) obj).trim().length() == 0) {
				return true;
			}
		} else if (obj instanceof Collection) {
			if (((Collection) obj).size() == 0) {
				return true;
			}
		} else if (obj instanceof Map) {
			if (((Map) obj).size() == 0) {
				return true;
			}
		} else if (obj instanceof List) {
			if (((List) obj).size() == 0) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 校验手机号码
	 * 
	 * @param mobile
	 * @return
	 */
	public static boolean isMobile(String mobile) {
		if (isEmpty(mobile)) {
			return false;
		}
		String regEx = "[1]{1}[0-9]{10}";
		return Pattern.compile(regEx).matcher(mobile).matches();
	}

	/**
	 * 是否是EMAIL格式
	 * 
	 * @param email
	 * @return
	 */
	public static boolean isEmail(String email) {
		/** EMAIL 格式正则 */
		String mailRegx = "[\\w\\.\\_\\-]+@(\\w+.)+[a-zA-Z]{2,3}";
		Pattern pattern = Pattern.compile(mailRegx);
		Matcher m = pattern.matcher(email.toLowerCase());
		if (m.matches()) {
			return true;
		}
		return false;
	}

	/**
	 * 工具类将 List、Map转换成json格式
	 */
	public static JSONArray getMlToJson(Object obj) {
		JSONArray jsonArray = new JSONArray();
		if (isEmpty(obj)) {
		}
		if (obj instanceof List) {
			jsonArray = JSONArray.fromObject(obj);
		}
		if (obj instanceof Map) {
			jsonArray = JSONArray.fromObject(obj);
		}
		return jsonArray;
	}

	/**
	 * 获取当前时间
	 */
	public static String getCurrentTime() {
		Date current = new Date();
		return dateToString(current);
	}

	/**
	 * 时间转化String
	 */
	public static String dateToString(Date date) {
		SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");
		return simpleDate.format(date);
	}

	/**
	 * 测试json转换
	 */
	@Test
	public void test() {
		List<Admin> list = new ArrayList<Admin>();
		for (int i = 0; i < 8; i++) {
			Admin admin = new Admin();
			admin.setUsername("user" + i);
			admin.setPassword("1234sa" + i);
			list.add(admin);
		}
		System.out.println(getMlToJson(list));
	}

	/**
	 * 获取随机的图片名字
	 * 
	 * @return
	 */
	public static String getUploadFileName() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssS");
		String fileName = simpleDateFormat.format(new Date());
		Random random = new Random();
		String randomCode = "";
		for (int i = 0; i < 8; i++) {
			randomCode += Integer.toString(random.nextInt(36), 36);
		}
		fileName = fileName + randomCode;
		return fileName;
	}
	
	/* 
	 * public Float getStringToFloat (String str) {
		float f ;
		if(str.matches()){
			
		}
		
		return; 
	}*/

	/**
	 * 处理get访问方式的乱码问题<br/>
	 * str2.getBytes().length != str2.length(); //这句就是来判断 String是否含有中文字符。<br/>
	 * ( brIDcardData[i]>= 0x4e00)&&(brIDcardData[i]<=0x9fbb);
	 * //brIDcardData的第i个字符为中文字符<br/>
	 * 
	 * @param str
	 * 
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	/*public static String getWayEncode(String str) throws UnsupportedEncodingException {
		String encodeStr = null;
		encodeStr = new String(str.getBytes("iso-8859-1"), ConstantsCode.ENCODE);
		return encodeStr;
	}*/

}
