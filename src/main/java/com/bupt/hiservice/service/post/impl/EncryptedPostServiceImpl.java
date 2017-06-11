package com.bupt.hiservice.service.post.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bupt.hiservice.dao.EncryptedPostDao;
import com.bupt.hiservice.dao.EncryptedPostKeywordDao;
import com.bupt.hiservice.entity.post.EncryptedPost;
import com.bupt.hiservice.entity.post.EncryptedPostKeyword;
import com.bupt.hiservice.service.post.EncryptedPostService;
import com.bupt.hiservice.vo.EncryptedPostReqVo;

@Service
@Transactional(readOnly = true)
public class EncryptedPostServiceImpl implements EncryptedPostService {
	
	@Autowired
	private EncryptedPostDao encryptedPostDao;
	
	@Autowired
	private EncryptedPostKeywordDao encryptedPostKeywordDao;

	@Transactional
	@Override
	public EncryptedPost saveEncryptedPost(EncryptedPostReqVo encryptedPostReqVo) {
		// 保存帖子
		EncryptedPost encryptedPost = encryptedPostDao.saveAndFlush(encryptedPostReqVo.getEncryptedPost());
		// 保存帖子关键词索引
		Set<String> keywords = encryptedPostReqVo.getKeywords();
		List<EncryptedPostKeyword> encryptedPostKeywords = new ArrayList<>();
		for (String keyword : keywords) {
			EncryptedPostKeyword encryptedPostKeyword = new EncryptedPostKeyword();
			encryptedPostKeyword.setKeyword(keyword);
			encryptedPostKeyword.setPostId(encryptedPost.getId());
			encryptedPostKeywords.add(encryptedPostKeyword);
		}
		encryptedPostKeywordDao.save(encryptedPostKeywords);
		return encryptedPost;
	}

}
