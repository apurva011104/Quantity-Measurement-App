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
        if(Double.compare(value, Double.NaN)==0 || value==Double.POSITIVE_INFINITY || value==Double.NEGATIVE_INFINITY){
            throw new IllegalArgumentException("Invalid length value");
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
        switch(unit){
            case FEET:
                conversionFactor =  LengthUnit.FEET.conversionFactor;
                break;
            case YARDS:
                conversionFactor =  LengthUnit.YARDS.conversionFactor;
                break;
            case CENTIMETERS:
                conversionFactor =  LengthUnit.CENTIMETERS.conversionFactor;
                break;
            case INCHES:
                conversionFactor =  LengthUnit.INCHES.conversionFactor;
                break;
            default:
                throw new IllegalArgumentException("Invalid unit");
        }

        return Math.round(value * conversionFactor * 100.0) / 100.0;
    }

    private double convertFromBaseToTargetUnit(double lengthInInches, LengthUnit targetUnit){
        return lengthInInches / targetUnit.conversionFactor ;
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

    public Length convertTo(LengthUnit targetUnit){
        if(targetUnit == null){
            throw new IllegalArgumentException("Target unit cannot be null.");
        }
        double valueInBaseUnit = convertToBaseUnit();
        double valueInTargetUnit = convertFromBaseToTargetUnit(valueInBaseUnit, targetUnit);
        valueInTargetUnit = Math.round(valueInTargetUnit * 100.0) / 100.0;
        return new Length(valueInTargetUnit, targetUnit);
    }

    public Length add(Length thatLength){
        if(thatLength==null){
            throw new IllegalArgumentException("Cannot be added to null");
        }
        double thisLengthInInches = convertToBaseUnit();
        double thatLengthInInches = thatLength.convertToBaseUnit();

        double sum = thisLengthInInches + thatLengthInInches;
        double lengthInThisUnit = convertFromBaseToTargetUnit(sum, unit);

        return new Length(lengthInThisUnit, unit);
    }

    @Override
    public String toString() {
        return String.format("%.2f %s", value, unit.toString().toLowerCase());
    }

}
