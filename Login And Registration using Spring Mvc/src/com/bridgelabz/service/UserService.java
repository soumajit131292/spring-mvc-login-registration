package com.bridgelabz.service;

import java.util.List;

import com.bridgelabz.model.LoginPojo;
import com.bridgelabz.model.RegistrationDetails;

public interface UserService {

	public int register(RegistrationDetails userDetails);

	public int login(LoginPojo loginDetails);

	public void setPassword(String password, String emailid);

	public int checkEmailIsPresent(String email);
	
	public void sendMail(String email,String password);

	public void dleteUser(String email);
	
	public List<RegistrationDetails> showdata();
}