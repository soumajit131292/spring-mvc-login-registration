package com.bridgelabz.repository;

import java.util.List;

import com.bridgelabz.model.LoginPojo;
import com.bridgelabz.model.RegistrationDetails;

public interface AppDao {

	public int doregister(RegistrationDetails userDetails);
	public int doLogin(LoginPojo loginDetails);
	public int checkEmail(String email);
	public void setPasswordToDataBase(String password, String email);
	public void deleteById(String email);
	public List<RegistrationDetails> getUser();
}
