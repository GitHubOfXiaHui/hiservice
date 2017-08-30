package com.bupt.hiservice.service.post;

import com.bupt.clientsdk.dto.post.PostCreateReqDTO;
import com.bupt.clientsdk.dto.post.PostCreateResDTO;

public interface PostService {

	PostCreateResDTO savePost(PostCreateReqDTO req) throws Exception;

}
