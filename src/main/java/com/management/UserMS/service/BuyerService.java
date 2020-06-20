package com.management.UserMS.service;

import java.util.regex.Pattern;
import javax.naming.InvalidNameException;
import javax.validation.Validator;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


//import com.management.UserMS.Validator.BuyerValidator;
import com.management.UserMS.dto.BuyerDTO;
import com.management.UserMS.entity.Buyer;
import com.management.UserMS.repository.BuyerRepository;
@Service
public class BuyerService {
	private static final Logger logger = LoggerFactory.getLogger(BuyerService.class);
	@Autowired
	BuyerRepository buyerRepo;
	
//	@Autowired
//	BuyerValidator buyerValidator;

	
	
	public String registerBuyer(BuyerDTO buyerDTO) throws Exception {
		
		try {
		logger.info("Registration request for user {}", buyerDTO);
		validateBuyer(buyerDTO);
		Buyer be=buyerDTO.createEntity();
		buyerRepo.save(be);
		return("new user created");
		}catch(Exception e) {
			throw new Exception("Name is not valid");
		}
	}
	private void validateBuyer(BuyerDTO buyerDTO) throws Exception {

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

	
	

	public boolean buyerLogin(BuyerDTO buyerDTO2) throws Exception {

		Buyer buyer = buyerRepo.findByEmail(buyerDTO2.getEmail());
		if (buyer != null) {
			if (buyer.getPassword().equals(buyerDTO2.getPassword())) {
			return true;
			} else {
				throw new Exception("BuyerLogin:Invalid Password");
			}
		}
		return false;
	}

	public void deactivateBuyer(BuyerDTO buyerDTO) throws Exception {

		Buyer buyer = buyerRepo.findByEmail(buyerDTO.getEmail());
		if (buyer != null) {
			buyer.setIsActive(false);
			buyerRepo.save(buyer);
		} else {
			System.out.println("Invalid Email ID ");
		}

	}
	
	
	public int getRewardPoints(int buyerId) {
		System.out.println("BuyerId"+buyerId);
		Buyer buyer=buyerRepo.findByBuyerId(buyerId);
		return buyer.getRewardPoints();

		
	}
	
	public void updateRewardPoint(int buyerId, int point) {
		Buyer buyer =buyerRepo.findByBuyerId(buyerId);
		if (buyer!=null){
		buyer.setRewardPoints(point);
		buyerRepo.save(buyer);
		}else {
			System.out.println("Invalid BuyerId");
		}
		
	}
	public void updateBuyerPrivilege(String email, boolean privilege) throws Exception {
		Buyer buyer= buyerRepo.findByEmail(email);
		if(buyer!=null){
			if(buyer.getIsPrivileged()) {
				if(!privilege) {
					buyer.setIsPrivileged(privilege);
				    buyerRepo.save(buyer);
					
				}
				else {
				throw new Exception("Buyer is already privileged");
				}
			}
			else if(!(buyer.getIsPrivileged())) {
				if(privilege) {
					if(buyer.getRewardPoints()<10000) {
						throw new Exception("Insufficient Reward Points");
					}
					else {
						buyer.setIsPrivileged(privilege);
						buyer.setRewardPoints(buyer.getRewardPoints()-10000);
						buyerRepo.save(buyer);
					}	
				}
				else {
					
					throw new Exception("Not Privileged");
					
				}
			}
		buyer.setIsPrivileged(privilege);
	    buyerRepo.save(buyer);
		}
		else {
			throw new Exception("Invalid Email");
		}
		
			
	}
	
public boolean IsPrivileged(int buyerId) {
		
		Buyer buyer= buyerRepo.findByBuyerId(buyerId);
		
		if((buyer.getIsPrivileged())==false) {
			
			return false;
		}
		else {
			
			return true;
		}
		
	}
}
//	
//	public int getRewardPoint(int buyerId) {
//		System.out.println("inside buyer service"+buyerId);
//		BuyerEntity buyerEntity=buyerRepository.findByBuyerId(buyerId);
//		System.out.println(buyerEntity);
//		return buyerEntity.getRewardPoints();
//
//		
//	}
//	
//	public void updateRewardPoint(int buyerId, int point) {
//		BuyerEntity buyerEntity =buyerRepository.findByBuyerId(buyerId);
//		buyerEntity.setRewardPoints(point);
//		buyerRepository.save(buyerEntity);
//		
//	}
//	
//	public boolean IsPrivileged(int buyerId) {
//		
//		BuyerEntity buyerEntity= buyerRepository.findByBuyerId(buyerId);
//		
//		if((buyerEntity.isPrivileged())==false) {
//			
//			return false;
//		}
//		else {
//			
//			return true;
//		}
//		
//	}
//	
//	public void updateBuyerPrivilege(String email,boolean privilege) throws UserException {
//		BuyerEntity buyerEntity= buyerRepository.findByEmail(email);
//		System.out.println("=====service========");
//		if(buyerEntity!=null){
//			if(buyerEntity.isPrivileged()) {
//				if(!privilege) {
//					buyerEntity.setPrivileged(privilege);
//				    buyerRepository.save(buyerEntity);
//					
//				}
//				else {
//				throw new AlreadyPrivilegeException("Buyer.ALREADY_PRIVILEGE");
//				}
//			}
//			else if(!(buyerEntity.isPrivileged())) {
//				if(privilege) {
//					if(buyerEntity.getRewardPoints()<10000) {
//						throw new InSufficientRewardPoint("Buyer.INSUFFICIENT_REWARD_POINTS");
//					}
//					else {
//						buyerEntity.setPrivileged(privilege);
//						buyerEntity.setRewardPoints(buyerEntity.getRewardPoints()-10000);
//						buyerRepository.save(buyerEntity);
//					}
//					
//				}
//				else {
//					
//					throw new AlreadyNotPrivilegeException("Buyer.ALREADY_NOT_PRIVILEGE");
//					
//				}
//			}
//		buyerEntity.setPrivileged(privilege);
//	    buyerRepository.save(buyerEntity);
//		}
//		else {
//			throw new InvalidEmailIdException("Buyer.INVALID_EMAIL");
//		}
//		
//	}
//	
//	public Buyer getBuyerDetails(String email) throws UserException {
//		BuyerEntity buyerEntity= buyerRepository.findByEmail(email);
//		if(buyerEntity!=null) {
//		Buyer buyer=new Buyer();
//		BeanUtils.copyProperties(buyerEntity, buyer);
//		return buyer;
//		}
//		else {
//			throw new InvalidEmailIdException("Buyer.INVALID_EMAIL");
//		}
//		
//	}
//	
//	
//	
//	
//	
//	
//	
//}
