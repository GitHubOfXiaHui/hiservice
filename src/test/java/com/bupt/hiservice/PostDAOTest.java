package com.bupt.hiservice;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bupt.hiservice.dao.PostDAO;
import com.bupt.hiservice.entity.post.Post;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:applicationContext.xml", "classpath:applicationContext-mvc.xml",
		"classpath:applicationContext-jpa.xml" })
public class PostDAOTest {
	
	@Autowired
	private PostDAO postDAO;

	@Test
	public void findByIdsTest() {
		Set<Long> ids = new HashSet<>();
		ids.add(1L);
		Page<Post> posts = postDAO.findByIds(ids, new PageRequest(0, 1));
		System.out.println(posts);
	}
}
