package com.google.utils;

import com.google.exceptions.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;


/**
 * 
 * 返回数据封装
 * @author iuv
 * @date 2017年7月4日 上午11:26:47
 */
public class R extends HashMap<String, Object> {
	
	private static final long serialVersionUID = 3179330537764417168L;
	private static final Logger logger = LoggerFactory.getLogger(R.class);
	

	public R() {
		put("errorCode", 0);
		put("errorMsg", "操作成功!");
	}
	
	public static R fail(Exception e){
		if(e instanceof BusinessException){
			BusinessException ex = (BusinessException) e;
			return fail(-1,ex.getMessage());
		}else{
			logger.error(e.getMessage(),e);
			return fail(-1,"未知异常,请联系平台客服人员");
		}
	}
	
	public static R fail(int code, String msg) {
		R r = new R();
		r.put("errorCode", code);
		r.put("errorMsg", msg);
		return r;
	}
	
	public static R fail() {
		R r = new R();
		r.put("errorCode", -1);
		r.put("errorMsg", "操作失败");
		return r;
	}

	public static R succ(String msg) {
		R r = new R();
		r.put("errorMsg", msg);
		return r;
	}
	
	public static R succ(Map<String, Object> map) {
		R r = new R();
		r.putAll(map);
		return r;
	}
	
	public static R succ() {
		return new R();
	}

	public R put(String key, Object value) {
		super.put(key, value);
		return this;
	}
}
