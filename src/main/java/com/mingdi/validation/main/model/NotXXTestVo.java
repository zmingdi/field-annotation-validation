package com.mingdi.validation.main.model;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class NotXXTestVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -620451161202106762L;

	@NotEmpty
	private String notEmptyStr;
	
	@NotNull
	private String notNullStr;
	
	@NotBlank
	private String notBlankStr;

	public String getNotEmptyStr() {
		return notEmptyStr;
	}

	public void setNotEmptyStr(String notEmptyStr) {
		this.notEmptyStr = notEmptyStr;
	}

	public String getNotNullStr() {
		return notNullStr;
	}

	public void setNotNullStr(String notNullStr) {
		this.notNullStr = notNullStr;
	}

	public String getNotBlankStr() {
		return notBlankStr;
	}

	public void setNotBlankStr(String notBlankStr) {
		this.notBlankStr = notBlankStr;
	}
}
