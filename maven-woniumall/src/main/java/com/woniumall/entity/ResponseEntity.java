package com.woniumall.entity;

/**
 *  ajax请求-统一响应格式
 */
public class ResponseEntity<T> {
	private int code;  //状态码
	private String msg;  //消息内容
	private T data;   //数据

	public ResponseEntity() {
	}

	public ResponseEntity(int code, String msg) {
		this(code, msg, null);
	}

	public ResponseEntity(int code, String msg, T data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	public static final ResponseEntity<Void> SUCCESS = new ResponseEntity<Void>(200, "ok");
                public static final ResponseEntity<Void> FORBID= new ResponseEntity<Void>(403, "权限不足");
	public static final ResponseEntity<Void> FAIL = new ResponseEntity<Void>(500, "Error");

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
