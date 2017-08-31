package com.bupt.hiservice.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bupt.hiservice.entity.post.Post;

public interface PostDAO extends JpaRepository<Post, Long> {

	@Query(value = "select p from Post p where p.expired = false and p.id in ?1", countQuery = "select count(p) from Post p where p.expired = false and p.id in ?1")
	Page<Post> findByIds(Iterable<Long> ids, Pageable page);

}
