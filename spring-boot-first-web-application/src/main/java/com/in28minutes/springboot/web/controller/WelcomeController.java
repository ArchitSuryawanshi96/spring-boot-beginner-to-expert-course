package com.in28minutes.springboot.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")
public class WelcomeController {

	//LoginService service = new LoginService();
//	@Autowired
//	private LoginService service;
//	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String showWelcomePage(ModelMap model) {
		model.put("name", getLoggedinUserName());
		//model.put(null, model)
		return "welcome";
	}
	
	
	private String getLoggedinUserName() {
		Object principal = 
				SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(principal instanceof UserDetails) {
			return ((UserDetails)principal).getUsername();
		}
		return principal.toString();
	}
	
	
	
}

//	@RequestMapping(value = "/login", method = RequestMethod.POST)
//	public String showWelcomePage(@RequestParam String name, @RequestParam String password, ModelMap model) {
//		boolean isValidUser = service.validateUser(name, password);
//
//		if (!isValidUser) {
//			model.put("errorMessage", "Invalid credentials");
//			return "login";
//		}
//		model.put("name", name);
//		model.put("password", password);
//
//		return "welcome";
//	}


