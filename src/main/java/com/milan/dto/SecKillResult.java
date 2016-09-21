package com.milan.dto;

public class SecKillResult<T> {

	private int errcode;//状态码
	private String errmsg;//错误信息
	private T data;//数据
	
	
	public SecKillResult(int errcode, String errmsg) {
		super();
		this.errcode = errcode;
		this.errmsg = errmsg;
	}
	
	public SecKillResult(int errcode, T data) {
		super();
		this.errcode = errcode;
		this.data = data;
	}


	public int getErrcode() {
		return errcode;
	}
	public void setErrcode(int errcode) {
		this.errcode = errcode;
	}
	public String getErrmsg() {
		return errmsg;
	}
	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
}
