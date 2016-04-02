package com.beebank.model;

import java.io.Serializable;

import com.beebank.constants.ReturnCode;

public class RspDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1873017490336366047L;
	
	
	

	public RspDTO(String ret) {
		super();
		this.ret = ret;
	}
	
	

	public RspDTO() {
		super();
	}



	public RspDTO(String ret, String msg) {
		super();
		this.ret = ret;
		this.msg = msg;
	}
	

	private String ret = ReturnCode.SUCCESS;
	
	private String msg;

	public String getRet() {
		return ret;
	}

	public void setRet(String ret) {
		this.ret = ret;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	

}
