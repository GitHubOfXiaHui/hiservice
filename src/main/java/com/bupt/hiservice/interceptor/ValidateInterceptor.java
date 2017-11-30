package com.bupt.hiservice.interceptor;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.bupt.hiservice.blockchain.BlockchainAPI;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ValidateInterceptor extends HandlerInterceptorAdapter {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String user = getUser(request);
		String lease = BlockchainAPI.getTransactionDateByHash(user);
		if (lease == null || isExpired(lease)) {
			return false;
		}
		return super.preHandle(request, response, handler);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private String getUser(HttpServletRequest request) throws IOException {
		ServletInputStream in = request.getInputStream();
		Map req = new ObjectMapper().readValue(in, Map.class);
		return req != null ? (String) req.getOrDefault("user", "") : "";
	}

	private boolean isExpired(String lease) {
		long now = System.currentTimeMillis();
		return Long.parseLong(lease) < now;
	}

}
