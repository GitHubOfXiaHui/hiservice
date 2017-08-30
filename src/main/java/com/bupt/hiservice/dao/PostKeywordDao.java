package com.bupt.hiservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bupt.hiservice.entity.post.PostKeyword;

public interface PostKeywordDao extends JpaRepository<PostKeyword, Long> {

}
