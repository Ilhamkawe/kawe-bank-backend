package com.example.kawebackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import com.example.kawebackend.entity.TransactionTypeEntity;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface TransactionTypeRepository extends JpaRepository<TransactionTypeEntity, Integer> {

    @Query(value = "SELECT t FROM TransactionTypeEntity t WHERE t.code IN ('transfer', 'receiver') ORDER BY t.code ASC ")
    List<Object> getTypeTransfer();

}

