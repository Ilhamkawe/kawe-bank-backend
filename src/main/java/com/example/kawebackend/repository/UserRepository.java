package com.example.kawebackend.repository;

import com.example.kawebackend.dto.reqbody.UserReqBody;
import com.example.kawebackend.dto.reqbody.wallet.WalletReqBody;
import com.example.kawebackend.entity.UserEntity;
import com.example.kawebackend.entity.WalletEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query(value = "SELECT u FROM UserEntity u JOIN u.wallet w")
    List<UserEntity> getUsersWithWallet();

    @Query(value = "SELECT  COUNT(u.email) FROM UserEntity u WHERE u.email = :email")
    Integer isEmailExist();

//    @Modifying
//    @Query(value = "INSERT INTO " +
//            "UserEntity(name, email, username, password, verified, profile_picture) " +
//            "VALUES(:#{user.name}, :#{user.email}, :#{user.username}, :#{user.password}, :#{user.verified}, :#{user.profilePicture})")
//    UserEntity registerUser(@Param("user") UserReqBody user);

}
