package com.bupt.hiservice.entity.post;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "post")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region = "com.bupt.hiservice.entity.post")
public class Post extends Text {

	private static final long serialVersionUID = -3083813214468856573L;

	@Override
	public String toString() {
		return "Post [value=" + value + ", id=" + id + "]";
	}

}
