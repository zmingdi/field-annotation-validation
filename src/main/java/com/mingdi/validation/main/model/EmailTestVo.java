package com.mingdi.validation.main.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.mingdi.validation.main.annotations.First;
import com.mingdi.validation.main.annotations.Second;

public class EmailTestVo {
	/**
	 * In some condition, the same field will requested different validations.
	 * Use groups concept to complete this requirement.
	 * Define the groups, and assign the validation group where it used.
	 */
	@Email(groups= {First.class})
	@NotBlank(groups = {Second.class})
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
