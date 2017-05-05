package com.bupt.hiservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	@RequestMapping({"/", "/index"})
	public String index() {
		return INDEX;
	}
	
	private static final String INDEX = "index";
}
