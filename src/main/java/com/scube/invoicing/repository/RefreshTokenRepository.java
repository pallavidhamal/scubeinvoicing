package com.scube.invoicing.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import com.scube.invoicing.entity.RefreshToken;
import com.scube.invoicing.entity.UserInfoEntity;


public interface RefreshTokenRepository extends JpaRepository<RefreshToken, String> {

    @Override
    Optional<RefreshToken> findById(String id);

    RefreshToken findByToken(String token);
    
    int deleteByUser(UserInfoEntity empInfoEntity);

}