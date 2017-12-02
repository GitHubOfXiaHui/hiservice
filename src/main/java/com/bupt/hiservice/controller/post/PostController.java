package com.bupt.hiservice.controller.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bupt.clientsdk.dto.post.PostCreateReqDTO;
import com.bupt.clientsdk.dto.post.PostCreateResDTO;
import com.bupt.clientsdk.dto.post.PostDeleteReqDTO;
import com.bupt.clientsdk.dto.post.PostDeleteResDTO;
import com.bupt.clientsdk.dto.post.PostFindReqDTO;
import com.bupt.clientsdk.dto.post.PostFindResDTO;
import com.bupt.clientsdk.dto.post.PostGetReqDTO;
import com.bupt.clientsdk.dto.post.PostGetResDTO;
import com.bupt.clientsdk.dto.post.PostUpdateReqDTO;
import com.bupt.clientsdk.dto.post.PostUpdateResDTO;
import com.bupt.hiservice.aspect.UserValidation;
import com.bupt.hiservice.service.post.PostService;

@RestController
@RequestMapping("/post")
public class PostController {

	@Autowired
	private PostService postService;

	@UserValidation
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public PostCreateResDTO create(@RequestBody PostCreateReqDTO req) throws Exception {
		return postService.savePost(req);
	}

	@UserValidation
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public PostDeleteResDTO delete(@RequestBody PostDeleteReqDTO req) throws Exception {
		return postService.deletePost(req);
	}
	
	@UserValidation
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public PostUpdateResDTO update(@RequestBody PostUpdateReqDTO req) throws Exception {
		return postService.updatePost(req);
	}
	
	@UserValidation
	@RequestMapping(value = "/find", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public PostFindResDTO find(@RequestBody PostFindReqDTO req) throws Exception {
		return postService.findPosts(req);
	}
	
	@UserValidation
	@RequestMapping(value = "/get", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public PostGetResDTO get(@RequestBody PostGetReqDTO req) throws Exception {
		return postService.getPost(req);
	}
	
}
