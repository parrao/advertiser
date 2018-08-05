package com.iheart.ms.model;

public class Advertiser {
	
	private Integer id;
	private String contactName;
	private Integer  creditLimit;
	
	public Advertiser() {
		
	}

	public Advertiser(int id,String contactName,int creditLimit) {
	
		this.id=id;
		this.contactName=contactName;
		this.creditLimit=creditLimit;
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContact_name(String contactName) {
		this.contactName = contactName;
	}

	public Integer getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(int creditLimit) {
		this.creditLimit = creditLimit;
	}
	
	
	@Override
	  public String toString() {
	    return "Advertiser{" +
	        "id=" + id +
	        ", contactName='" + contactName + '\'' +
	        ", creditLimit=" + creditLimit +
	        '}';
	  }
	
}
