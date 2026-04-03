package com.apps.qmaservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apps.qmaservice.entity.OperationHistoryEntity;


public interface OperationHistoryRepository extends JpaRepository<OperationHistoryEntity, Long>{
    List<OperationHistoryEntity> findByUserEmail(String userEmail);

    List<OperationHistoryEntity> findByUserEmailAndOperationTypeIgnoreCase(String userEmail, String operationType);

    void deleteByUserEmail(String userEmail);
}
