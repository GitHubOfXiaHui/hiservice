package com.bupt.hiservice.controller.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bupt.hiservice.entity.post.EncryptedPost;
import com.bupt.hiservice.service.post.EncryptedPostService;
import com.bupt.hiservice.vo.EncryptedPostReqVo;

@RestController
@RequestMapping("/post")
public class EncryptedPostController {
	
	@Autowired
	private EncryptedPostService encryptedPostService;

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public EncryptedPost create(@RequestBody EncryptedPostReqVo encryptedPostReqVo) {
		return encryptedPostService.saveEncryptedPost(encryptedPostReqVo);
	}
}
