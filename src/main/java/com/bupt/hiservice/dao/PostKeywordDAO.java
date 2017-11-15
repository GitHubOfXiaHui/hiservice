package com.bupt.hiservice.dao;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Mapper;

import com.bupt.hiservice.entity.post.PostKeyword;

@Mapper
public interface PostKeywordDAO {

	void saveAllPostKeyword(List<PostKeyword> postKeywords);

	List<PostKeyword> findByKeywordIn(Set<String> keywords);

}
