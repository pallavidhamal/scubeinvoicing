package com.scube.invoicing.service;

import javax.validation.Valid;

import com.scube.invoicing.dto.AuthUserDto;
import com.scube.invoicing.dto.incoming.ForgetPasswordIncomingDto;
import com.scube.invoicing.dto.incoming.SetNewPasswordIncomingDto;
import com.scube.invoicing.dto.incoming.UserLoginIncomingDto;

public interface AuthService {

	AuthUserDto authenticateUser(UserLoginIncomingDto loginRequest);

	boolean resetPassword(String email);
	  
	boolean checkResetPasswordConditions(String email);
	  
	boolean setNewPassword(SetNewPasswordIncomingDto setNewPasswordIncomingDto);
	
	boolean signoutUser(UserLoginIncomingDto loginRequest);

//	boolean generateNewOtp(String mobileNo);
	
	//boolean generateNewOtp(ForgetPasswordIncomingDto forgetPasswordIncomingDto);
	
//	boolean validateGeneratedOtp(ForgetPasswordIncomingDto forgetPasswordIncomingDto);
	
//	void removeOtpNotVerified();

	boolean deleteUserAccount(@Valid SetNewPasswordIncomingDto setNewPasswordIncomingDto);
	
	boolean sendForgetPasswordResetLink(@Valid UserLoginIncomingDto userLoginIncomingDto);
	
	boolean resetPasswordViaEmailLink(@Valid SetNewPasswordIncomingDto setNewPasswordIncomingDto);

}
