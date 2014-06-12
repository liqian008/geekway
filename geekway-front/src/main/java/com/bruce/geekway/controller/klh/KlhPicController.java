package com.bruce.geekway.controller.klh;


import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value={"klh"})
public class KlhPicController {
	
	/**
	 * 投票详情
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/pic")
	public String voteInfo(Model model, HttpServletRequest request) {
		return "klh/pic/pic";
	}
}
