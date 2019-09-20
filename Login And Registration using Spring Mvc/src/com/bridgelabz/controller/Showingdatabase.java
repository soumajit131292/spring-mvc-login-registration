package com.bridgelabz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bridgelabz.model.RegistrationDetails;
import com.bridgelabz.service.UserService;

@Controller
public class Showingdatabase {

	@Autowired
	private UserService userserviceimpl;

	@RequestMapping("/show")
	public String view() {
		return "show";

	}

	@RequestMapping("/showdata")
	public ModelAndView showDb() {
		String email="ab.cd@gmail.com";
		userserviceimpl.dleteUser(email);
		List<RegistrationDetails> details=userserviceimpl.showdata();
		ModelAndView mv=new ModelAndView("showdata");
		mv.addObject("users", details);
		System.out.println();
		return mv;
	}
	@ExceptionHandler
	public String exceptionHandling(Exception exception)
	{
		return "error";
	}
}