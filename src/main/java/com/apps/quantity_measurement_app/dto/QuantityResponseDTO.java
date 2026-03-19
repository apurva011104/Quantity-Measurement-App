package com.apps.quantity_measurement_app.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class QuantityResponseDTO {
    @NotNull(message="Value cannot be null")
    private Double quantityValue;

    @NotNull(message="Unit cannot be empty")
    private String unit;

    @NotNull(message="Measurement type cannot be empty")
    @Pattern(regexp = "LengthUnit|WeightUnit|VolumeUnit|TemperatureUnit",
         message = "Invalid measurement type")
    private String measurementType;

    public QuantityResponseDTO() {
    }

    public QuantityResponseDTO(Double quantityValue, String unit, String measurementType) {
        this.quantityValue = quantityValue;
        this.unit = unit;
        this.measurementType = measurementType;
    }

    public Double getValue() {
        return quantityValue;
    }

    public void setValue(Double value) {
        this.quantityValue = value;
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
