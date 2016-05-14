package com.stx.zzq.base;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.stx.zzq.common.utils.CommonUtils;

/**
 * 基础类
 * 
 * @author zzq_eason
 *
 */
public abstract class BaseEntity implements Map<String, Object>, Serializable {

	/* 封装map接口 */
	private Map<String, Object> map;

	/* 构造方法 */
	public BaseEntity() {
		map = new HashMap<String, Object>();
	}

	public BaseEntity(Map<String, Object> map) {
		this.map = map;
	}

	public BaseEntity(int init) {
		map = new HashMap<String, Object>(init);
	}

	/* 复写map接口方法 */
	@Override
	public int size() {
		return map.size();
	}

	@Override
	public boolean isEmpty() {
		return map.isEmpty();
	}

	@Override
	public boolean containsKey(Object key) {
		return map.containsKey(key);
	}

	@Override
	public boolean containsValue(Object value) {
		return map.containsValue(value);
	}

	@Override
	public Object get(Object key) {
		return map.get(key);
	}

	@Override
	public Object put(String key, Object value) {
		return map.put(key, value);
	}

	@Override
	public Object remove(Object key) {
		return map.remove(key);
	}

	/**
	 * 此方法是之后将
	 * 使用getParameterMap
	 * 直接将元素放入对应的实体类之中
	 */
	@Override
	public void putAll(Map<? extends String, ? extends Object> m) {
		// 判断是否为空
		if(CommonUtils.isEmpty(m)) {
			map.putAll(m);
			return ;
		}
		// 遍历map将里面的key和value分别放入对应的实体类中
		for (Map.Entry<? extends String, ? extends Object> entry : m.entrySet()) {
			map.put(entry.getKey(), entry.getValue());
		}
		
	}

	@Override
	public void clear() {
		map.clear();
	}

	@Override
	public Set<String> keySet() {
		return map.keySet();
	}

	@Override
	public Collection<Object> values() {
		return map.values();
	}

	@Override
	public Set<java.util.Map.Entry<String, Object>> entrySet() {
		return map.entrySet();
	}
	
	// 返回字符串
	public String getToString(String fieldName) {
		Object obj = get(fieldName);
		if(obj == null) {
			return null;
		}
		return obj.toString();
	}

}
