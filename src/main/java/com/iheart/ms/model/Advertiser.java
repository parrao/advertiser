package com.iheart.ms.model;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.Getter;

@AllArgsConstructor
@RequiredArgsConstructor
@Setter
@Getter
public class Advertiser {
	
	private Long id;
	private String contactName;
	private Long  creditLimit;
	
	
}
