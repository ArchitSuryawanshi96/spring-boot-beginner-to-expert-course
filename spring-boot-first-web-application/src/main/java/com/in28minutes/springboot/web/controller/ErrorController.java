package com.in28minutes.springboot.web.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@Controller("error")
public class ErrorController {

	@ExceptionHandler(Exception.class)
	public ModelAndView handleException(HttpServletRequest request, Exception ex) {
		ModelAndView mView = new ModelAndView();
		mView.addObject("exception", ex.getLocalizedMessage());
		mView.addObject("url", request.getRequestURI());
		mView.setViewName("error");
		return mView;
	}

}
