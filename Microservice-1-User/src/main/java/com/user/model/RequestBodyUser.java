package com.user.model;

public class RequestBodyUser {

	private ModelUser user;

	public RequestBodyUser() {
		
	}
	
	public RequestBodyUser(ModelUser user) {
		super();
		this.user = user;
	}

	public ModelUser getUser() {
		return user;
	}

	public void setUser(ModelUser user) {
		this.user = user;
	}
	
	
}
