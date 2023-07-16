package com.example.kawebackend.repository;

import com.example.kawebackend.dto.reqbody.user.UserReqBody;
import com.example.kawebackend.entity.UserEntity;

import org.springframework.data.jpa.repository.JpaRepository;
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

    @Query(value = "SELECT u FROM UserEntity u JOIN u.wallet WHERE u.id = :id")
    UserEntity getUsersWithWalletById(@Param("id") int id);

    @Query(value = "SELECT COUNT(u.email) > 0 FROM UserEntity u WHERE u.email = :email")
    Boolean isEmailExist(@Param("email") String email);

    @Query(value = "SELECT COUNT(u.username) > 0 FROM UserEntity u WHERE u.username = :username")
    Boolean isUsernameExist(@Param("username") String username);

    @Query(value = "SELECT u FROM UserEntity u WHERE u.id = :id")
    UserEntity getUserById(@Param("id") int id);

    @Query(value = "SELECT u FROM UserEntity  u WHERE u.email = :email")
    UserEntity getUserByEmail(@Param("email") String email);
//    @Modifying
//    @Query(value = "INSERT INTO " +
//            "UserEntity(name, email, username, password, verified, profile_picture) " +
//            "VALUES(:#{user.name}, :#{user.email}, :#{user.username}, :#{user.password}, :#{user.verified}, :#{user.profilePicture})")
//    UserEntity registerUser(@Param("user") UserReqBody user);

//    @Query(value = "UPDATE UserEntity u SET u.name = :req ")
//    Boolean updateUser(@Param("user")UserReqBody req)
}
