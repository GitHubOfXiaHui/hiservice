package com.bupt.hiservice;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bupt.clientsdk.dto.KeywordDTO;
import com.bupt.clientsdk.dto.page.DWZPage;
import com.bupt.clientsdk.dto.post.PostCreateReqDTO;
import com.bupt.clientsdk.dto.post.PostDTO;
import com.bupt.clientsdk.dto.post.PostDeleteReqDTO;
import com.bupt.clientsdk.dto.post.PostFindReqDTO;
import com.bupt.clientsdk.dto.post.PostFindResDTO;
import com.bupt.clientsdk.dto.post.PostGetReqDTO;
import com.bupt.hiservice.service.post.PostService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:applicationContext.xml", "classpath:applicationContext-mvc.xml",
		"classpath:applicationContext-jpa.xml" })
public class PostServiceImplTest {

	@Autowired
	private PostService postService;
	
	@Test
	public void savePostTest() throws Exception {
		PostCreateReqDTO req = new PostCreateReqDTO();
		PostDTO postDTO = new PostDTO();
		postDTO.setText("a");
		req.setPost(postDTO);
		Set<KeywordDTO> keywordDTOs = new HashSet<>();
		KeywordDTO keywordDTO = new KeywordDTO();
		keywordDTO.setValue("a");
		keywordDTOs.add(keywordDTO);
		req.setKeywords(keywordDTOs);
		postService.savePost(req);
		System.out.println("SUCCESS");
	}
	
	@Test
	public void findPostsTest() throws Exception {
		PostFindReqDTO req = new PostFindReqDTO();
		Set<String> keywords = new HashSet<>();
		keywords.add("a");
		req.setKeywords(keywords);
		DWZPage page = new DWZPage();
		req.setPage(page);
		PostFindResDTO res = postService.findPosts(req);
		System.out.println(res);
	}
	
	@Test
	public void deletePostTest() throws Exception {
		PostDeleteReqDTO req = new PostDeleteReqDTO();
		req.setId(5L);
		postService.deletePost(req);
	}
	
	@Test
	public void getPostTest() throws Exception {
		PostGetReqDTO req = new PostGetReqDTO();
		req.setId(6L);
		System.out.println(postService.getPost(req));
	}
	
}
