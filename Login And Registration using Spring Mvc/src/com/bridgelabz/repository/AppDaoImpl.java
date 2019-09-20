package com.bridgelabz.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.bridgelabz.model.LoginPojo;
import com.bridgelabz.model.RegistrationDetails;

@Repository
public class AppDaoImpl implements AppDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

//registration
	public int doregister(RegistrationDetails userDetails) {
		String sql = "insert into UserRegistration(firstname,lastname,gender,country,password,mobileNumber,emailId) values('"
				+ userDetails.getFirstname() + "','" + userDetails.getLastname() + "','" + userDetails.getGender()
				+ "','" + userDetails.getCountry() + "','" + userDetails.getPassword() + "','"
				+ userDetails.getMobileNumber() + "','" + userDetails.getEmailId() + "')";
		int execute = jdbcTemplate.update(sql);
		return execute;
	}

//login
	public int doLogin(LoginPojo loginDetails) {
		String sql = "select * from UserRegistration where emailId='" + loginDetails.getFirstname() + "' and password='"
				+ loginDetails.getPassword() + "'";
		List<RegistrationDetails> details = jdbcTemplate.query(sql, new GetUser());
		if (details.size() != 0) {
			return 1;
		} else
			return 0;
	}

//fetching data from database table 
	class GetUser implements RowMapper<RegistrationDetails> {
		@Override
		public RegistrationDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
			RegistrationDetails details = new RegistrationDetails();
			details.setFirstname(rs.getString("firstname"));
			details.setLastname(rs.getString("lastname"));
			details.setCountry(rs.getString("country"));
			details.setEmailId(rs.getString("emailId"));
			details.setMobileNumber(rs.getString("mobileNumber"));
			details.setPassword(rs.getString("password"));
			return details;
		}
	}

	public List<RegistrationDetails> getUser() {
		String sql = "select * from UserRegistration";
		List<RegistrationDetails> details = jdbcTemplate.query(sql, new GetUser());
		return details;
	}

//checking whether the email is in database or not
	public int checkEmail(String email) {
		String sql = "select * from UserRegistration where emailId= '" + email + "'";
		// String sql = "select * from UserRegistration";
		List<RegistrationDetails> details = jdbcTemplate.query(sql, new GetUser());
		if (details.size() > 0) {
			System.out.println(details);
			return 1;
		}
		return 0;
	}

//setting password to database
	public void setPasswordToDataBase(String password, String email) {
		String sql = "update UserRegistration set password = ? where emailId= ?";
		jdbcTemplate.update(sql, password, email);
	}

	@Override
	public void deleteById(String email) {
		String sql = "select * from UserRegistration where emailId = '" + email + "'";
		jdbcTemplate.update(sql);
	}
}