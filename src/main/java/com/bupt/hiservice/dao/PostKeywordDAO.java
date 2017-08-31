package com.bupt.hiservice.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bupt.hiservice.entity.post.PostKeyword;

public interface PostKeywordDAO extends JpaRepository<PostKeyword, Long> {

	List<PostKeyword> findByValueIn(Iterable<String> values);
	
}
