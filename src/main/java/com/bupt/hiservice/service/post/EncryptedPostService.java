package com.bupt.hiservice.service.post;

import com.bupt.hiservice.entity.post.EncryptedPost;
import com.bupt.hiservice.vo.EncryptedPostReqVo;

public interface EncryptedPostService {

	EncryptedPost saveEncryptedPost(EncryptedPostReqVo param);

}
