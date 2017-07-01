package com.bupt.hiservice.service.post.impl;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bupt.hiservice.dao.EncryptedPostDao;
import com.bupt.hiservice.dao.EncryptedPostKeywordDao;
import com.bupt.hiservice.entity.post.EncryptedPost;
import com.bupt.hiservice.entity.post.EncryptedPostKeyword;
import com.bupt.hiservice.service.post.EncryptedPostService;
import com.bupt.hiservice.utils.CipherUtils;
import com.bupt.hiservice.vo.EncryptedPostReqVo;
import com.bupt.hiservice.vo.Keyword;

@Service
@Transactional(readOnly = true)
public class EncryptedPostServiceImpl implements EncryptedPostService {
	
	@Autowired
	private EncryptedPostDao encryptedPostDao;
	
	@Autowired
	private EncryptedPostKeywordDao encryptedPostKeywordDao;
	
	@Autowired
	private CipherUtils cipher;

	@Transactional
	@Override
	public EncryptedPost saveEncryptedPost(EncryptedPostReqVo encryptedPostReqVo) throws InvalidKeyException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException {
		// 保存帖子
		EncryptedPost encryptedPost = encryptedPostDao.saveAndFlush(encryptedPostReqVo.getEncryptedPost());
		// 保存帖子关键词索引
		Set<Keyword> keywords = encryptedPostReqVo.getKeywords();
		List<EncryptedPostKeyword> encryptedPostKeywords = new ArrayList<>();
		for (Keyword keyword : keywords) {
			EncryptedPostKeyword encryptedPostKeyword = new EncryptedPostKeyword();
			encryptedPostKeyword.setKeyword(keyword.getText());
			encryptedPostKeyword.setScore(keyword.getScore());
			encryptedPostKeyword.setPostId(cipher.encryptPostId(encryptedPost.getId(), (long) (keyword.getScore() * SCALE)));
			encryptedPostKeywords.add(encryptedPostKeyword);
		}
		encryptedPostKeywordDao.save(encryptedPostKeywords);
		return encryptedPost;
	}
	
	private static final int SCALE = 1000;

}
