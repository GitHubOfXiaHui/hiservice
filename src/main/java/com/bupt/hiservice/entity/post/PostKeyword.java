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
public class PostKeyword extends IdLongEntity {

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((keyword == null) ? 0 : keyword.hashCode());
		result = prime * result + ((postId == null) ? 0 : postId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PostKeyword other = (PostKeyword) obj;
		if (keyword == null) {
			if (other.keyword != null)
				return false;
		} else if (!keyword.equals(other.keyword))
			return false;
		if (postId == null) {
			if (other.postId != null)
				return false;
		} else if (!postId.equals(other.postId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PostKeyword [keyword=" + keyword + ", postId=" + postId + ", id=" + id + "]";
	}

}
