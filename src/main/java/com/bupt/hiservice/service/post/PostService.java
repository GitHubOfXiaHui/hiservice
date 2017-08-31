package com.bupt.hiservice.service.post;

import com.bupt.clientsdk.dto.post.PostCreateReqDTO;
import com.bupt.clientsdk.dto.post.PostCreateResDTO;
import com.bupt.clientsdk.dto.post.PostDeleteReqDTO;
import com.bupt.clientsdk.dto.post.PostDeleteResDTO;
import com.bupt.clientsdk.dto.post.PostFindReqDTO;
import com.bupt.clientsdk.dto.post.PostFindResDTO;
import com.bupt.clientsdk.dto.post.PostGetReqDTO;
import com.bupt.clientsdk.dto.post.PostGetResDTO;

public interface PostService {

	PostCreateResDTO savePost(PostCreateReqDTO req) throws Exception;

	PostFindResDTO findPosts(PostFindReqDTO req) throws Exception;

	PostDeleteResDTO deletePost(PostDeleteReqDTO req) throws Exception;

	PostGetResDTO getPost(PostGetReqDTO req) throws Exception;

}
