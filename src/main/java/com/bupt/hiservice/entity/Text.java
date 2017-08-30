package com.bupt.hiservice.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.Type;

@MappedSuperclass
public abstract class Text extends IdLongEntity {

	private static final long serialVersionUID = 2349658089718707845L;

	@Column
	protected String value;

	@Column
	@Type(type = "yes_no")
	protected Boolean expired = false;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Boolean getExpired() {
		return expired;
	}

	public void setExpired(Boolean expired) {
		this.expired = expired;
	}

}
