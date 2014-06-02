package com.bruce.geekway.controller.klh;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = { "klh" })
public class KlhUserController {

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		return "klh/login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginGo(Model model) {
		return "klh/login";
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(Model model) {
		return "klh/register";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerGo(Model model) {
		return "klh/register";
	}

	@RequestMapping(value = "/bindProfile", method = RequestMethod.GET)
	public String bindProfile(Model model) {
		return "klh/bindProfile";
	}

	@RequestMapping(value = "/bindProfile", method = RequestMethod.POST)
	public String bindProfileGo(Model model) {
		return "klh/bind";
	}
}
