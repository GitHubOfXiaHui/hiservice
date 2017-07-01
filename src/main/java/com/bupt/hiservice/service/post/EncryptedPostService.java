package com.bupt.hiservice.service.post;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

import com.bupt.hiservice.entity.post.EncryptedPost;
import com.bupt.hiservice.vo.EncryptedPostReqVo;

public interface EncryptedPostService {

	EncryptedPost saveEncryptedPost(EncryptedPostReqVo param)  throws InvalidKeyException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException;

}
