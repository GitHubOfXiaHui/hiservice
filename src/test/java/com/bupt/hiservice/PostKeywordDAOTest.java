package com.bupt.hiservice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bupt.hiservice.dao.PostKeywordDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:applicationContext.xml", "classpath:applicationContext-mvc.xml",
		"classpath:applicationContext-jpa.xml" })
public class PostKeywordDAOTest {

	@Autowired
	private PostKeywordDAO postKeywordDAO;
	
	@Test
	public void namingStrategyTest() {
		postKeywordDAO.findOne(1L);
		System.out.println("SUCCESS");
	}
	
}
