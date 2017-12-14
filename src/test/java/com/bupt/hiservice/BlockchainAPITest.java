package com.bupt.hiservice;

import java.util.Date;

import org.junit.Test;

import com.bupt.hiservice.blockchain.BlockchainAPI;
import com.bupt.hiservice.entity.security.User;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BlockchainAPITest {
	
	@Test
	public void blockchainTest() throws Exception {
		User user = new User();
		user.setCompanyCode("6207223100001");
		user.setCompanyName("微软");
		user.setCorporation("比尔·盖茨");
		user.setCorporationId("X");
		user.setPhone("X");
		user.setRentDate(new Date());
		user.setExpireDate(new Date());
		String str = new ObjectMapper().writeValueAsString(user);
		System.out.println(BlockchainAPI.getTransactionDateByHash(BlockchainAPI.sendTransaction(str)));
	}
	
	public static void main(String[] args) throws Exception {
		User user = new User();
		user.setCompanyCode("6207223100001");
		user.setCompanyName("微软");
		user.setCorporation("比尔·盖茨");
		user.setCorporationId("X");
		user.setPhone("X");
		user.setRentDate(new Date());
		user.setExpireDate(new Date());
		String str = new ObjectMapper().writeValueAsString(user);
		System.out.println(str);
	}

}
