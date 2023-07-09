package com.example.kawebackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.kawebackend.dto.reqbody.wallet.WalletReqBody;
import com.example.kawebackend.entity.UserEntity;
import com.example.kawebackend.entity.WalletEntity;

@Repository
@EnableJpaRepositories
public interface WalletRepository extends JpaRepository<WalletEntity, Long> {

//    @Query(value = "INSERT INTO WalletEntity(balance, pin, user_id, card_number) VALUES(:wallet.balance, :wallet.pin, :wallet.userId :wallet.cardNumber)")
//    WalletEntity createWallet(@Param("wallet") WalletReqBody wallet);

}
