package com.apps.quantitymeasurement;

import com.apps.quantitymeasurement.Length.LengthUnit;

public class Length {
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

    }

    private final double value;
    private final LengthUnit unit;

    public Length(double value, LengthUnit unit) {
        if(unit==null){
            throw new IllegalArgumentException("Length Unit cannot be null");
        }
        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public LengthUnit getUnit() {
        return unit;
    }

    private double convertToBaseUnit(){
        double conversionFactor;
        if(unit==LengthUnit.FEET){
            conversionFactor =  LengthUnit.FEET.conversionFactor;
        }
        else if(unit==LengthUnit.YARDS){
            conversionFactor =  LengthUnit.YARDS.conversionFactor;
        }
        else if(unit==LengthUnit.CENTIMETERS){
            conversionFactor =  LengthUnit.CENTIMETERS.conversionFactor;
        }
        else{
            conversionFactor =  LengthUnit.INCHES.conversionFactor;
        }
    
        return Math.round(value * conversionFactor * 100.0) / 100.0;
    }

    public boolean compare(Length thatLength){
        double thatLengthValue = thatLength.convertToBaseUnit();
        double thisLengthValue = this.convertToBaseUnit();

        return Double.compare(thisLengthValue, thatLengthValue)==0;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj==null || obj.getClass()!=this.getClass()){
            return false;
        }
        Length anotherObj = (Length)obj;
        return compare(anotherObj);
    }

}
