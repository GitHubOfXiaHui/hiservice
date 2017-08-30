package com.bupt.hiservice.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Keyword extends IdLongEntity {

	private static final long serialVersionUID = 5130374203252791777L;

	@Column
	protected String value;
	
	@Column
	protected Integer salt;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Integer getSalt() {
		return salt;
	}

	public void setSalt(Integer salt) {
		this.salt = salt;
	}
	
}
