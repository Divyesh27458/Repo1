package com.management.UserMS.Validator;

import java.util.regex.Pattern;

import javax.naming.InvalidNameException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.management.UserMS.Validator.BuyerValidator;
import com.management.UserMS.dto.BuyerDTO;
import com.management.UserMS.entity.Buyer;
import com.management.UserMS.repository.BuyerRepository;
import com.management.UserMS.service.BuyerService;

public class BuyerValidator{
private static final Logger logger = LoggerFactory.getLogger(BuyerService.class);
@Autowired
BuyerRepository buyerRepo;

public void validateBuyer(BuyerDTO buyerDTO) throws Exception {

		logger.info("Buyer details are being validated");
		// TODO Auto-generated method stub
		if(!isValidName(buyerDTO.getName()))
			throw new InvalidNameException("BuyerRegistration: Invalid Name");
		if(!isValidEmail(buyerDTO.getEmail()))
			throw new Exception("BuyerRegistration: Invalid Email");
		if(!isValidPhoneNumber(buyerDTO.getPhoneNumber()))
			throw new Exception("BuyerRegistration:Invalid Phone number");
		if(!isvalidPassword(buyerDTO.getPassword()))
			throw new Exception("BuyerRegistration: Invalid Password");
		if(!isAlreadyPhoneNumberExist(buyerDTO.getPhoneNumber()))
			throw new Exception("BuyerRegistration: Phone number already exists");
		if(!isAlreadyEmailIdExist(buyerDTO.getEmail()))
			throw new Exception("BuyerRegistration: Email already exists");
		
		
	}

	private boolean isAlreadyEmailIdExist(String email) {
		// TODO Auto-generated method stub
		Buyer buyer=buyerRepo.findByEmail(email);
		if (buyer!=null)
			return false;
		return true;
	}

	private boolean isAlreadyPhoneNumberExist(String phoneNumber) {
		// TODO Auto-generated method stub
		Buyer buyer=buyerRepo.findByPhoneNumber(phoneNumber);
		if (buyer!=null)
			return false;
		return true;
	}

	private boolean isvalidPassword(String password) {
		return Pattern.matches("(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*]).{7,20}$",password);
	}

	private boolean isValidPhoneNumber(String phoneNumber) {
		// TODO Auto-generated method stub
		return Pattern.matches("^\\d{10}$", phoneNumber);
	}

	private boolean isValidEmail(String email) {
		// TODO Auto-generated method stub
		return Pattern.matches("^[A-Za-z0-9+_.-]+@(.+)$",email);
	}

	private boolean isValidName(String name) {
		// TODO Auto-generated method stub
		return Pattern.matches("^[a-zA-Z]+[-a-zA-Z\\s]+([-a-zA-Z]+)$", name);
	}


}
