package com.srini.search.bean;

import org.apache.solr.client.solrj.beans.Field;

public class ClientInfoBean {

	@Field("client_type")
	private String	clientType;
	
	@Field("first_name")
	private String	firstName;
	
	@Field("middle_name")
	private String	middleName;
	
	@Field("last_name")
	private String	lastName;
	
	@Field("entity_name")
	private String	entityName;
	
	@Field("country")
	private String	country;
	
	@Field("region")
	private String	region;
	
	@Field("dob")
	private String	dob;
	
	@Field("business_id")
	private String	businessId;
	
	@Field("govt_id")
	private String	govtId;
	
	@Field("account_no")
	private long accountNo;
	
	@Field("source")
	private String	source;
	
	public ClientInfoBean() {
	}

	public String getClientType() {
		return clientType;
	}

	public void setClientType(String clientType) {
		this.clientType = clientType;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	public String getGovtId() {
		return govtId;
	}

	public void setGovtId(String govtId) {
		this.govtId = govtId;
	}

	public long getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(long accountNo) {
		this.accountNo = accountNo;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	@Override
	public String toString() {
		return "ClientInfoBean [clientType=" + clientType + ", firstName=" + firstName + ", middleName=" + middleName
				+ ", lastName=" + lastName + ", entityName=" + entityName + ", country=" + country + ", region="
				+ region + ", dob=" + dob + ", businessId=" + businessId + ", govtId=" + govtId + ", accountNo="
				+ accountNo + ", source=" + source + "]";
	}
	
}
