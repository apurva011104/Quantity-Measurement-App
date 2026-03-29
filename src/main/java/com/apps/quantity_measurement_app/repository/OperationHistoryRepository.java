package com.apps.quantity_measurement_app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apps.quantity_measurement_app.entity.OperationHistoryEntity;
import com.apps.quantity_measurement_app.entity.User;

public interface OperationHistoryRepository extends JpaRepository<OperationHistoryEntity, Long>{
    List<OperationHistoryEntity> findByUser(User user);

    List<OperationHistoryEntity> findByUserAndOperationTypeIgnoreCase(User user, String operationType);

    void deleteByUser(User user);
}
