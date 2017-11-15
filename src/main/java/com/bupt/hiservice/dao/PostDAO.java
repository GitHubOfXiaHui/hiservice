package com.bupt.hiservice.dao;

import java.util.Set;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bupt.hiservice.entity.post.Post;

@Mapper
public interface PostDAO {

	Long savePost(Post post);
	
	Page<Post> findByIds(@Param("ids") Set<Long> ids, @Param("page") Pageable page);

	Page<Post> findAll(Pageable page);

}
