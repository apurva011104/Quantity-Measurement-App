package com.apps.quantity_measurement_app.units;

public enum WeightUnit implements IMeasurable{
    
    KILOGRAMS(1.0),
    GRAMS(0.001),
    POUNDS(0.453592);

    private final double conversionFactor;

    private WeightUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    @Override
    public String getUnitName(){
        return this.name();
    }

    @Override
    public double getConversionFactor() {
        return conversionFactor;
    }

    @Override
    public double convertToBaseUnit(double value){
        return value * conversionFactor;
    }

    @Override
    public double convertFromBaseUnit(double value){
        return value / conversionFactor;
    }
    
}
