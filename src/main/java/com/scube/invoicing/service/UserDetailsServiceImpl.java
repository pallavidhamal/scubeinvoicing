package com.scube.invoicing.service;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.scube.invoicing.entity.UserInfoEntity;
import com.scube.invoicing.repository.UserInfoRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	
	@Autowired
//	UserRepository userRepository;
	UserInfoRepository	empInfoRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserInfoEntity user = empInfoRepository.findByMobilenumber(username);

		return UserDetailsImpl.build(user);
	}

}