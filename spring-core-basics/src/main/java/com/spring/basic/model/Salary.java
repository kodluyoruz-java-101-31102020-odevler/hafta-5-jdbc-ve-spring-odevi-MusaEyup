package com.spring.basic.model;

import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

// Component etiket ile bu sınıfı Spring Application contexte dahil ediyoruz
@Component
@Scope("prototype")
public class Salary {
	
	private double value;
	private Date createDate;
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	

}
