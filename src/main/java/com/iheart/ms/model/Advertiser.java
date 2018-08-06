package com.iheart.ms.model;

public class Advertiser {
	
	private Long id;
	private String contactName;
	private Long  creditLimit;
	
	public Advertiser() {
		
	}

	public Advertiser(Long id,String contactName,Long creditLimit) {
	
		this.id=id;
		this.contactName=contactName;
		this.creditLimit=creditLimit;
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public Long getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(Long creditLimit) {
		this.creditLimit = creditLimit;
	}
	
	
	
}
