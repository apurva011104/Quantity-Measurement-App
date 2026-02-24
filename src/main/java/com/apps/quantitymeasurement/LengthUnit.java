package com.apps.quantitymeasurement;

public enum LengthUnit{
    FEET(12.0),
    INCHES(1.0),
    YARDS(36.0),
    CENTIMETERS(0.393701);

    private final double conversionFactor;

    private LengthUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    public double getConversionFactor() {
        return conversionFactor;
    }

    public double convertToBaseUnit(double value){
        return value * conversionFactor;
    }

    public double convertFromBaseUnit(double value){
        return value / conversionFactor;
    }
}
