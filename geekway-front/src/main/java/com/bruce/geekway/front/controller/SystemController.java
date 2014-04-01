package com.bruce.geekway.front.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value="/mobile")
public class SystemController {

	
	@RequestMapping(value = "/index")
	public String index(Model model) {
		return "mobile/index";
	}
	
	@RequestMapping(value = "/blogs")
	public String blogList(Model model) {
		return "mobile/blogList";
	}
	
	@RequestMapping(value = "/blog")
	public String blog(Model model) {
		return "mobile/blog";
	}
}
