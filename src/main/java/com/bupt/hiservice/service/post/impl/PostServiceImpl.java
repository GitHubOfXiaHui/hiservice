package com.bupt.hiservice.service.post.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bupt.clientsdk.dto.BaseResponseDTO;
import com.bupt.clientsdk.dto.KeywordDTO;
import com.bupt.clientsdk.dto.enumeration.ResponseEnum;
import com.bupt.clientsdk.dto.post.PostCreateReqDTO;
import com.bupt.clientsdk.dto.post.PostCreateResDTO;
import com.bupt.hiservice.dao.PostDao;
import com.bupt.hiservice.dao.PostKeywordDao;
import com.bupt.hiservice.entity.post.Post;
import com.bupt.hiservice.entity.post.PostKeyword;
import com.bupt.hiservice.service.post.PostService;

@Service
@Transactional(readOnly = true)
public class PostServiceImpl implements PostService {
	
	@Autowired
	private PostDao postDao;
	
	@Autowired
	private PostKeywordDao postKeywordDao;

	@Transactional
	@Override
	public PostCreateResDTO savePost(PostCreateReqDTO req) throws Exception {
		// TODO Auto-generated method stub
		// 保存帖子
		Post post = new Post();
		post.setValue(req.getText());
		post = postDao.saveAndFlush(post);
		
		// 保存帖子关键词索引
		Set<KeywordDTO> keywords = req.getKeywords();
		Collection<PostKeyword> postKeywords = new ArrayList<>();
		for (KeywordDTO keyword : keywords) {
			PostKeyword postKeyword = new PostKeyword();
			postKeyword.setValue(keyword.getValue());
			postKeyword.setSalt(RAN.nextInt());
			postKeyword.setPostId(post.getId() ^ postKeyword.getSalt());
			postKeywords.add(postKeyword);
		}
		postKeywordDao.save(postKeywords);
		return BaseResponseDTO.buildResponse(ResponseEnum.SUCCESS, PostCreateResDTO.class);
	}
	
	private static final ThreadLocalRandom RAN = ThreadLocalRandom.current();

}
