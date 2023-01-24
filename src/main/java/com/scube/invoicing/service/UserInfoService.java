package com.scube.invoicing.service;

import java.util.List;

import javax.validation.Valid;

import com.scube.invoicing.dto.AuthUserDto;
//import com.scube.invoicing.dto.incoming.OtpVerificationIncomingDto;
import com.scube.invoicing.dto.incoming.UserInfoIncomingDto;
import com.scube.invoicing.entity.UserInfoEntity;

public interface UserInfoService {

	boolean addUserInfo(@Valid UserInfoIncomingDto userInfoIncomingDto);

	//boolean moblieOtpVerify(@Valid OtpVerificationIncomingDto otpVerificationIncomingDto);
	
	boolean editUserProfile(UserInfoIncomingDto userInfoIncomingDto);
	
	boolean deleteUserProfile(String userId);

	List<AuthUserDto> getAllUser();

	AuthUserDto getUserById(String userId);
	
//	AuthUserDto getPartnerUserById(String userId);
	

	//List<AuthUserDto> getAllPartnerUsers(String string);
	int findCountForWeekNewAddedUser();

	//List<AuthUserDto> getPartnerUsersByRoleCode(String string);
	//UserInfoEntity getChargingStationByPartnerId(String id);

	UserInfoEntity getUserByMobilenumber(String mobilenumber);
	UserInfoEntity getUserInfoEntityById(String userId);
}
