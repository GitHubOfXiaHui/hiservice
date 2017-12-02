package com.bupt.hiservice.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.bupt.hiservice.entity.post.PostKeyword;

public interface PostKeywordDAO extends JpaRepository<PostKeyword, Long> {

	@Query(value = "select * from post_keyword_mem where keyword in ?1", nativeQuery = true)
	List<PostKeyword> findByKeywordIn(Iterable<String> keywords);

	@Transactional
	@Modifying
	void deleteByPostId(Long postId);
	
}
