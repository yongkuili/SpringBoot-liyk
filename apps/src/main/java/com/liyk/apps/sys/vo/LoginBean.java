package com.liyk.apps.sys.vo;

/**
 * 登录接口封装对象
 * @author Louis
 * @date Oct 29, 2018
 */
public class LoginBean {

	private String account;
	private String password;
	private String captcha;
	private String unique;

	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCaptcha() {
		return captcha;
	}
	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

	public String getUnique() {
		return unique;
	}

	public void setUnique(String unique) {
		this.unique = unique;
	}
}
