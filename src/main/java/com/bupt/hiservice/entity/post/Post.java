package com.bupt.hiservice.entity.post;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.bupt.hiservice.entity.IdLongEntity;

@Entity
@Table(name = "post")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region = "com.bupt.hiservice.entity.post")
public class Post extends IdLongEntity {

	private static final long serialVersionUID = -3083813214468856573L;

	@Column
	private String text;
	
	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<PostKeyword> postKeywords = new HashSet<>();

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Set<PostKeyword> getPostKeywords() {
		return postKeywords;
	}

	public void setPostKeywords(Set<PostKeyword> postKeywords) {
		this.postKeywords = postKeywords;
	}

	@Override
	public String toString() {
		return "Post [text=" + text + ", id=" + id + "]";
	}
	
}
