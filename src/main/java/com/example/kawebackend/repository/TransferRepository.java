package com.example.kawebackend.repository;

import com.example.kawebackend.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface TransferRepository extends JpaRepository<TransactionEntity, Integer> {
//    Optional<TrasactionEntity> findById(int id);

}
