//package com.management.UserMS.service;
//
//import java.util.Optional;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.management.UserMS.Validator.Validator;
//import com.management.UserMS.dto.BuyerDTO;
//import com.management.UserMS.dto.BuyerLoginDTO;
//import com.management.UserMS.dto.SellerDTO;
//import com.management.UserMS.dto.SellerLoginDTO;
//import com.management.UserMS.entity.Buyer;
//import com.management.UserMS.entity.Seller;
//import com.management.UserMS.repository.BuyerRepository;
//import com.management.UserMS.repository.SellerRepository;
//@Service
//public class UserService {
//	Logger logger = LoggerFactory.getLogger(this.getClass());
//	@Autowired
//	BuyerRepository buyerRepo;
//	@Autowired
//	SellerRepository sellerRepo;
//	
//	
//	public String registerBuyer(BuyerDTO buyerDTO) throws Exception {
//		
//		try {
//		logger.info("Registration request for user {}", buyerDTO);
//		if (Validator.buyerValidate(buyerDTO));{
//		Buyer be=buyerDTO.createEntity();
//		buyerRepo.save(be);
//		return("new user created");
//		}
//		}catch(Exception e) {
//			throw new Exception("Name is not valid");
//		}
//	}
//	
//	public String registerSeller(SellerDTO sellerDTO) throws Exception {
//		
//		try {
//		logger.info("Registration request for seller {}", sellerDTO);
//		Seller se=sellerDTO.createEntity();
//		
//		sellerRepo.save(se);
//		return("new seller created");
//		}catch(Exception e) {
//			throw new Exception("Name is not valid");
//		}
//	}
//	public boolean Buyerlogin(BuyerLoginDTO loginDTO) {
//		Buyer buyer = null;
//		logger.info("Login request for customer {} with password {}",loginDTO.getBuyerId(), loginDTO.getEmail(), loginDTO.getPassword());
////		Optional<Buyer> optBuyer = buyerRepo.findById(loginDTO.getBuyerId(),loginDTO.getEmail());
//		Optional<Buyer> optBuyer = buyerRepo.findById(loginDTO.getBuyerId());
//		if (optBuyer.isPresent()) {
//			buyer = optBuyer.get();
//			if (buyer.getPassword().equals(loginDTO.getPassword())) {
//				return true;
//			}
//		}
//
//		return false;
//	}
//	
//	public boolean Sellerlogin(SellerLoginDTO loginDTO) {
//		Seller seller = null;
//		logger.info("Login request for customer {} with password {}",loginDTO.getSellerId(),loginDTO.getPassword());
////		Optional<Buyer> optBuyer = buyerRepo.findById(loginDTO.getBuyerId(),loginDTO.getEmail());
//		Optional<Seller> optSeller = sellerRepo.findById(loginDTO.getSellerId());
//		if (optSeller.isPresent()) {
//			seller = optSeller.get();
//			if (seller.getPassword().equals(loginDTO.getPassword())) {
//				return true;
//			}
//		}
//
//		return false;
//	}
//	
//	public void deactivateBuyer(Integer buyerId) throws Exception {
//		try {
//			buyerRepo.deleteById(buyerId);
//			System.out.println("Buyer account deleted successfully");
//		}catch(Exception e) {
//			throw new Exception("Unsuccessful");
//		}
//	}
//	
//	public void deactivateSeller(Integer sellerId) throws Exception {
//		try {
//			sellerRepo.deleteById(sellerId);
//			System.out.println("Seller account deleted successfully");
//		}catch(Exception e) {
//			throw new Exception("Unsuccessful");
//		}
//	}
//
//}