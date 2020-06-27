package com.sample.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CreateUserRequestModel {
	
	@NotNull(message="First Name is required")
	@Size(min=2, message = "Fname should be more than 2 char")
	private String fName;
	private String lastName;
	@NotNull
	@Size(min=2, max=16 , message = "Password should be greater than 2 and lesser than 16")
	private String password;
	@NotNull(message = "Email cannot be null")
	@Email
	private String email;
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}


}
