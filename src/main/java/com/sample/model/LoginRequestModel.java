package com.sample.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import com.sun.istack.NotNull;

public class LoginRequestModel {
	
	@Email
	@Size(min=8, max=20 ,message = "Email is not of proper length")
	String email;
	@NotNull
	String password;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	

}
