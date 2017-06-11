package com.bupt.hiservice.entity.post;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.bupt.hiservice.entity.IdLongEntity;

@Entity
@Table(name = "post_keyword")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region = "com.bupt.hiservice.entity.post")
public class EncryptedPostKeyword extends IdLongEntity {

	private static final long serialVersionUID = -3307129124410202083L;

	@Column
	private String keyword;
	
	@Column(name = "post_id")
	private Long postId;

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Long getPostId() {
		return postId;
	}

	public void setPostId(Long postId) {
		this.postId = postId;
	}
	
}
