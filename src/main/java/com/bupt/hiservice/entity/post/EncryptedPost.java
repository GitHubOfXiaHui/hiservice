package com.bupt.hiservice.entity.post;

import javax.persistence.Column;

import com.bupt.hiservice.entity.IdLongEntity;

public class EncryptedPost extends IdLongEntity {

	private static final long serialVersionUID = -3083813214468856573L;

	@Column
	private String data;

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "EncryptedPost [id=" + id + ", data=" + data + "]";
	}
	
}
