package com.bruce.geekway.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bruce.geekway.annotation.NeedAuthorize;
import com.bruce.geekway.service.IRegUserService;

@Controller
public class AccountController {

	private static final Logger logger = LoggerFactory.getLogger(AccountController.class);
	
	@Autowired
	private IRegUserService regUserService;


	@NeedAuthorize
	@RequestMapping(value = "profile")
	public String profile(Model model, HttpServletRequest request, HttpServletResponse response) {
		
		return "account/profile";
	}
	
}