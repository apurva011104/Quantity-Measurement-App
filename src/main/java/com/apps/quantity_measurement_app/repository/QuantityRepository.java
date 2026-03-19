package com.apps.quantity_measurement_app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apps.quantity_measurement_app.entity.QuantityEntity;

public interface QuantityRepository extends JpaRepository<QuantityEntity, Long> {

    List<QuantityEntity> findByMeasurementTypeIgnoreCase(String measurementType);
}