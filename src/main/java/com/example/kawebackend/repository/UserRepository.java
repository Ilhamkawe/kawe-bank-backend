package com.example.kawebackend.repository;

import com.example.kawebackend.dto.reqbody.UserReqBody;
import com.example.kawebackend.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
//    @Query(value="SELECT u FROM UserEntity u JOIN u.wallet w")
//    List<UserEntity> getUsers();
//    @Query("""
//    SELECT new com.example.kawebackend.entity.UserEntity()
//""")
    @Query(value= "SELECT * FROM users JOIN wallets ON users.id = wallets.user_id", nativeQuery = true)
    List<UserEntity> getUsers();

}
