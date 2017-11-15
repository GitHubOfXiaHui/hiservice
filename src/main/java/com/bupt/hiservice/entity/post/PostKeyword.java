package com.bupt.hiservice.entity.post;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.bupt.hiservice.entity.IdLongEntity;

@Entity
@Table(name = "post_keyword")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region = "com.bupt.hiservice.entity.post")
public class PostKeyword extends IdLongEntity {

	private static final long serialVersionUID = -3307129124410202083L;

	@Column
	private String keyword;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "post_id")
	private Post post;

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	@Override
	public String toString() {
		return "PostKeyword [keyword=" + keyword + ", post=" + post + ", id=" + id + "]";
	}
	
}
