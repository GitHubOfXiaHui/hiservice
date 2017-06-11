package com.bupt.hiservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bupt.hiservice.entity.post.EncryptedPostKeyword;

public interface EncryptedPostKeywordDao extends JpaRepository<EncryptedPostKeyword, Long> {

	/**
	 * 根据关键词查找索引
	 * @param key
	 * @return
	 */
	EncryptedPostKeyword findByKeyword(String keyword);

}
