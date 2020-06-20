package com.management.UserMS.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import com.management.UserMS.Validator.BuyerValidator;
import com.management.UserMS.dto.BuyerDTO;
import com.management.UserMS.entity.Buyer;
import com.management.UserMS.service.BuyerService;

@RestController
@CrossOrigin
@RequestMapping(value="/api")
public class BuyerController {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	BuyerService buyerService;

	

	@PostMapping(value="buyer/register",  consumes = MediaType.APPLICATION_JSON_VALUE)
	public String registerUser(@RequestBody BuyerDTO buyerDTO) {
		try {
		logger.info("Registration request for buyer {}", buyerDTO);
		buyerDTO.setIsActive(true);
		buyerDTO.setIsPrivileged(false);
		buyerDTO.setRewardPoints(0);
		buyerService.registerBuyer(buyerDTO);
		return "Successfull";
	}catch(Exception e) {
		return("Not sucessfull");
	}
	}

	@PostMapping(value = "buyer/login",consumes = MediaType.APPLICATION_JSON_VALUE)
	public String loginBuyer(@RequestBody BuyerDTO buyerDTO) throws Exception {
		try {
			buyerService.buyerLogin(buyerDTO);
			return "Login Successfull";
		} catch (Exception e) {
			return " Login unsuccessfull, check your credentials and try again";
		}
	}


	@PostMapping(value = "buyer/deactivate",consumes = MediaType.APPLICATION_JSON_VALUE)
	public String deactivateBuyer(@RequestBody BuyerDTO buyerDTO) throws Exception{
			try {
				buyerService.deactivateBuyer(buyerDTO);
			} catch (Exception e) {
				throw new Exception("Invalid Credentials");
			}
			return "Account Deactivated";
	
	}
	
	@GetMapping(value = "rewardPoint/{buyerId}")
	public int getRewardPoints(@PathVariable int buyerId) {
		System.out.println("BuyerID is:"+ buyerId);
		int Points = buyerService.getRewardPoints(buyerId);
		return Points;
	
	}
	
	@PutMapping(value = "rewardPoint/update/{buyerId}/{point}")
	public void updateRewardPoint(@PathVariable int buyerId,@PathVariable int point) {
	
		buyerService.updateRewardPoint(buyerId, point);

	}
	
	@GetMapping(value = "buyer/isPrivilege/{buyerId}")
	public boolean isBuyerPrivileged(@PathVariable int buyerId) {
		System.out.println("inside buyer privilege");
		return buyerService.IsPrivileged(buyerId);
	}
}

