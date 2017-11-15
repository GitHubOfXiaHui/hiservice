package com.bupt.hiservice.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.bupt.hiservice.entity.post.Post;

public interface PostDAO extends JpaRepository<Post, Long> {

	Page<Post> findByIdIn(Iterable<Long> ids, Pageable page);

	Page<Post> findAll(Pageable page);

}
