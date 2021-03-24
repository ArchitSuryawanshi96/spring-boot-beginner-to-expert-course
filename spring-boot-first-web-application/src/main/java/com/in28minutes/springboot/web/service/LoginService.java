package com.in28minutes.springboot.web.service;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
public class LoginService {
	public boolean validateUser(String userid, String password) {
		return userid.equalsIgnoreCase("archit") && password.equalsIgnoreCase("asd");
	}
}
