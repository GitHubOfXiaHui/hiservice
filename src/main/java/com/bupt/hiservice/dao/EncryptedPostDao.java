package com.bupt.hiservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bupt.hiservice.entity.post.EncryptedPost;

public interface EncryptedPostDao extends JpaRepository<EncryptedPost, Long> {

}
