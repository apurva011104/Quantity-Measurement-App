package com.apps.quantity_measurement_app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apps.quantity_measurement_app.entity.OperationHistoryEntity;

public interface OperationHistoryRepository extends JpaRepository<OperationHistoryEntity, Long>{
    List<OperationHistoryEntity> findByOperationTypeIgnoreCase(String operationType);
}
