package com.apps.qmaservice.dto.requestDTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class QuantityRequestDTO {

    @NotNull(message="Value cannot be null")
    private Double quantityValue;

    @NotNull(message="Unit cannot be empty")
    private String unit;

    @NotNull(message="Measurement type cannot be empty")
    @Pattern(regexp = "LengthUnit|WeightUnit|VolumeUnit|TemperatureUnit",
         message = "Invalid measurement type")
    private String measurementType;

    public QuantityRequestDTO() {
    }

    public QuantityRequestDTO(Double value, String unit, String measurementType) {
        this.quantityValue = value;
        this.unit = unit.toUpperCase();
        this.measurementType = measurementType;
    }

    public Double getQuantityValue() {
        return quantityValue;
    }
    
    public void setQuantityValue(Double quantityValue) {
        this.quantityValue = quantityValue;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit.toUpperCase();
    }

    public String getMeasurementType() {
        return measurementType;
    }

    public void setMeasurementType(String measurementType) {
        this.measurementType = measurementType;
    }

    @Override
    public String toString(){
        return String.format("QuantityRequestDTO{Value: %.2f, Unit: %s, Measurement Type: %s}"
                                ,quantityValue, unit, measurementType);
    }
    
}
