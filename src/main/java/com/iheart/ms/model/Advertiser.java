package com.iheart.ms.model;

public class Advertiser {
	
	private int id;
	private String contact_name;
	private int  credit_limit;
	
	public Advertiser() {
		
	}

	public Advertiser(int id,String contact_name,int credit_limit) {
	
		this.id=id;
		this.contact_name=contact_name;
		this.credit_limit=credit_limit;
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContact_name() {
		return contact_name;
	}

	public void setContact_name(String contact_name) {
		this.contact_name = contact_name;
	}

	public int getCredit_limit() {
		return credit_limit;
	}

	public void setCredit_limit(int credit_limit) {
		this.credit_limit = credit_limit;
	}
	
	
	
	
}
