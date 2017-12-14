package com.bupt.hiservice.entity.security;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class User implements Serializable {

	private static final long serialVersionUID = -5498145518708463186L;
	
	@JsonIgnore
	private String commend;
	
	private String companyCode;
	
	private String companyName;
	
	private String address;
	
	private String phone;
	
	private String corporation;
	
	private String corporationId;
	
	private Date rentDate;
	
	private Date expireDate;

	public String getCommend() {
		return commend;
	}

	public void setCommend(String commend) {
		this.commend = commend;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCorporation() {
		return corporation;
	}

	public void setCorporation(String corporation) {
		this.corporation = corporation;
	}

	public String getCorporationId() {
		return corporationId;
	}

	public void setCorporationId(String corporationId) {
		this.corporationId = corporationId;
	}

	public Date getRentDate() {
		return rentDate;
	}

	public void setRentDate(Date rentDate) {
		this.rentDate = rentDate;
	}

	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	@Override
	public String toString() {
		return "User [commend=" + commend + ", companyCode=" + companyCode + ", companyName=" + companyName
				+ ", address=" + address + ", phone=" + phone + ", corporation=" + corporation + ", corporationId="
				+ corporationId + ", rentDate=" + rentDate + ", expireDate=" + expireDate + "]";
	}

}
