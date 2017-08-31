package com.bupt.hiservice.service.post.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bupt.clientsdk.dto.BaseResponseDTO;
import com.bupt.clientsdk.dto.KeywordDTO;
import com.bupt.clientsdk.dto.enumeration.ResponseEnum;
import com.bupt.clientsdk.dto.page.DWZPage;
import com.bupt.clientsdk.dto.page.PageUtils;
import com.bupt.clientsdk.dto.post.PostCreateReqDTO;
import com.bupt.clientsdk.dto.post.PostCreateResDTO;
import com.bupt.clientsdk.dto.post.PostDTO;
import com.bupt.clientsdk.dto.post.PostDeleteReqDTO;
import com.bupt.clientsdk.dto.post.PostDeleteResDTO;
import com.bupt.clientsdk.dto.post.PostFindReqDTO;
import com.bupt.clientsdk.dto.post.PostFindResDTO;
import com.bupt.clientsdk.dto.post.PostGetReqDTO;
import com.bupt.clientsdk.dto.post.PostGetResDTO;
import com.bupt.hiservice.dao.PostDAO;
import com.bupt.hiservice.dao.PostKeywordDAO;
import com.bupt.hiservice.entity.post.Post;
import com.bupt.hiservice.entity.post.PostKeyword;
import com.bupt.hiservice.service.post.PostService;

@Service
@Transactional(readOnly = true)
public class PostServiceImpl implements PostService {

	@Autowired
	private PostDAO postDAO;

	@Autowired
	private PostKeywordDAO postKeywordDAO;

	@Transactional
	@Override
	public PostCreateResDTO savePost(PostCreateReqDTO req) throws Exception {
		// TODO Auto-generated method stub
		// 保存帖子
		Post post = new Post();
		post.setValue(req.getPost().getText());
		post = postDAO.saveAndFlush(post);

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
		postKeywordDAO.save(postKeywords);
		return BaseResponseDTO.buildResponse(ResponseEnum.SUCCESS, PostCreateResDTO.class);
	}

	@Override
	public PostFindResDTO findPosts(PostFindReqDTO req) throws Exception {
		// TODO Auto-generated method stub
		Set<String> keywords = req.getKeywords();
		DWZPage dwzPage = req.getPage();
		Page<Post> page;
		if (keywords != null && !keywords.isEmpty()) {
			page = findByKeywords(keywords, PageUtils.createPageable(dwzPage));
		} else {
			page = findAll(PageUtils.createPageable(dwzPage));
		}

		return buildPostFindResDTO(page, dwzPage);
	}

	private Page<Post> findAll(Pageable page) {
		// TODO Auto-generated method stub
		return postDAO.findAll(page);
	}

	private Page<Post> findByKeywords(Set<String> keywords, Pageable page) {
		// TODO Auto-generated method stub
		List<PostKeyword> postKeywords = postKeywordDAO.findByValueIn(keywords);
		Set<Long> postIds = new HashSet<>();
		for (PostKeyword postKeyword : postKeywords) {
			postIds.add(postKeyword.getPostId() ^ postKeyword.getSalt());
		}
		if (!postIds.isEmpty()) {
			return postDAO.findByIds(postIds, page);
		} else {
			return new PageImpl<Post>(new ArrayList<>());
		}
	}

	private PostFindResDTO buildPostFindResDTO(Page<Post> page, DWZPage dwzPage) throws Exception {
		// TODO Auto-generated method stub
		List<Post> posts = page.getContent();
		List<PostDTO> postDTOs = new ArrayList<>();
		for (Post post : posts) {
			PostDTO postDTO = new PostDTO(post.getId(), post.getValue());
			postDTOs.add(postDTO);
		}
		PageUtils.injectPageProperties(dwzPage, page);

		PostFindResDTO response = BaseResponseDTO.buildResponse(ResponseEnum.SUCCESS, PostFindResDTO.class);
		response.setPosts(postDTOs);
		response.setPage(dwzPage);
		return response;
	}

	@Transactional
	@Override
	public PostDeleteResDTO deletePost(PostDeleteReqDTO req) throws Exception {
		// TODO Auto-generated method stub
		Post post = postDAO.findOne(req.getId());
		post.setExpired(true);
		postDAO.save(post);
		return BaseResponseDTO.buildResponse(ResponseEnum.SUCCESS, PostDeleteResDTO.class);
	}

	@Override
	public PostGetResDTO getPost(PostGetReqDTO req) throws Exception {
		// TODO Auto-generated method stub
		Post post = postDAO.getOne(req.getId());
		PostGetResDTO response = BaseResponseDTO.buildResponse(ResponseEnum.SUCCESS, PostGetResDTO.class);
		response.setPost(new PostDTO(post.getId(), post.getValue()));
		return response;
	}

	private static final ThreadLocalRandom RAN = ThreadLocalRandom.current();

}
