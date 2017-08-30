package com.bupt.hiservice.entity.post;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.bupt.hiservice.entity.Keyword;

@Entity
@Table(name = "post_keyword")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region = "com.bupt.hiservice.entity.post")
public class PostKeyword extends Keyword {

	private static final long serialVersionUID = -3307129124410202083L;

	@Column(name = "post_id")
	private Long postId;

	public Long getPostId() {
		return postId;
	}

	public void setPostId(Long postId) {
		this.postId = postId;
	}

	@Override
	public String toString() {
		return "PostKeyword [postId=" + postId + ", value=" + value + ", salt=" + salt + ", id=" + id + "]";
	}

}
