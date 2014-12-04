package com.bruce.geekway.model.wx.json.response;

public class WxJsonResult {
	private int errcode;
	private String errmsg;

	public int getErrcode() {
		return errcode;
	}

	public void setErrcode(Integer errcode) {
		this.errcode = errcode;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

}