package com.example.kawebackend.repository;

import com.example.kawebackend.entity.TransferHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferHistoryRepository extends JpaRepository<TransferHistoryEntity, Integer> {
}
