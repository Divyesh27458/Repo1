//package com.management.UserMS.controller;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.management.UserMS.dto.BuyerDTO;
//import com.management.UserMS.dto.BuyerLoginDTO;
//import com.management.UserMS.dto.SellerDTO;
//import com.management.UserMS.dto.SellerLoginDTO;
//import com.management.UserMS.service.UserService;
//
//@RestController
//@CrossOrigin
//@RequestMapping(value="/api")
//public class UserController {
//
//	Logger logger = LoggerFactory.getLogger(this.getClass());
//	@Autowired
//	UserService userService;
//	
//
//	@PostMapping(value="buyer/register",  consumes = MediaType.APPLICATION_JSON_VALUE)
//	public String registerUser(@RequestBody BuyerDTO buyerDTO) {
//		try {
//		logger.info("Registration request for user {}", buyerDTO);
//		userService.registerBuyer(buyerDTO);
//		return "Successful";
//	}catch(Exception e) {
//		return("Not sucessfulL");
//	}
//	}
//}
//		
////	@PostMapping(value="seller/register",  consumes = MediaType.APPLICATION_JSON_VALUE)
////	public String registerUser(@RequestBody SellerDTO sellerDTO) {
////		try {
////			logger.info("Registration request for seller {}", sellerDTO);
////			userService.registerSeller(sellerDTO);
////			return "Successful";
////		}catch(Exception e) {
////			return("Not sucessfulL");
////		}
////	}
////		
////	// Buyer Login
////	@PostMapping(value = "/buyer/login",consumes = MediaType.APPLICATION_JSON_VALUE)
////	public boolean login(@RequestBody BuyerLoginDTO loginDTO) {
////		logger.info("Login request for customer {} with password {}", loginDTO.getBuyerId(),loginDTO.getPassword());
////		return userService.Buyerlogin(loginDTO);
////		}
////	
////	// Seller Login
////	@PostMapping(value = "/seller/login",consumes = MediaType.APPLICATION_JSON_VALUE)
////	public boolean login(@RequestBody SellerLoginDTO loginDTO) {
////		logger.info("Login request for customer {} with password {}", loginDTO.getSellerId(),loginDTO.getPassword());
////		return userService.Sellerlogin(loginDTO);
////		}
////	
////	// Deactivating Buyer Account
////	@PostMapping(value="buyers/{buyerId}",consumes = MediaType.APPLICATION_JSON_VALUE)
////	public String deactivateBuyer(@PathVariable Integer buyerId) throws Exception {
////		try {
////		userService.deactivateBuyer(buyerId);
////		return "Buyer account deleted successfully";
////	}catch(Exception e) {
////		throw new Exception("Deletion unsuccessfull");
////	}
////	}
////	
////	// Deactivating Seller Account
////	@PostMapping(value="sellers/{sellerId}",consumes = MediaType.APPLICATION_JSON_VALUE)
////	public String deactivateSeller(@PathVariable Integer sellerId) throws Exception {
////		try {
////		userService.deactivateSeller(sellerId);
////		return "Seller account deleted successfully";
////	}catch (Exception e) {
////		throw new Exception("Deletion unsuccessfull");
////		
////	}
////	}
////	
////		
////}
