package com.bupt.hiservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bupt.hiservice.entity.post.Post;

public interface PostDao extends JpaRepository<Post, Long> {

}
