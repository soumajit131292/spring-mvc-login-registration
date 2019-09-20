package com.bridgelabz.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.bridgelabz.model.LoginPojo;
import com.bridgelabz.model.RegistrationDetails;
import com.bridgelabz.repository.AppDao;

@Service()
public class UserServiceImpl implements UserService {

	@Autowired
	 private AppDao daoImpl;

	@Autowired
	private MailSender mailSender;

//calling for registration	
	@Override
	public int register(RegistrationDetails userDetails) {
		int result = daoImpl.doregister(userDetails);
		return result;
	}

//calling for login
	public int login(LoginPojo loginDetails) {
		int result = daoImpl.doLogin(loginDetails);
		return result;
	}

//calling for mail id is present or not
	public int checkEmailIsPresent(String email) {
		int result = daoImpl.checkEmail(email);
		if (result > 0) {
			return 1;
		}
		return 0;
	}

//calling for password reset 
	public void setPassword(String password, String emailid) {
		daoImpl.setPasswordToDataBase(password, emailid);
	}

//sending email to user
	public void sendMail(String to, String otp) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("soumajit131292@gmail.com");
		message.setTo(to);
		message.setSubject("OTP");
		message.setText(otp);
		mailSender.send(message);
	}

//creating password encryption

	public String getSecurePassword(String passwordToHash) throws NoSuchAlgorithmException, NoSuchProviderException {
		String generatedPassword = null;
		try {
			// Create MessageDigest instance for MD5
			MessageDigest md = MessageDigest.getInstance("MD5");
			// Add password bytes to digest
			md.update(passwordToHash.getBytes());
			// Get the hash's bytes
			byte[] bytes = md.digest();
			// This bytes[] has bytes in decimal format;
			// Convert it to hexadecimal format
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			// Get complete hashed password in hex format
			generatedPassword = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return generatedPassword;
	}

	@Override
	public void dleteUser(String email) {
		daoImpl.deleteById(email);
		
	}

	@Override
	public List<RegistrationDetails> showdata() {
		return daoImpl.getUser();		
	}
}