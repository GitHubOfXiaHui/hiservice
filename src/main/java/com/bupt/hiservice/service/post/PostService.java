package com.bupt.hiservice.service.post;

import com.bupt.clientsdk.dto.post.PostCreateReqDTO;
import com.bupt.clientsdk.dto.post.PostCreateResDTO;
import com.bupt.clientsdk.dto.post.PostFindReqDTO;
import com.bupt.clientsdk.dto.post.PostFindResDTO;

public interface PostService {

	PostCreateResDTO savePost(PostCreateReqDTO req) throws Exception;

	PostFindResDTO findPosts(PostFindReqDTO req) throws Exception;

}
