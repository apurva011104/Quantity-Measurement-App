package com.apps.qmaservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name="quantities", 
    uniqueConstraints = {
        @UniqueConstraint(
            name = "unique_quantity_constraint",
            columnNames = {"quantity_value", "unit", "measurement_type"}
        )
    }
)
public class QuantityEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="quantity_value", nullable=false)
    private double quantityValue;

    @Column(name="unit", nullable=false)
    private String unit;

    @Column(name="measurement_type", nullable=false)
    private String measurementType;

    public QuantityEntity() {
    }

    public QuantityEntity(double value, String unit, String measurementType) {
        this.quantityValue = value;
        this.unit = unit;
        this.measurementType = measurementType;
    }

    public double getQuantityValue() {
        return quantityValue;
    }

    public void setQuantityValue(double value) {
        this.quantityValue = value;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getMeasurementType() {
        return measurementType;
    }

    public void setMeasurementType(String measurementType) {
        this.measurementType = measurementType;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return String.format("%.2f %s", quantityValue, unit);
    }
    
}
