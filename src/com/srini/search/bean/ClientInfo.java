package com.srini.search.bean;

public enum ClientInfo {
	CLIENT_TYPE("client_type"),
	FIRST_NAME("first_name"),
	MIDDLE_NAME("middle_name"),
	LAST_NAME("last_name"),
	ENTITY_NAME("entity_name"),
	COUNTRY("country"),
	REGION("region"),
	DOB("dob"),
	BUSINESS_ID("business_id"),
	GOVT_ID("govt_id"),
	ACCOUNT_NO("account_no"),
	SOURCE("source");
	
	private String name;
	
	public String getName() {
		return name;
	}
	
	ClientInfo(String name) {
		this.name=name;
	}
	
}
