package com.bridgelabz.controller;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bridgelabz.model.LoginPojo;
import com.bridgelabz.service.UserService;
import com.bridgelabz.service.UserServiceImpl;

@Controller
public class LoginController {

	@Autowired
	private UserService userserviceimpl;

//handler mapping
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView showLogin(@ModelAttribute LoginPojo loginDetails) {
		ModelAndView mav = new ModelAndView("login");
		mav.addObject("loginDetails", loginDetails);
		return mav;
	}

//post mapping
	@PostMapping(value = "/loginProcess")
	public ModelAndView loginProcess(@ModelAttribute LoginPojo loginDetails)
			throws NoSuchAlgorithmException, NoSuchProviderException {
		ModelAndView mav = null;
		String password = loginDetails.getPassword();
		String encryptpass = ((UserServiceImpl) userserviceimpl).getSecurePassword(password);
		loginDetails.setPassword(encryptpass);
		int valid = userserviceimpl.login(loginDetails);
		if (valid == 1) {
			mav = new ModelAndView("welcome");
			mav.addObject("firstname", loginDetails.getFirstname());
		} else {
			mav = new ModelAndView("errorpage");
		}
		return mav;
	}

}