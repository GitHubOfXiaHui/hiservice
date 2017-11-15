package com.bupt.hiservice.service.post.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import com.bupt.clientsdk.dto.post.PostUpdateReqDTO;
import com.bupt.clientsdk.dto.post.PostUpdateResDTO;
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
		// 保存帖子
		Post post = new Post();
		post.setText(req.getPost().getText());

		// 保存帖子关键词索引
		Set<KeywordDTO> keywords = req.getKeywords();
		Set<PostKeyword> postKeywords = post.getPostKeywords();
		for (KeywordDTO keyword : keywords) {
			PostKeyword postKeyword = new PostKeyword();
			postKeyword.setKeyword(keyword.getValue());
			postKeyword.setPost(post);
			postKeywords.add(postKeyword);
		}
		postDAO.save(post);
		return BaseResponseDTO.buildResponse(ResponseEnum.SUCCESS, PostCreateResDTO.class);
	}

	@Override
	public PostFindResDTO findPosts(PostFindReqDTO req) throws Exception {
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
		return postDAO.findAll(page);
	}

	private Page<Post> findByKeywords(Set<String> keywords, Pageable page) {
		List<PostKeyword> postKeywords = postKeywordDAO.findByKeywordIn(keywords);
		Set<Long> postIds = new HashSet<>();
		for (PostKeyword postKeyword : postKeywords) {
			postIds.add(postKeyword.getPost().getId());
		}
		if (!postIds.isEmpty()) {
			return postDAO.findByIdIn(postIds, page);
		} else {
			return new PageImpl<Post>(new ArrayList<>());
		}
	}

	private PostFindResDTO buildPostFindResDTO(Page<Post> page, DWZPage dwzPage) throws Exception {
		List<Post> posts = page.getContent();
		List<PostDTO> postDTOs = new ArrayList<>();
		for (Post post : posts) {
			PostDTO postDTO = new PostDTO(post.getId(), post.getText());
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
		postDAO.delete(req.getId());
		return BaseResponseDTO.buildResponse(ResponseEnum.SUCCESS, PostDeleteResDTO.class);
	}

	@Override
	public PostGetResDTO getPost(PostGetReqDTO req) throws Exception {
		Post post = postDAO.findOne(req.getId());
		PostGetResDTO response = BaseResponseDTO.buildResponse(ResponseEnum.SUCCESS, PostGetResDTO.class);
		response.setPost(new PostDTO(post.getId(), post.getText()));
		return response;
	}
	
	@Transactional
	@Override
	public PostUpdateResDTO updatePost(PostUpdateReqDTO req) throws Exception {
		PostDeleteResDTO delete = deletePost(req.getPostDeleteReq());
		PostCreateResDTO create = savePost(req.getPostCreateReq());
		if (delete.isSuccess() && create.isSuccess()) {
			return BaseResponseDTO.buildResponse(ResponseEnum.SUCCESS, PostUpdateResDTO.class);
		} else {
			return BaseResponseDTO.buildResponse(ResponseEnum.ERROR_20, PostUpdateResDTO.class);
		}
	}
	
}
