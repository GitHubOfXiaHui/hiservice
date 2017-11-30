package com.bupt.hiservice.entity.post;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.bupt.hiservice.entity.IdLongEntity;

@Entity
@Table(name = "post")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region = "com.bupt.hiservice.entity.post")
public class Post extends IdLongEntity {

	private static final long serialVersionUID = -3083813214468856573L;

	@Lob
	@Column(columnDefinition = "TEXT")
	private String text;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "Post [text=" + text + ", id=" + id + "]";
	}
	
}
