package com.bupt.hiservice;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import com.bupt.clientsdk.dto.page.DWZPage;
import com.bupt.clientsdk.dto.post.PostFindReqDTO;
import com.bupt.clientsdk.dto.post.PostFindResDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:applicationContext.xml", "classpath:applicationContext-mvc.xml",
		"classpath:applicationContext-jpa.xml" })
public class PostControllerTest {
	
	@Test
	public void findTest() {
		RestTemplate rest = new RestTemplate();
		PostFindReqDTO req = new PostFindReqDTO();
		Set<String> keywords = new HashSet<>();
		keywords.add("一");
		DWZPage page = new DWZPage();
		req.setKeywords(keywords);
		req.setPage(page);
		PostFindResDTO response = rest.postForObject("http://localhost:8080/hiservice/post/find", req, PostFindResDTO.class);
		System.out.println(response);
	}
	
}
