package com.bupt.hiservice.entity.post;

public class PostKeyword {

	private Long id;
	private String keyword;
	private Long postId;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
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
	public String toString() {
		return "PostKeyword [id=" + id + ", keyword=" + keyword + ", postId=" + postId + "]";
	}

}
